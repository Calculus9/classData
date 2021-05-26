package Examsystem;
/*
 * 客户端
 */

import Examsystem.bean.Examination;
import Examsystem.view.client.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import static Examsystem.view.client.PaperView.allCheckBoxMap;
import static Examsystem.view.client.PaperView.allJRadioButtonMap;

public class Student {

    private LoginView loginView;
    private Socket cilentSocket = null;
    private static final String connection = "127.0.0.1";
    private static final int port = 5000;
    private DataOutputStream dos = null;//new 管子
    private DataInputStream dis = null;
    private ObjectInputStream ois = null;
    private String name;
    // 登陆临时验证字符串
    private String str = new String();
    // 考试响应字符串
    public String examStart = new String();
    // 考试开始时间和持续时间字符串 & 做分隔
    private static String timeStartFromServer = null;
    private static String timeDurFromServer = null;
    // 当前登陆人员的信息存储字符串
    private String successLoginStr = null;
    // 考试准备页面视图
    public AlertView alertView = new AlertView("100");
    // 考试对象列表
    public static ArrayList<Examination> examinationList = new ArrayList<Examination>();
    // 帮助检验进度条的
    private int checkLoading = 0;
    //检验是否断网
    MyWindowListener myWindowListener = null;

    Student() {
        this.initSoket();
    }
    /*初始化连接*/
    private void initSoket() {
        try {
            cilentSocket = new Socket(connection, port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
     * 学生登陆
     */
    private void studentLogin() {
        loginView = new LoginView();
        loginView.init();
        loginView.getClick().addActionListener(new ActionListener() {
            @SuppressWarnings("deprecation")
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                str = loginView.getTextAdminNum().getText() + "&" +loginView.getTextNum().getText()+"%"+ loginView.getTextName().getText();
                name=loginView.getTextName().getText();
                sendStringInfo(str);
                if (getStringInfo().equals("1")) {
                    // 如果登录成功，就保存当前的登录状态信息
                    successLoginStr = new String(str);
                    JOptionPane.showMessageDialog(null, "登录成功");
                    EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            try {
                                // 准备页面
                                loginView.getFrame().setVisible(false);
                                alertView.getPrepareJFrame().setVisible(true);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } else {
                    successLoginStr = null;
                    JOptionPane.showMessageDialog(null, "登录失败,用户名、学号输入错误");
                }
            }
        });
    }

    /*
     * 客户端 ->服务端
     * 客户端  发送字符串消息
     * */
    public void sendStringInfo(String str) {
        try {
            dos = new DataOutputStream(this.cilentSocket.getOutputStream());
            dos.writeUTF(str);
            System.out.println("stu：" + str);
            //服务器端：接收数据
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
     * 服务端 -> 客户端
     * 客户端接收字符串消息
     */
    public String getStringInfo() {
        String string = new String();
        try {
            dis = new DataInputStream(cilentSocket.getInputStream());
            string = dis.readUTF();
            //  从服务端返回的消息为空，或者当我的分别的两个窗口关闭了的时候，就是模拟了断网的时刻
            if(string.length() == 0) {
                JOptionPane.showMessageDialog(null, "<html><font size=8> 您以离线，请尝试重新连接</html>");
            }
            String status = string.split("&")[0];
            timeStartFromServer = string.split("&")[2];
            timeDurFromServer = string.split("&")[1];
            System.out.println("string:" + string);
            System.out.println("status:" + status);
            System.out.println("timeStartFromServer:" + timeStartFromServer);
            System.out.println("timeDurFromServer:" + timeDurFromServer);
            return status;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";

    }

    /*
     *  得到 从服务端返回的 对象
     * */
    public void getObjectInfo() {
        try {
            while (true) {
                ois = new ObjectInputStream(cilentSocket.getInputStream());
                Examination examination = (Examination) ois.readObject();
                examinationList.add(examination);
                int length = examinationList.size();
                if (length == 4 * 40 + 20) {
//                    ois.close();
                    System.out.println("考试对象接收完毕");
                    break;
                }
            }
            return;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return;
    }

    /*
     * 考生须知响应
     * 服务端返回试卷对象字符串
     */
    private void studentAlert() {
        System.out.println(789);
        System.out.println(timeDurFromServer);
        alertView.getBtnNewButton().addActionListener(new ActionListener() {
            @SuppressWarnings("deprecation")
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                examStart = alertView.getBtnNewButton().getName();
                // 发送请求试卷的响应
                sendStringInfo(examStart);
                getObjectInfo();
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            // 把我得到的试卷对象处理成 答题页面
                            alertView.getPrepareJFrame().setVisible(false);
                            PaperView paper = new PaperView(examinationList);
                            myWindowListener = new MyWindowListener();
                            // 答题页面监听
                            paper.getFrame().addWindowListener(myWindowListener);
                            // 客户端的进度条
                            ProcessBar loading = new ProcessBar(paper,name);
                            loading.start();
                            System.out.println(successLoginStr);
                            // 传输每一个单选题和判断题过过去
                            sendJRadioButtonInfoToServer(successLoginStr);
                            // 传输每一个多选题过去
                            sendJCheckBoxInfoToServer(successLoginStr);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    /*
     * 根据我的字符类型，给每一个单选和选择题按钮增加监听事件，传输到服务端
     */
    public void sendJRadioButtonInfoToServer(String successLoginStr) {
        /*
         * 1.遍历单选题，找到每一个按钮，增加监听事件
         * */
        for (Integer i = 0; i < 20 * 4; i++) {
            // 这里的k就是按键的Name
            for (String key : allJRadioButtonMap.keySet()) {
                if (key.equals(Integer.toString(i))) {
                    allJRadioButtonMap.get(key).addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (e.getSource() == allJRadioButtonMap.get(key)) {
                                System.out.println("单选 " + successLoginStr + key);
                                sendStringInfo(successLoginStr + "&" + key);
                            }
                        }
                    });

                }
            }
        }
        /*
         * 遍历选择题，找到每一个按钮，增加监听事件
         */
        for (Integer i = 160; i < examinationList.size(); i++) {
            for (String key : allJRadioButtonMap.keySet()) {
                if (key.equals(Integer.toString(i))) {
                    allJRadioButtonMap.get(key).addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (e.getSource() == allJRadioButtonMap.get(key)) {
                                System.out.println("判断 " + successLoginStr + key);
                                sendStringInfo(successLoginStr + "&" + key);
                            }
                        }
                    });
                }
            }
        }
    }

    /*
     * 根据我的字符类型，给每一个多选题按钮增加监听事件，传输到服务端
     */
    public void sendJCheckBoxInfoToServer(String successLoginStr) {
        for (Integer i = 80; i < 40 * 4; i++) {
            for (String key : allCheckBoxMap.keySet()) {
                if (key.equals(Integer.toString(i))) {
                    allCheckBoxMap.get(key).addItemListener(new ItemListener() {
                        @Override
                        public void itemStateChanged(ItemEvent e) {
                            // 选上并且选中了
                            if (e.getSource() == allCheckBoxMap.get(key)) {
                                if (e.getStateChange() == ItemEvent.SELECTED) {
                                    sendStringInfo(successLoginStr + "&" + key);
                                } else {
                                    // 多了一个"&"符号，表示没有选中
                                    sendStringInfo(successLoginStr + "&" + key + "&");
                                }
                            }
                            System.out.println(key);
                        }
                    });
                }
            }
        }

    }

    public AlertView getPreparingPageView() {
        return alertView;
    }

    public void setPreparingPageView(AlertView alertView) {
        this.alertView = alertView;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Student student = new Student();
                    // 逻辑处理放在main函数里面
                    student.studentLogin();
                    student.studentAlert();
                    MyWindowListener myWindowListener = new MyWindowListener();
                    // 登录页面监听
                    student.loginView.getFrame().addWindowListener(myWindowListener);
                    // 准备页面监听
                    student.alertView.getPrepareJFrame().addWindowListener(myWindowListener);
                    // 答题页面
                    student.successLoginStr = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

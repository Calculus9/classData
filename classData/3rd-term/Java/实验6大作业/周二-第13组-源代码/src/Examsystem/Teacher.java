package Examsystem;


import Examsystem.bean.Examination;
import Examsystem.bean.Student;
import Examsystem.dao.impl.StudentDaoImpl;
import Examsystem.enums.LoginEnum;
import Examsystem.service.impl.ExaminationHistoryServiceImpl;
import Examsystem.service.impl.ExaminationServiceImpl;
import Examsystem.service.impl.LoginServiceImpl;
import Examsystem.utils.JDBCUtils;
import Examsystem.view.sever.StuListView;
import Examsystem.view.sever.TeacherView;
import Examsystem.vo.ResultVO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务端
 */
public class Teacher extends JFrame {
    private static Map<String, Socket> socketMap = new HashMap<String, Socket>();
    //端口
    private static final int port = 5000;
    //
    private ServerSocket serverSocket = null;
    private boolean isStart = false;
    private Connection connection;
    private LoginServiceImpl loginService = new LoginServiceImpl();
    // 服务端页面
    private TeacherView teacherView;
    // 服务端时间
    private static String timeInfo = new String();
    private static String examDurTime = "100";
    // 从数据库获取的考试对象
    public static ExaminationServiceImpl examinationService = null;
    // 从数据库获取所有的考生对象
    public static StudentDaoImpl studentDaoImpl = null;
    // 考试历史的获取
    public static ExaminationHistoryServiceImpl examinationHistoryService = null;
    private List<Student> students;
    StuListView stuListView = null;

    public Teacher() {
        this.init();
        this.openConnection();
        this.start();
    }
    /*创建教师端视图层*/
    public void init() {
        teacherView = new TeacherView();
    }
    /*打开连接*/
    public void openConnection() {
        teacherView.getBtnContact().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (serverSocket == null)
                        serverSocket = new ServerSocket(port);
                    isStart = true;
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        // 生成试卷按钮点击时拼接字符串
        teacherView.getBtnProductExam().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timeInfo = examDurTime + "&" + "100";
                System.out.println(timeInfo);
            }
        });
    }
    /*服务器启动*/
    public void start() {
        try {
            try {
                if (serverSocket == null)
                    serverSocket = new ServerSocket(port);
                isStart = true;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            // 接受多个客户端的多个消息，
            while (isStart) {
                Socket server = serverSocket.accept();
                ClientConnection clientConnection = new ClientConnection(server);
                Thread thread = new Thread(clientConnection);
                thread.start();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    /*客户端 -> 线程 */
    class ClientConnection extends Thread {
        Socket s = null;
        public ClientConnection(Socket s) {
            this.s = s;
        }
        @Override
        public void run() {
            try {
                /*多线程链接数据库 */
                connectMysql();
                connection = JDBCUtils.connection;
                DataInputStream dis = new DataInputStream(s.getInputStream());
                while (isStart && connection != null) {
                    String infoFromClient = dis.readUTF();
                    System.out.println("info  " + infoFromClient);
                    // 保存所有的socket key是信息，值是socket对象
                    socketMap.put(infoFromClient, s);
                    /*判断从客户端传过来的字符串是请求试卷的响应还是登陆验证*/
                    // 1. 如果是登陆请求，只有一个"&"
                    int count = 0;
                    for (int i = 0; i < infoFromClient.length(); i++) {
                        if (infoFromClient.charAt(i) == '&') {
                            count++;
                        }
                    }
                    // 1.1 如果是登陆请求，只有一个"&"
                    if (count == 1) {
                        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                        if (checkInfo(infoFromClient)) {//信息正确
                            dos.writeUTF("1" + "&" + timeInfo);
                        } else {
                            dos.writeUTF("0" + "&" + timeInfo);
                        }
                    } else if (infoFromClient.equals("考试开始已响应")) {
                        System.out.println("开始响应客户端的请求试卷请求");
                        OutputStream fis = s.getOutputStream();
                        ObjectOutputStream oos = null;
                        // 2.过来的是响应试卷的请求 , 传输考试对象回去
                        for (Examination examination : examinationService.getExamination()) {
                            oos = new ObjectOutputStream(fis);
                            oos.writeObject(examination);
                        }
                        System.out.println("客户端的请求试卷传输完毕");
                        // 如果是传输的答案过来，我们要写进数据库里面
                    } else {
                        System.out.println("开始响应客户端的发送过来的判题请求");
                        /*
                         * 1. 传过来的是准考证，人，选项string，和一个有多余的"&"符号的字符串
                         * 1.1
                         */
                        int flag = 0;
                        if (infoFromClient.charAt(infoFromClient.length() - 1) == '&') {
                            // 多了一个&符号，表示点了没有选中
                            flag = 1;
                        }
                        String stuAdm = infoFromClient.split("&")[0];
                        String stuNum = (infoFromClient.split("&")[1]).split("%")[0];
                        // 传过来的选项
                        String stuSelceted = infoFromClient.split("&")[2];
                        System.out.println("当前请求的用户准考证为 " + stuAdm + " 学号为 " + stuNum + "选择的选项id为 " + stuSelceted);
                        System.out.println(Integer.parseInt(stuSelceted));
                        // 其中的key是传过来是信息,如果传过来的是单选或者判断，我们直接传题号过去更新或者新增就行
                        if (
                                (0 < Integer.parseInt(stuSelceted)) && (Integer.parseInt(stuSelceted) < 80)
                                        ||
                                        (160 <= Integer.parseInt(stuSelceted)) && (Integer.parseInt(stuSelceted) < 180)
                        ) {
                            System.out.println(Integer.parseInt(stuSelceted));
                            System.out.println("多选和单选");
                            examinationHistoryService.onQuestionOptionChangedDan(stuAdm, stuNum, Integer.parseInt(stuSelceted));
                        } else {
                            // 如果传过来的是多选题，根据范围来定的，可以不用标记多选题了
                            if (flag == 0) {
                                /* 1. 如果是点了并且选中了，直接传ID，注意这里不用管是不是同一道题 */
                                examinationHistoryService.onQuestionOptionChangedDuoChecked(stuAdm, stuNum, Integer.parseInt(stuSelceted));
                            } else {
                                /*2. 如果点了，没有选上，传id，对应的选项换成 &&；*/
                                examinationHistoryService.onQuestionOptionChangedDuoNoChceked(stuAdm, stuNum, Integer.parseInt(stuSelceted), "&&");
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /* 核对个人信息 */
    public boolean checkInfo(String str) {
        String admNum = str.split("&")[0];
        String tmp = str.split("&")[1];
        String stuNum = tmp.substring(0, 10);
        ResultVO login = loginService.login(admNum, stuNum);
        if (login.getStatus() == LoginEnum.SUCCESS.getCode()) {
            String name = getName(str);
            stuListView.render(name);
            return true;
        } else {
            return false;
        }
    }
    /*获取姓名*/
    public String getName(String str) {
        int pos = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '%') {
                pos = i;
                break;
            }
        }
        return str.substring(pos + 1, str.length());
    }
    /*连接mysql*/
    public void connectMysql() {
        JDBCUtils.beginConnect();
        /**
         * 考试试卷点击更新 + 考试历史试卷获取
         */
        examinationHistoryService = new ExaminationHistoryServiceImpl();
        /*
         * 连接的时候考试对象类获取
         */
        examinationService = new ExaminationServiceImpl();
        /*
         * 考试所有学生的对象列表获取
         */
        studentDaoImpl = new StudentDaoImpl();
        students = studentDaoImpl.findAll();
        if(stuListView==null)
            stuListView = new StuListView(students);
    }
    public static void main(String[] args) {
        Teacher service = new Teacher();
    }
}

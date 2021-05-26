package Examsystem.view.client;

import Examsystem.bean.Examination;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class PaperView extends JFrame{
    private ArrayList<Examination> examinationList;
    public int totCnt = 50;
    private int y=20;
    private Integer cnt = 0;
    public int cntSingle = 0, cntMul = 0, cntPanduan = 0;
    public JFrame frame=new JFrame("试卷");
    private ArrayList<JRadioButton>abuttons=new ArrayList<JRadioButton>();
    // 存储所有的JRadioButtonm的map对象
    public static Map<String, JRadioButton> allJRadioButtonMap = new HashMap<String, JRadioButton>();
    // 存储所有JCheckBox的map对象
    public static Map<String, JCheckBox> allCheckBoxMap = new HashMap<String, JCheckBox>();
    private ButtonGroup tmp=new ButtonGroup();
    private ArrayList<ButtonGroup>aGroup=new ArrayList<ButtonGroup>();
    private ArrayList<JCheckBox>bbtns=new ArrayList<JCheckBox>();
    private ArrayList<JRadioButton>cbuttons=new ArrayList<JRadioButton>();
    private ArrayList<ButtonGroup>cGroup=new ArrayList<ButtonGroup>();
    private JPanel contentPane=new JPanel();
    private JOptionPane jp=null;
    public JScrollPane scrollPane = null;
    public JButton submit = new JButton("提交");
    // 2. 初始化进度条
    public static ProcessBar loading;
    public PaperView(ArrayList<Examination> examinationList) {
        this.examinationList = examinationList;
        this.init();
        this.render();
    }
    /*初始化试卷*/
    public void init() {
        this.initJFrame();
        this.initContentPane();
        this.initScroll();
    }
    /*渲染试卷*/
    public void render(){
        this.renderTitle();
        this.renderSingle();
        this.renderMul();
        this.renderJud();
        this.renderSubmit();
    }
    /*初始化面板*/
    public void initJFrame() {
        frame.pack();
        frame.setBounds(500, 100, 700, 800);
    }
    /*初始化容器*/
    public void initContentPane() {
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
    }
    /*初始化滚动条*/
    public void initScroll(){
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(900, 0, 782, 829);
        scrollPane.setViewportView(contentPane);
        contentPane.setPreferredSize(new Dimension(1000,8500));
        frame.getContentPane().add(scrollPane);
    }

    /*渲染试卷标题*/
    public void  renderTitle(){
        System.out.println(examinationList.get(0).getExamName());
        JLabel title=new JLabel(examinationList.get(0).getExamName());
        title.setFont(new Font("楷体", Font.BOLD, 20));
        title.setBounds(50, 0, 450, 35);
        contentPane.add(title);
    }
    /*渲染题目类型*/
    public void renderDetailTiltle(int i){
        Examination exam1=examinationList.get(i);
        String label=exam1.getExamQuestionNum()+"、"+exam1.getExamQuestionName()+"("+exam1.getExamQuestionDescribe()+")";
        JLabel type1=new JLabel(label);
        type1.setFont(new Font("楷体", Font.BOLD, 15));
        type1.setBounds(10, y+=20, 450, 35);
        contentPane.add(type1);
    }
    /*渲染单选题*/
    public void renderSingle(){
        this.renderDetailTiltle(0);
        this.renderSingleQues();
    }
    /*渲染单选题题干*/
    public void renderSingleQues(){
        for (Integer i=0;i<20*4;i++)
        {
            Examination current=examinationList.get(i);
            if(i>=1&&(current.getQuestionNum().equals(examinationList.get(i-1).getQuestionNum()))) {}
            else{
                if(i!=0) y+=3;
                JLabel title=new JLabel(current.getQuestionNum()+"."+current.getQuestionDescribe());
                title.setFont(new Font("楷体",Font.PLAIN,15));
                title.setBounds(10,y+=30,4450,35);
                contentPane.add(title);
            }
            String s=current.getOptionNum()+"、"+current.getOptionContent();
            JRadioButton content=new JRadioButton(s);
            content.setFont(new Font("楷体",Font.PLAIN,15));
            content.setBounds(10,y+=30,4450,35);
            // 单选题按键存储到对象中
            String tempStr = Integer.toString(++cnt);
            content.setName(tempStr);
            abuttons.add(content);
            tmp.add(content);
            if((cnt)%4==0){tmp = new ButtonGroup();}
            contentPane.add(content);
            cntSingle++;
        }
    }
    /*渲染多选题*/
    public void renderMul(){
        this.renderDetailTiltle(81);
        this.renderMulQues();
    }
    /*渲染多选题题干*/
    public void renderMulQues(){
        for (Integer i=80;i<40*4;i++)
        {
            Examination current=examinationList.get(i);
            if(i>=1&&(current.getQuestionNum().equals(examinationList.get(i-1).getQuestionNum()))) {}
            else{
                if(i!=0) y+=3;
                JLabel title=new JLabel(current.getQuestionNum()+"."+current.getQuestionDescribe());
                title.setFont(new Font("楷体",Font.PLAIN,15));
                title.setBounds(10,y+=30,4450,35);
                contentPane.add(title);
            }
            String s=current.getOptionNum()+"、"+current.getOptionContent();
            JCheckBox content=new JCheckBox(s);
            content.setFont(new Font("楷体",Font.PLAIN,15));
            content.setBounds(10,y+=30,4450,35);
            // 存储所有的复选框到map中
            String tempStr = Integer.toString(++cnt);
            content.setName(tempStr);
            allCheckBoxMap.put(tempStr,content);
            bbtns.add(content);
            contentPane.add(content);
            cntMul++;
        }
    }
    /*渲染判断题*/
    public void renderJud(){
        this.renderDetailTiltle(161);
        this.renderJudQues();
    }
    /*渲染判断题题干*/
    public void renderJudQues(){
        for (Integer i=160;i<examinationList.size();i++)
        {
            Examination current=examinationList.get(i);
            if(i>=1&&(current.getQuestionNum().equals(examinationList.get(i-1).getQuestionNum()))) {}
            else{
                if(i!=0) y+=3;
                JLabel title=new JLabel(current.getQuestionNum()+"."+current.getQuestionDescribe());
                title.setFont(new Font("楷体",Font.PLAIN,15));
                title.setBounds(10,y+=30,4450,35);
                contentPane.add(title);
            }
            String s=current.getOptionNum()+"、"+current.getOptionContent();
            JRadioButton content=new JRadioButton(s);
            content.setFont(new Font("楷体",Font.PLAIN,15));
            content.setBounds(10,y+=30,4450,35);
            //判断题按键存储到map对象中
            String tempStr = Integer.toString(++cnt);
            content.setName(tempStr);
            allJRadioButtonMap.put(tempStr,content);
            cbuttons.add(content);
            tmp.add(content);
            if((cnt)%2==0){tmp=new ButtonGroup();}
            contentPane.add(content);
            cntPanduan++;
        }
    }
    /*渲染提交按钮*/
    public void renderSubmit(){
        y+=50;
        submit.setBounds(250, y, 100, 50);
        submit.setFont(new Font("楷体",Font.BOLD,20));
        contentPane.add(submit);
        //监听事件
        submit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int res=jp.showConfirmDialog(null, "确认交卷？", "提示", JOptionPane.YES_NO_OPTION);
                if (res==JOptionPane.YES_OPTION)
                {
                    frame.setVisible(false);
                    JOptionPane.showMessageDialog(null, "<html><font size=8>考试已结束，请等待老师判卷！</html>");
                }
            }
        });
    }
    /*倒计时*/
    public void countDown() {
        long currentTime = System.currentTimeMillis() + 1* 60 * 1000;
        Date end = new Date(currentTime);
        CountDown countDown = new CountDown(contentPane, this);
        countDown.execute(500, 0, end.getHours(),end.getMinutes(),end.getSeconds());
        if (System.currentTimeMillis()==currentTime)
        {
            submit.doClick();
        }
    }
    // 通过字符型的id找打对应的bottom
    //1. 判断题和单选题
    public JRadioButton findByJRadioButtonId(String str) {
        for (String key : allJRadioButtonMap.keySet()) {
            return allJRadioButtonMap.get(key);
        }
        return null;
    }
    // 2.找到对应的复选框
    public JCheckBox findByJCheckBoxId(String str) {
        for (String key : allCheckBoxMap.keySet()) {
            return allCheckBoxMap.get(key);
        }
        return null;
    }
    /*封装*/
    public JFrame getFrame() {
        return frame;
    }
    public void setFrame(JFrame frame) {
        this.frame = frame;
    }
    public JButton getSubmit() {
        return submit;
    }
    public void setSubmit(JButton submit) {
        this.submit = submit;
    }
}

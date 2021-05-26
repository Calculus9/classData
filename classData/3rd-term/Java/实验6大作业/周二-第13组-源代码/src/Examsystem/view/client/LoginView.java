package Examsystem.view.client;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {
    // 登录面板
    private JFrame frame;
    // 登录页面按键
    private JButton click;
    //文本框
    public TextField textName;
    private TextField textPass;
    private TextField textNum;
    private TextField textAdminNum;
    //标题
    private JLabel nameJLabe;
    private JLabel numJLabe;
    private JLabel adminNumJLabel;
    //容器
    private JPanel pName;
    private JPanel pNumber;
    private JPanel pAdmin;
    private JPanel pButton;
    private Font font = new Font("仿宋", Font.BOLD, 18);
    public LoginView() {
    }
    public void init() {
        this.initFrame();
        this.initJPanel();
        this.initName();
        this.initButton();
        this.initTextField();
        this.initJLabel();
        this.addSubgroup();
    }
    /*初始化面板*/
    public void initFrame() {
        frame = new JFrame("登录界面");
        frame.pack();
        frame.setLayout(new GridLayout(5, 1, 4, 4));
        frame.setSize(400, 400);
        frame.setLocation(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    /*初始化容器*/
    public void initJPanel() {
        pNumber = new JPanel();
        pAdmin = new JPanel();
        pButton = new JPanel();
    }
    /*初始化按钮*/
    public void initButton() {
//        quit = new JButton("退出登录");
        click = new JButton("确认登录");
        click.setBackground(Color.ORANGE);
//        quit.setBackground(Color.ORANGE);
        click.setFont(font);
//        quit.setFont(font);
        Dimension preferredSize = new Dimension(120, 60);
        click.setPreferredSize(preferredSize);
//        quit.setPreferredSize(preferredSize);
    }
    /*初始化文本框*/
    public void initTextField() {
        textNum = new TextField(30);
        textPass = new TextField(30);
        textAdminNum = new TextField(30);
    }
    /*初始化名字*/
    public void initName(){
        pName = new JPanel();
        textName = new TextField(30);
        nameJLabe = new JLabel("用户名");
        pName.add(nameJLabe);
        pName.add(textName);
        frame.add(pName);
    }
    /*初始化标签*/
    public void initJLabel() {
        numJLabe = new JLabel("学号");
        adminNumJLabel = new JLabel("准考证");
        nameJLabe.setFont(font);
        numJLabe.setFont(font);
        adminNumJLabel.setFont(font);
    }
    /*增加分组*/
    public void addSubgroup() {
        // 2.2学号
        pNumber.add(numJLabe);
        pNumber.add(textNum);
        // 2.3准考证号
        pAdmin.add(adminNumJLabel);
        pAdmin.add(textAdminNum);
        //2.4准考证号
        pButton.add(click);
        // 3.面板加入容器
        frame.add(pNumber);
        frame.add(pAdmin);
        frame.add(pButton);
    }
    /*封装--*/
    public JFrame getFrame() {
        return frame;
    }
    public TextField getTextAdminNum() {
        return textAdminNum;
    }
    public LoginView setTextAdminNum(TextField textAdminNum) {
        this.textAdminNum = textAdminNum;
        return this;
    }
    public JLabel getAdminNumJLabel() {
        return adminNumJLabel;
    }
    public LoginView setAdminNumJLabel(JLabel adminNumJLabel) {
        this.adminNumJLabel = adminNumJLabel;
        return this;
    }
    public JPanel getpAdmin() {
        return pAdmin;
    }
    public LoginView setpAdmin(JPanel pAdmin) {
        this.pAdmin = pAdmin;
        return this;
    }
    public LoginView setFrame(JFrame frame) {
        this.frame = frame;
        return this;
    }
    public JButton getClick() {
        return click;
    }
    public TextField getTextName() {
        return textName;
    }
    public TextField getTextPass() {
        return textPass;
    }
    public TextField getTextNum() {
        return textNum;
    }
    public JLabel getNameJLabe() {
        return nameJLabe;
    }
    public JLabel getNumJLabe() {
        return numJLabe;
    }
    public JPanel getpName() {
        return pName;
    }
    public JPanel getpButton() {
        return pButton;
    }
    public LoginView setClick(JButton click) {
        this.click = click;
        return this;
    }
    public LoginView setTextName(TextField textName) {
        this.textName = textName;
        return this;
    }
    public LoginView setTextPass(TextField textPass) {
        this.textPass = textPass;
        return this;
    }
    public LoginView setTextNum(TextField textNum) {
        this.textNum = textNum;
        return this;
    }
    public LoginView setNameJLabe(JLabel nameJLabe) {
        this.nameJLabe = nameJLabe;
        return this;

    }
    public JLabel getNumJLabel() {
        return numJLabe;
    }
    public LoginView setNumJLabe(JLabel numJLabel) {
        this.numJLabe = numJLabel;
        return this;
    }
    public LoginView setpName(JPanel pName) {
        this.pName = pName;
        return this;
    }
    public JPanel getpNumber() {
        return pNumber;
    }
    public LoginView setpNumber(JPanel pNumber) {
        this.pNumber = pNumber;
        return this;
    }
    public LoginView setpButton(JPanel pButton) {
        this.pButton = pButton;
        return this;

    }
   /*--封装*/

}

package Examsystem.view.sever;

import javax.swing.*;
import java.awt.*;

/*教师窗口界面*/
public class TeacherView {
    // 服务端面板
    private JFrame frame;

    // 分隔组件
    private JPanel pContact;
    private JPanel pProductExam;
    private JPanel pExport;
    private JPanel pExportSco;
    private JPanel timeStartJPanel;

    // 服务端标签
//    private JLabel contactJLabel;
//    private JLabel examJLabel;

    // 服务端页面按键
    private JButton btnContact;
    private JButton btmExportHis;
    private JButton btnExportScore;
    private JButton btnProductExam;

    private Font font = new Font("楷体", Font.BOLD, 20);

    private Dimension preferredSize = new Dimension(120, 60);

    public TeacherView() {
        this.init();
        this.frameSetVisible(true);
    }

    public void init() {
        this.initFrame();
        this.initJPanel();
        this.initButton();
        this.addSubgroup();
    }

    public void initFrame() {
        frame = new JFrame("服务端");
        frame.pack();
        frame.setLayout(new FlowLayout(3,3,3));
        frame.setSize(300, 200);
        frame.setLocation(100, 400);
    }

    public void initButton() {
        btnContact = new JButton("打开连接");
        btnProductExam = new JButton("生成试卷");
        btmExportHis = new JButton("导出历史");
        btnExportScore = new JButton("导出成绩");
        // 按钮字体
        btnContact.setFont(font);
        btnProductExam.setFont(font);
        btmExportHis.setFont(font);
        btnExportScore.setFont(font);
        // 按钮大小
        btnContact.setPreferredSize(preferredSize);
        btnProductExam.setPreferredSize(preferredSize);
        btmExportHis.setPreferredSize(preferredSize);
        btnExportScore.setPreferredSize(preferredSize);
        // 按钮 id
        btnContact.setName("ID1");
        btnProductExam.setName("ID2");
//        btmExportHis.setName("ID3");
//        btnExportScore.setName("ID3");
    }

    public void initJPanel() {
        pContact = new JPanel();
        pProductExam = new JPanel();
        pExport = new JPanel();
        pExportSco=new JPanel();
        timeStartJPanel = new JPanel();
    }

    public void addSubgroup() {
        // 连接
        pContact.add(btnContact);
        // 试卷
        pProductExam.add(btnProductExam);
        // 导出
        pExport.add(btmExportHis);
        pExportSco.add(btnExportScore);

        // 3.面板加入容器
        frame.add(pContact);
        frame.add(timeStartJPanel);
        frame.add(pProductExam);
        frame.add(pExport);
        frame.add(pExportSco);
    }

    public void frameSetVisible(Boolean statu) {
        frame.setVisible(statu);
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JPanel getpContact() {
        return pContact;
    }

    public void setpContact(JPanel pContact) {
        this.pContact = pContact;
    }

    public JPanel getpProductExam() {
        return pProductExam;
    }

    public void setpProductExam(JPanel pProductExam) {
        this.pProductExam = pProductExam;
    }

    public JPanel getpExport() {
        return pExport;
    }

    public void setpExport(JPanel pExport) {
        this.pExport = pExport;
    }

    public JPanel getTimeStartJPanel() {
        return timeStartJPanel;
    }

    public void setTimeStartJPanel(JPanel timeStartJPanel) {
        this.timeStartJPanel = timeStartJPanel;
    }

    public JButton getBtnContact() {
        return btnContact;
    }

    public void setBtnContact(JButton btnContact) {
        this.btnContact = btnContact;
    }


    public JButton getBtnProductExam() {
        return btnProductExam;
    }

    public void setBtnProductExam(JButton btnProductExam) {
        this.btnProductExam = btnProductExam;
    }

    public Dimension getPreferredSize() {
        return preferredSize;
    }

    public void setPreferredSize(Dimension preferredSize) {
        this.preferredSize = preferredSize;
    }

}

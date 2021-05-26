package Examsystem.view.sever;

import Examsystem.bean.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StuListView {
    private List<Student> stu;
    private JFrame frame = new JFrame();
    private JPanel contentPane;
    private DefaultTableModel model = null;
    private JTable table = null;
    private JScrollPane scrollPane  = null;

    public StuListView(List<Student> stu) {
        this.stu = stu;
        this.init();
    }

    /*初始化试卷*/
    public void init() {
        this.initJFrame();
        this.initContentPane();
        this.initTable();
    }

    /*初始化面板*/
    public void initJFrame() {
        frame.pack();
        frame.setBounds(1000, 100, 450, 300);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /*初始化容器*/
    public void initContentPane() {
        contentPane = new JPanel();
    }

    /*初始化表格*/
    public void initTable() {
        String[][] datas = {};
        String[] titles = {  "姓名","准考证号", "性别", "登录状态"};
        model = new DefaultTableModel(datas, titles);
        table = new JTable(model);

        for (int i=0;i<this.stu.size();i++)
        {
            String stuNum = this.stu.get(i).getStuNum();
            String stuName = this.stu.get(i).getStuName();
            String stuGender = this.stu.get(i).getStuGender();
            model.addRow(new String[]{stuName,stuNum,  stuGender,"未登录"});
        }
        table.getTableHeader().setForeground(Color.BLACK);
        table.getTableHeader().setFont(new Font("楷体", Font.BOLD, 19));  // 设置表头名称字体样式
        // 第一列列宽设置为40
        table.getColumnModel().getColumn(0).setPreferredWidth(40);
        // 设置滚动面板视口大小（超过该大小的行数据，需要拖动滚动条才能看到）
        table.setPreferredScrollableViewportSize(new Dimension(600, 500));
        table.setFont(new Font("楷体", Font.PLAIN, 18));
        table.setRowHeight(35);
        // 添加滚动条
        scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane);
        frame.setContentPane(contentPane);
        frame.setLocationRelativeTo(null);
        /*
         * 字居中显示设置
         */
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, r);
        frame.setSize(800, 600);
    }
    public void render(String str) {
        for (int i = 0; i < stu.size(); i++) {
            if (stu.get(i).getStuName().equals(str)) {
                table.setValueAt("已登录", i, 3);
                table.repaint();
                return;
            }
        }
    }
}

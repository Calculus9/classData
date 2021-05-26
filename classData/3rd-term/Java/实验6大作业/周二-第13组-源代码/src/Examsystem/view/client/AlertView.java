package Examsystem.view.client;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/*考生须知*/
public class AlertView extends JFrame {
    private JFrame prepareJFrame = new JFrame("考生须知");
    private JPanel contentPane;
    private JButton btnNewButton = new JButton("\u8FDB\u5165\u8003\u8BD5");
    private String timeDurFromServer = null;
    public AlertView(String timeDurFromServer) {
        this.timeDurFromServer = timeDurFromServer;
        this.init();
        this.addSubgroup();
    }

    public void init() {
        this.initPrepareJFrame();
        this.initContentPane();
    }

    public void addSubgroup() {
        this.addLable();
        this.addToFrame();
    }

    public void addToFrame() {
        prepareJFrame.add(contentPane);
    }

    public void initPrepareJFrame() {
        prepareJFrame.pack();
        prepareJFrame.setLocation(700, 400);
        prepareJFrame.setBounds(100, 100, 450, 800);
    }

    public void initContentPane() {
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
    }
    public void addLable() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 1300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("\u9762\u5411\u5BF9\u8C61Java\u7A0B\u5E8F\u8BBE\u8BA1\u8003\u8BD5\u987B\u77E5");
        lblNewLabel.setFont(new Font("楷体", Font.BOLD, 20));
        lblNewLabel.setBounds(60, 13, 372, 28);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("\u8003\u8BD5\u89C4\u5219");
        lblNewLabel_1.setFont(new Font("楷体", Font.BOLD, 18));
        lblNewLabel_1.setBounds(14, 39, 105, 28);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 =new JLabel("<html>一、考生凭姓名、学号和准考证号登陆考生界面。其中准考证号为学号后4位。</html>");
        lblNewLabel_2.setFont(new Font("楷体", Font.BOLD, 18));
        lblNewLabel_2.setBounds(10, 68, 400, 50);
        contentPane.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("<html>\u4E8C\u3001\u51ED\u300A\u51C6\u8003\u8BC1\u300B\u548C\u8EAB\u4EFD\u8BC1\u3001\u6237\u53E3\u672C\u7B49\u6709\u6548\u8EAB\u4EFD\u8BC1\u4EF6\uFF0C\u6309\u89C4\u5B9A\u65F6\u95F4\u548C\u5730\u70B9\u53C2\u52A0\u8003\u8BD5\u3002</html>");
        lblNewLabel_3.setFont(new Font("楷体", Font.BOLD, 18));
        lblNewLabel_3.setBounds(10, 112, 400, 50);
        contentPane.add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("<html>\u4E09\u3001\u8003\u751F\u5165\u573A\u540E\uFF0C\u5BF9\u53F7\u5165\u5EA7\uFF0C\u5C06\u300A\u51C6\u8003\u8BC1\u300B\u3001\u8EAB\u4EFD\u8BC1\u7B49\u8BC1\u4EF6\u653E\u5728\u684C\u5B50\u4E0A\u4EE5\u4FBF\u6838\u9A8C\u3002\u8003\u751F\u9886\u5230\u7B54\u9898\u5361\u540E\uFF0C\u5E94\u5728\u6307\u5B9A\u4F4D\u7F6E\u7ACB\u5373\u51C6\u786E\u6E05\u695A\u5730\u586B\u5199\u59D3\u540D\u3001\u51C6\u8003\u8BC1\u53F7\u548C\u7C98\u8D34\u6761\u5F62\u7801\u3002\u51E1\u6F0F\u586B\u3001\u9519\u586B\u6216\u5B57\u8FF9\u4E0D\u6E05\u3001\u4E0D\u6309\u8981\u6C42\u7C98\u8D34\u6761\u5F62\u7801\u7684\u7B54\u9898\u5361\u89C6\u4E3A\u65E0\u6548\uFF0C\u8D23\u4EFB\u7531\u8003\u751F\u81EA\u5DF1\u8D1F\u8D23\u3002</html>");
        lblNewLabel_4.setFont(new Font("楷体", Font.BOLD, 18));
        lblNewLabel_4.setBounds(10, 144, 400, 150);
        contentPane.add(lblNewLabel_4);

        JLabel lblNewLabel_5 = new JLabel("<html>\u56DB\u3001\u8BF7\u786E\u4FDD\u826F\u597D\u7684\u8003\u8BD5\u73AF\u5883,\u5982\u9047\u65AD\u7535\u65AD\u7F51\u60C5\u51B5,\u53EF\u91CD\u65B0\u767B\u5F55\u8003\u8BD5,\" + \"\u4F46\u8003\u8BD5\u65F6\u95F4\u4E0D\u4E88\u8865\u507F\u3002</html>");
        lblNewLabel_5.setFont(new Font("楷体", Font.BOLD, 18));
        lblNewLabel_5.setBounds(10, 231, 400, 150);
        contentPane.add(lblNewLabel_5);

        JLabel lblNewLabel_6 = new JLabel("\u9898\u578B:\u9009\u62E9\u9898(20\u9053\uFF0C\u6BCF\u98982\u5206\uFF0C\u5171\u8BA140\u5206)");
        lblNewLabel_6.setFont(new Font("仿宋", Font.PLAIN, 18));
        lblNewLabel_6.setBounds(14, 345, 400, 20);
        contentPane.add(lblNewLabel_6);

        JLabel lblNewLabel_7 = new JLabel("\u586B\u7A7A\u9898(20\u9898\uFF0C\u6BCF\u98982\u5206\uFF0C\u5171\u8BA140\u5206)");
        lblNewLabel_7.setFont(new Font("仿宋", Font.PLAIN, 18));
        lblNewLabel_7.setBounds(60, 379, 400, 20);
        contentPane.add(lblNewLabel_7);

        JLabel lblNewLabel_8 = new JLabel("\u5224\u65AD\u9898(10\u9053\uFF0C\u6BCF\u98982\u5206\uFF0C\u5171\u8BA120\u5206)");
        lblNewLabel_8.setFont(new Font("仿宋", Font.PLAIN, 18));
        lblNewLabel_8.setBounds(60, 417, 400, 18);
        contentPane.add(lblNewLabel_8);

        btnNewButton.setFont(new Font("华文楷体", Font.BOLD, 20));
        btnNewButton.setBounds(124, 449, 185, 48);
        btnNewButton.setName("二.进入考试");
        btnNewButton.setBackground(Color.decode("#415d9e"));
        contentPane.add(btnNewButton);
    }

    public JFrame getPrepareJFrame() {
        return prepareJFrame;
    }

    public AlertView setPrepareJFrame(JFrame prepareJFrame) {
        this.prepareJFrame = prepareJFrame;
        return this;
    }

    @Override
    public JPanel getContentPane() {
        return contentPane;
    }

    public AlertView setContentPane(JPanel contentPane) {
        this.contentPane = contentPane;
        return this;
    }

    public JButton getBtnNewButton() {

        return btnNewButton;
    }

    public AlertView setBtnNewButton(JButton btnNewButton) {
        this.btnNewButton = btnNewButton;
        return this;
    }
}

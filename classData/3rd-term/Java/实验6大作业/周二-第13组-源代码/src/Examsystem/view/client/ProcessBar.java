package Examsystem.view.client;

import javax.swing.*;
import java.awt.*;

/*进度条*/
public class ProcessBar implements Runnable{
    private PaperView paperView = null;
    private JFrame frame;
    private int amount = 0;
    private String name;
    private JProgressBar bar;
    private JLabel label = new JLabel();
    private JPanel panel = null;
    public ProcessBar(PaperView paperView, String name){
        this.paperView = paperView;
        this.name=name;
        this.init();
    }

    public void init() {
        frame = new JFrame();
        panel = new JPanel();
        frame.setBounds(400, 100, 500, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(null);
        bar = new JProgressBar(0, 50);
        bar.setForeground(Color.lightGray);
        bar.setBounds(1, 100, 480, 50);
        bar.setValue(0);
        bar.setStringPainted(true);
        panel.add(bar);
        label = new JLabel("试卷加载中");
        label.setFont(new Font("宋体", Font.BOLD, 20));
        label.setBounds(80, 10, 150, 50);
        panel.add(label);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    public void start() {
        new Thread( this).start();
    }
    @Override
    public void run() {
        while (true) {
            System.out.println(amount);
            if (this.amount >= this.paperView.totCnt) {
                this.paperView.frame.setVisible(true);
                frame.setVisible(false);
//                this.paperView.draw(name);
                this.paperView.countDown();
                break;
            }
            bar.setValue(this.amount * 100 / this.paperView.totCnt);
            panel.updateUI();
            try {
                System.out.println(++amount);
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

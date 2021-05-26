package testExam;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.server.UID;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.text.html.HTMLDocument.HTMLReader.BlockAction;

public class LoadingWindow implements Runnable {
	private RenderPaper renderPaper = null;
	private JFrame frame;
	public int amount=0;
	int tmp=0;
	private JProgressBar bar;
	private JLabel label = new JLabel();
	private JPanel panel = null;
	private JPanel jp = null;

	public LoadingWindow(RenderPaper renePaper) {
		// TODO Auto-generated constructor stub
		this.renderPaper =renePaper;
		this.init();
//		this.start();
	}

	public void start() {
		new Thread(this).start();
	}
	public void init() {
		frame = new JFrame();
		panel = new JPanel();
        frame.setBounds(400, 100, 500, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(null);
		bar = new JProgressBar(0,this.renderPaper.totCnt);
		bar.setForeground(Color.lightGray);
		bar.setBounds(1, 100, 480, 50);
		bar.setValue(0);
//		bar.setUI(Dialog);
		bar.setStringPainted(true);
		panel.add(bar);
		label = new JLabel();
		label.setFont(new Font("宋体", Font.BOLD, 20));
		label.setBounds(0, 0, 150, 50);
		panel.add(label);
		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}

	@Override
	public void run() {
		while(true)
		{
			System.out.println(amount);
			if(this.amount>=this.renderPaper.totCnt)break;
			try {
//				System.out.println(amount);
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			bar.setValue(this.amount*100/this.renderPaper.totCnt);
			panel.updateUI();	
		}
		startExam();
	}
	public void startExam()
	{
		Button button=new Button("开始考试");
		button.setBounds(190, 10, 100, 50);
		button.setFont(new Font("宋体", Font.BOLD, 20));
		panel.add(button);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.setVisible(false);
				renderPaper.frame.setVisible(true);
				renderPaper.countDown();
			}
		});
	}
}

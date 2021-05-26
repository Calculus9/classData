package testExam;


import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.util.Timer;
import java.util.TimerTask;

public class CountDown {
    // ��¼����ʱ��ʱ���ʱ���֣���
    private long hour;
    private long minute;
    private long second;
    // ����ʣ���ʱ��(��λ: ��)
    private long totalSec;
    private long tempTotalSec;
    private JPanel jp;
    // �رս�����ʱ��
    private boolean isClose = false;
    private JLabel label1 = null;
    private RenderPaper renderPaper = null;
    public CountDown() {
    }

    public CountDown(JPanel jp, long totalSec, RenderPaper renderPaper) {
        this.renderPaper = renderPaper;
        this.totalSec = totalSec;
        this.tempTotalSec = totalSec;
        this.jp = jp;
        // ������ǩ�����ڸñ�ǩ����ʾ���Խ���ʱ��
        label1 = new JLabel();
        label1.setFont(new Font("����", Font.BOLD, 20));
    }

    public void execute(int x, int y) {
        Timer timer = new Timer();
        label1.setBounds(x, y, 1000, 100);
        label1.setForeground(Color.BLUE);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
            	if(isClose) {
            		timer.cancel();
            	}
                if(tempTotalSec >= 0) {
                    String s = changeFormat(tempTotalSec);                    
                    label1.setText(s);
                    jp.add(label1);
                    jp.updateUI();
                    tempTotalSec--;
                } else {
                	renderPaper.submit.doClick();
                    timer.cancel();
                }
            }
        }, 0, 1000);
    }

    /**
     * ��ʣ�������ת���ɶ�Ӧ��ʽ���ַ���
     * @param totalSec ʣ�������
     * @return ���������ʾ���ַ���
     */
    public  String changeFormat(long totalSec) {
        hour = totalSec / 60 / 60 % 60;
        minute = totalSec / 60 % 60;
        second = totalSec % 60;
        return "���뿼�Խ���ʱ�仹��: " + String.format("%02d", hour) + ":" + String.format("%02d", minute) + ":" + String.format("%02d", second) + " ��";
    }
    /**
     * �رն�ʱ��
     */
    public void stop() {
    	isClose = true;
    }
    /**
     * ��������
     */
    public void reset() {
    	tempTotalSec = totalSec;
    	label1.setText("");
    }
}

package testExam;


import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.util.Timer;
import java.util.TimerTask;

public class CountDown {
    // 记录倒计时的时间的时，分，秒
    private long hour;
    private long minute;
    private long second;
    // 考试剩余的时间(单位: 秒)
    private long totalSec;
    private long tempTotalSec;
    private JPanel jp;
    // 关闭结束定时器
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
        // 创建标签，并在该标签中显示考试结束时间
        label1 = new JLabel();
        label1.setFont(new Font("宋体", Font.BOLD, 20));
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
     * 将剩余的秒数转化成对应格式的字符串
     * @param totalSec 剩余的秒数
     * @return 在面板中显示的字符串
     */
    public  String changeFormat(long totalSec) {
        hour = totalSec / 60 / 60 % 60;
        minute = totalSec / 60 % 60;
        second = totalSec % 60;
        return "距离考试结束时间还有: " + String.format("%02d", hour) + ":" + String.format("%02d", minute) + ":" + String.format("%02d", second) + " 秒";
    }
    /**
     * 关闭定时器
     */
    public void stop() {
    	isClose = true;
    }
    /**
     * 重置数据
     */
    public void reset() {
    	tempTotalSec = totalSec;
    	label1.setText("");
    }
}

package Examsystem.view.client;


import javax.swing.*;
import java.awt.*;
import java.util.Date;
/*
* 倒计时：传送结束时刻的时间点，将其与当前时间点相减得到剩余时间
* */
public class CountDown {
    private long hour;
    private long minute;
    private long second;
    private JPanel jp;

    private JLabel label1 = null;
    private PaperView renderPaper = null;
    public CountDown() {
    }

    public CountDown(JPanel jp, PaperView renderPaper) {
        this.renderPaper = renderPaper;
        this.jp = jp;
        label1 = new JLabel();
        label1.setFont(new Font("宋体", Font.BOLD, 20));
    }

    public void execute(int x, int y, int endHour ,int endMin ,int endSec) {
        System.out.println(endHour+":"+endMin+":"+endSec);
        label1.setBounds(x, y, 1000, 100);
        label1.setForeground(Color.BLUE);
        long end=(endHour-0)*3600+(endMin)*60+(endSec);
        while(end>new Date(System.currentTimeMillis()).getHours()*3600+new Date(System.currentTimeMillis()).getMinutes()*60+new Date(System.currentTimeMillis()).getSeconds())
        {
            long currentTime = System.currentTimeMillis();
            Date now = new Date(currentTime);
            int nowH=now.getHours();
            int nowM=now.getMinutes();
            int nowS=now.getSeconds();
            long delta=(endHour-nowH)*3600+(endMin-nowM)*60+(endSec-nowS);
//            System.out.println("delta:"+delta);
            String s = changeFormat(delta);
            label1.setText(s);
            jp.add(label1);
            jp.updateUI();
        }
    }
    public String changeFormat(long totalSec) {
        hour = totalSec / 60 / 60 % 60;
        minute = totalSec / 60 % 60;
        second = totalSec % 60;
        if (hour==0&& minute==0&&second==1)
        {
            renderPaper.frame.setVisible(false);
            renderPaper.submit.doClick();
        }
        return "距离考试结束还有： " + String.format("%02d", hour) + ":" + String.format("%02d", minute) + ":" + String.format("%02d", second) + ".";
    }
}
package Examsystem.view.client;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
/*
 * 当登录，准备页面，答题页面三者任意一个窗口关闭，此时服务端与客户端的连接断掉

 */
public class MyWindowListener implements WindowListener  {

    public MyWindowListener(){

    }
    public void windowOpened(WindowEvent e) {
        System.out.println("窗体打开");
    }
    public void windowClosing(WindowEvent e) {
        System.out.println("窗体关闭exit");
        JOptionPane.showMessageDialog(null, "<html><font size=5> 您已离线，请尝试重新连接</html>");
    }
    public void windowClosed(WindowEvent e) {
        System.out.println("窗体关闭dispose");
        JOptionPane.showMessageDialog(null, "<html><font size=5> 您已离线，请尝试重新连接</html>");
    }
    public void windowIconified(WindowEvent e) {
        System.out.println("窗体最小化");
    }
    public void windowDeiconified(WindowEvent e) {
        System.out.println("窗体最小化恢复正常");
    }
    public void windowActivated(WindowEvent e) {
        System.out.println("窗体激活");
    }
    public void windowDeactivated(WindowEvent e) {
        System.out.println("窗体非激活");
    }
}

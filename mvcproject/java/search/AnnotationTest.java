package search;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @描述 : 
 * @创建者 : guoxiaopeng
 * @创建时间 : 2017-7-28 上午9:59:23
 */
public class AnnotationTest {

    private JFrame mainwin=new JFrame("使用注解绑定事件监听器");
    @ActionListennerFor(listener=OkListenner.class)
    private JButton ok=new JButton("确定");
    @ActionListennerFor(listener=CancelListenner.class)
    private JButton cancel=new JButton("取消");

    public void init(){
        JPanel jp=new JPanel();
        jp.add(ok);
        jp.add(cancel);
        mainwin.add(jp);
        ActionListenerInstaller.processAnnotations(this);
        mainwin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainwin.pack();
        mainwin.setVisible(true);
        
    }
    public static void main(String[] args) {
        new AnnotationTest().init();
    }
    class OkListenner implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "单击了确认按钮");
        }
    }
    class CancelListenner implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "单击了取消按钮");
        }
    }
}


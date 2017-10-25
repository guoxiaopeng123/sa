package search;

import java.awt.event.ActionListener;
import java.lang.reflect.Field;

import javax.swing.AbstractButton;

/**
 * @描述 : 
 * @创建者 : guoxiaopeng
 * @创建时间 : 2017-7-28 上午10:35:24
 */
public class ActionListenerInstaller {
    static void processAnnotations(Object obj){
        try {
            Class clz=obj.getClass();
            for (Field f:clz.getDeclaredFields()) {
                //将该成员变量设置成可自由访问
                f.setAccessible(true);
                //获取该成员变量上ActionListenerFor类型的annotataion
                ActionListennerFor a = f.getAnnotation(ActionListennerFor.class);
                Object object = f.get(obj);
                if(a!=null && object!=null &&object instanceof AbstractButton){
                   //获取a注解里的listener元数据(它是一个监听器类)
                    Class<? extends ActionListener> listener = a.listener();
                    //使用反射来创建listener类的对象
                    ActionListener newInstance = listener.newInstance();
                    AbstractButton ab=(AbstractButton) object;
                    ab.addActionListener(newInstance);
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}

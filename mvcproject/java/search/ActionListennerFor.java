package search;

import java.awt.event.ActionListener;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @描述 : 
 * @创建者 : guoxiaopeng
 * @创建时间 : 2017-7-28 上午9:55:11
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ActionListennerFor {
    
    //定义一个成员变量，用于设置元数据
    //该listener成员变量用于保存监听器实现类
    Class <? extends ActionListener>  listener();
}

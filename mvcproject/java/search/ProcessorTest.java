package search;

import java.lang.reflect.Method;

/**
 * @描述 :   注解处理工具
 * @创建者 : guoxiaopeng
 * @创建时间 : 2017-7-28 上午9:14:45
 */
public class ProcessorTest {

    public static void process(String clazz) throws SecurityException, ClassNotFoundException{
        
        int passed=0;
        int failed=0;
        for(Method m:Class.forName(clazz).getMethods()){
            //如果该方法使用了Testable修饰
            if(m.isAnnotationPresent(Testable.class)){
                try {
                    //调用m方法
                    m.invoke(null);
                    passed++;
                } catch (Exception e) {
                    // TODO: handle exceptioln("方法"+m+"运行失败，异常："+ " " +e.getMessage());
                    failed++;
                }
            }
        }
        System.out.println("========================================");
        System.out.println(passed);
        System.out.println(failed);
    }
}

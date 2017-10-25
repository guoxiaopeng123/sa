package search;


/**
 * @描述 : 
 * @创建者 : guoxiaopeng
 * @创建时间 : 2017-7-28 上午9:17:10
 */
public class MyTest {

    @Testable
    public static void m1(){
        
    }
    public static void m2(){
        
    }
    @Testable
    public static void test3(){
        throw new IllegalArgumentException("参数出错了！");
    }
    public static void m4(){
        
    }
    @Testable
    public static void m5(){
        
    }
    public static void m6(){
        
    }
    @Testable
    public static void m7(){
        throw new RuntimeException("程序业务出现异常");
    }
    public static void m8(){
        
    }
    
}

/**
 * @描述 : 
 * @创建者 : guoxiaopeng
 * @创建时间 : 2017-9-21 下午3:18:57
 */
public class test {

    public static void main(String[] args) {
        String root="com/prosay/controller";
        String path="D:/work/taotao/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/mvcproject/WEB-INF/classes/com/prosay/controller/IndexController.class";
        System.out.println(path.substring(path.lastIndexOf("/"),path.indexOf(".class")));
        String str = path.substring(path.lastIndexOf("/"),path.indexOf(".class"));
        String className=root+str;
        System.out.println(className);
        className = className.replace("/", ".");
        System.out.println(className);
    }
}

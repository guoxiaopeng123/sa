package mvc.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.prosay.controller.IndexController;

import mvc.annotation.Controller;
import mvc.annotation.RequestMapping;
import mvc.util.ScannerUtil;
/**
 * @描述 : 核心控制器
 * @创建者 : guoxiaopeng
 * @创建时间 : 2017-9-21 上午11:41:14
 */
@WebServlet(urlPatterns={"*.do"},loadOnStartup=0)
public class DispatcherServlet extends HttpServlet{

    /**
     * @描述 : 
     * @创建者 : guoxiaopeng
     * @创建时间 : 2017-9-21 下午2:38:31
     */
    private static final long serialVersionUID = 1L;
    private final static String BASE_PACAKGE="com.prosay.controller";
    //声明两个集合，分别存储Controller实例  以及每个映射路径对应的Methond
    private Map<String,Object> controllers=new HashMap<String, Object>();
    //被反射调用的Methond对象
    private Map<String,Method> methods=new HashMap<String, Method>();
    public void init(){
        System.out.println("开始扫描："+BASE_PACAKGE+"下面的所有Controller ");
        Map<String, Class<?>> classes = ScannerUtil.scannerClass(BASE_PACAKGE);
        Iterator<String> ito = classes.keySet().iterator();
        while(ito.hasNext()){
            String className = ito.next();
            Class c= classes.get(className);
            String path="";
            if(c.isAnnotationPresent(Controller.class)){
                path=((RequestMapping)c.getAnnotation(RequestMapping.class)).value();
            }
            try {
                controllers.put(className, c.newInstance());
                IndexController controller = (IndexController) c.newInstance();
                Method[] ms = c.getMethods();
                for(Method m:ms){
                    if(m.isAnnotationPresent(RequestMapping.class)){
                      String methodPath= ((RequestMapping)m.getAnnotation(RequestMapping.class)).value();
                        methods.put(path+methodPath, m);
                    }
                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        System.out.println("扫描结束：扫描到"+classes.size()+"个类");
    }
    @SuppressWarnings("unused")
    public void service(HttpServletRequest request,HttpServletResponse response){
        Iterator<Entry<String, Method>> iterator = methods.entrySet().iterator();
        String contextPath=request.getContextPath();
        String url= request.getRequestURI();
        if(url.equals(contextPath+"/")){
            url=url+"index.do";
        }
        String mappingPath=url.substring(url.indexOf(contextPath)+contextPath.length(),url.indexOf(".do"));
        Method method = methods.get(mappingPath);
        System.out.println(method.getDeclaringClass().getName());
        try {
            method.invoke(controllers.get(method.getDeclaringClass().getName()));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

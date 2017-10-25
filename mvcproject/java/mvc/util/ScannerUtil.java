package mvc.util;

import java.io.File;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @描述 : 
 * @创建者 : guoxiaopeng
 * @创建时间 : 2017-9-21 上午10:18:42
 */
public class ScannerUtil {

    public static void main(String[] args) {
        scannerClass("com.prosay.controller");
    }
    //扫描包mvc
    public static Map<String,Class<?>> scannerClass(String packagePath){
       String filePath=packagePath.replace(".","/");
//       System.out.println(filePath);
       Map<String,Class<?>> classes=new HashMap<String, Class<?>>(); 
       try {
        Enumeration<URL> dirs = (Enumeration<URL>) Thread.currentThread().getContextClassLoader().getResources(filePath);
        String root=Thread.currentThread().getContextClassLoader().getResource(filePath).getPath();
//        System.out.println("root  "+root);
        if(root!=null){
            root=root.substring(root.lastIndexOf(filePath))+"/";
        }
//        System.out.println(root);
        while(dirs.hasMoreElements()){
            URL url = dirs.nextElement();
            System.out.println(url.toString());
            if(url.getProtocol().equals("file")){
                //通过url对象产生实际的文件对象
//               /D:/work/taotao/my-search/target/classes/com/prosay/controller
                File folder = new File(url.getPath().substring(1));
                //开始扫描文件夹（子文件夹）的所有类
                scannerFile(folder, root, classes);
            }
        }
     
       } catch (Exception e) {
        // TODO: handle exception
           e.printStackTrace();
    }
       return null;
    }
    /**
     * 
     * @描述 : 将文件夹下面的所有的类扫描出来，遇到子文件时递归调用
     * @创建者 : guoxiaopeng
     * @创建时间 : 2017-9-21 上午10:44:56
     * @param folder
     * @param root  设定文件
     * @param map   返回类型
     */
    public static void scannerFile(File folder,String root,Map <String ,Class<?>> map){
        File[] listFiles = folder.listFiles();
        for (File f:listFiles) {
            if(f.isDirectory()){
                scannerFile(f, root+f.getName()+"/", map);
            }else{
                String path= f.getAbsolutePath();
                if(path.endsWith(".class")){
                    path= path.replace("\\", "/");
//                    D:/work/taotao/my-search/target/classes/com/prosay/controller/IndexController.class
                    String className=root+path.substring(path.lastIndexOf("/")+1,path.indexOf(".class"));
                    className = className.replace("/", ".");
                    System.out.println(className);
                    try {
                        map.put(className, Class.forName(className));
                    } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

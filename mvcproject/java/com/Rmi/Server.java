package com.Rmi;
/**
 * @描述 : 
 * @创建者 : guoxiaopeng
 * @创建时间 : 2017-7-31 下午4:54:39
 */

import java.rmi.*;
import javax.naming.*;
//服务器端，一定需要预先向服务器注册一个工厂对象，以备客户的远程访问
public class Server{    
  public static void main(String [] args) {
    registerFactory();
  }
    
  //这里使用了静态方法来注册工厂对象，
  public static void registerFactory(){
    String url = "rmi://localhost/";
    try {
      IFactory factory = new RMIFactory();
      Context namingContext = new InitialContext();
      namingContext.rebind(url + "factory", factory);
      System.out.println("服务器注册了一个工厂!");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}


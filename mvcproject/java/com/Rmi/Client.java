package com.Rmi;

import javax.naming.Context;
import javax.naming.InitialContext;

/**
 * @描述 : 
 * @创建者 : guoxiaopeng
 * @创建时间 : 2017-7-31 下午4:52:04
 */
public class Client {

    public static void main(String[] args) {
        String url="rmi://localhost";
        try {
            Context namingContext = new InitialContext(); 
            //远程访问，获得工厂的存根
            IFactory factory = (IFactory)namingContext.lookup(url + "factory");
              
            IService service01 = factory.getService("service01");
            System.out.println(service01.service("访问服务器 ..."));
              
            IService service02 = factory.getService("service02");
            System.out.println(service02.service("访问服务器 ...")); 
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace(); 
        }
    }
}

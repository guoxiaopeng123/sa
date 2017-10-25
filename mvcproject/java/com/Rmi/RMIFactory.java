package com.Rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;

/**
 * @描述 :  具体的RMI工厂类
 * @创建者 : guoxiaopeng
 * @创建时间 : 2017-7-31 上午9:34:26
 */
public class RMIFactory extends UnicastRemoteObject implements IFactory {

    //Hashtable 对象作为远程对象的缓冲区
    private Hashtable <String,IService> remoteObjects;
    protected RMIFactory() throws RemoteException {
       this.remoteObjects=new Hashtable<String, IService>();
    }

    public IService getService(String name) throws RemoteException {
        IService iService = this.remoteObjects.get(name);
        if(iService==null){
            System.out.println("客户端访问的远程对象" + name + "不存在!立即创建");
            iService=new RMISercice(name);
        }else{
            System.out.println("客户端访问的远程对象" + name + "已存在");
        }
        return iService;
    }

}

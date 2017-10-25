package com.Rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @描述 : 抽象的工厂类，因为工厂类也需要被远程访问，故扩展Remote接口
 * @创建者 : guoxiaopeng
 * @创建时间 : 2017-7-31 上午9:29:17
 */
public interface IFactory extends Remote {

    public IService getService(String name) throws RemoteException;
    
}

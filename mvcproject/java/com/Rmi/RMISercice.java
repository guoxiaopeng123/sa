package com.Rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @描述 : 
 * @创建者 : guoxiaopeng
 * @创建时间 : 2017-7-31 上午9:12:34
 */
public class RMISercice extends UnicastRemoteObject implements IService {

    private String name;
    protected RMISercice(String name) throws RemoteException {
        super();
        this.name=name;
    }

    public String service(String contenst) {
        return "from " + this.name + " >> " + contenst;
    }

}

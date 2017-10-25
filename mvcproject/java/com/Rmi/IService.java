package com.Rmi;
/**
 * @描述 : 
 * @创建者 : guoxiaopeng
 * @创建时间 : 2017-7-31 上午9:03:28
 */
public interface IService {

    /**
     * 
     * @描述 :抽象的服务接口，定义远程服务的标准service方法
     * 因为是远程对象类，所以要继承标识的远程接口Remote
     * 而且远程方法要声明抛出RemoteExcetion 
     * @创建者 : guoxiaopeng
     * @创建时间 : 2017-7-31 上午9:06:58
     * @param contenst
     * @return
     */
    public String service(String contenst) ;
}

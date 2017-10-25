package com.test1;
/**
 * @描述 : 服务端
 * @创建者 : guoxiaopeng
 * @创建时间 : 2017-9-1 下午4:36:14
 */
public class Server {

    public static void main(String[] args) {
        UserService userService= new UserServiceImpl();
        try {
            RpcFramework.publish(userService, 9999);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

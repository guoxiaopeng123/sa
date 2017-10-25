package com.test1;
/**
 * @描述 : 调用客户端
 * @创建者 : guoxiaopeng
 * @创建时间 : 2017-9-1 下午4:38:04
 */
public class Client {

    public static void main(String[] args) {
        try {
            UserService userService = RpcFramework.reference(UserService.class, "127.0.0.1", 9999);
            System.out.println(userService.getNameById("100"));
        } catch (Exception e) {
            e.printStackTrace();
        }  
    }
}

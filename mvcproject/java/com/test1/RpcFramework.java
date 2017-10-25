package com.test1;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
import java.net.Socket;

public class RpcFramework {
    /**
     * 初始化一个服务端socket，一直监听客户端请求，当有请求时，依次读取 方法名称，方法参数类型，方法参数， 根据这些信息在service上找到相应的method，然后，service方法上执行对应的方法，把方法返回值传输回去
     * 
     * @param service
     *            暴露的服务接口实现类
     * @param port
     *            绑定的端口
     * @throws Exception
     */
    @SuppressWarnings("resource")
    public static void publish(final Object service, final int port) throws Exception {
        final ServerSocket server = new ServerSocket(port);
        System.out.println("rpc server is start on port : " + port);
        while (true) {
            final Socket client = server.accept();
            new Thread(new Runnable() {

                public void run() {
                    ObjectInputStream input = null;
                    ObjectOutputStream output = null;
                    try {
                        input = new ObjectInputStream(client.getInputStream());
                        output = new ObjectOutputStream(client.getOutputStream());

                        String methodName = input.readUTF();
                        Class<?>[] pamTypes = (Class<?>[]) input.readObject();
                        Object[] args = (Object[]) input.readObject();

                        Method method = service.getClass().getMethod(methodName, pamTypes);
                        Object obj = method.invoke(service, args);

                        output.writeObject(obj);
                    } catch (Throwable e) {
                        try {
                            output.writeObject(e);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    } finally {
                        closeStream(output);
                        closeStream(input);
                        closeStream(client);
                    }
                }
            }).start();
        }
    }

    /**
     * 根据host, port初始化一个socket，然后传输 方法名称，方法参数类型，方法参数，最后获取服务器返回的结果，就是方法执行的返回值
     * 
     * @param clazz
     *            调用的接口
     * @param host
     *            远程服务器
     * @param port
     *            端口
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static <T> T reference(final Class<T> clazz, final String host, final int port) throws Exception {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[] { clazz }, new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                final Socket client = new Socket(host, port);

                ObjectOutputStream output = new ObjectOutputStream(client.getOutputStream());

                /**
                 * 往服务端发送方法名称，参数类型，参数
                 */
                output.writeUTF(method.getName());
                output.writeObject(method.getParameterTypes());
                output.writeObject(args);

                ObjectInputStream input = new ObjectInputStream(client.getInputStream());

                try {
                    /**
                     * 获取服务端，方法执行的结果
                     */
                    Object res = input.readObject();
                    if (res instanceof Throwable) {
                        throw (Throwable) res;
                    }
                    return res;
                } catch (Throwable e) {
                    throw e;
                } finally {
                    closeStream(input);
                    closeStream(output);
                    closeStream(client);
                }
            }
        });
    }

    private static void closeStream(AutoCloseable stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (Exception e) {
                // ignore
            }
        }
    }
}
package com.tt.java.design.singleton;

/**
 * @Desc
 * @Author cmlx
 * @Date 2020-7-22 0022 18:51
 */
public class SingleObject {

    //创建一个SingleObject的一个对象
    private static SingleObject instance = new SingleObject();

    //让构造函数为 private，这样该类就不会被实例化
    private SingleObject() {
    }

    //获取唯一可用的对象
    public static SingleObject getInstance() {
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello World!!!");
    }
}

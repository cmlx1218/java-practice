package com.tt.java.design.singleton;

/**
 * @Desc
 * @Author cmlx
 * @Date 2020-7-24 0024 15:12
 */
public class SingleObjectDemo {


    public static void main(String[] args) {

        // 不合法的构造函数，构造函数 SingleObject() 是不可见的
        //SingleObject singleObject = new SingleObject();

        //获取唯一可用的对象
        SingleObject singleObject = SingleObject.getInstance();
        singleObject.showMessage();


    }

}

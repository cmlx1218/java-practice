package com.tt.java.design.singleton;

/**
 * @Desc 懒汉式单例模式 -- 线程安全
 * <p>
 * 优点：第一次调用才初始化，避免内存浪费
 * 缺点：必须加锁 synchronized 才能保证单例，但是影响效率
 * getInstance()的性能对应用程序不是很关键（该方法使用不太频繁）
 * @Author cmlx
 * @Date 2020-7-24 0024 15:25
 */
public class LanhanSingleton02 {

    private static LanhanSingleton02 instance;

    private LanhanSingleton02() {
    }

    public static synchronized LanhanSingleton02 getInstance() {
        if (instance == null) {
            System.out.println("02线程安全单例模式 -- 实例化对象");
            instance = new LanhanSingleton02();
        }
        return instance;
    }
}

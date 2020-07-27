package com.tt.java.design.singleton;

/**
 * @Desc 懒汉式单例模式 -- 线程不安全
 * <p>
 * 因为没有加锁，所以不支持多线程
 * @Author cmlx
 * @Date 2020-7-24 0024 15:21
 */
public class LanhanSingleton01 {

    private static LanhanSingleton01 instance;

    private LanhanSingleton01() {
    }

    public static LanhanSingleton01 getInstance() {
        if (instance == null) {
            System.out.println("01线程不安全单例模式 -- 实例化对象");
            instance = new LanhanSingleton01();
        }
        return instance;
    }

}

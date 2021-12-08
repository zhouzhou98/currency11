package com.high.currency11;

import java.util.concurrent.Callable;

public class Vegetable implements Callable {
    @Override
    public Vegetable call() throws Exception {
        System.out.println("开始买菜、洗菜");
        Thread.sleep(1000);  // 模拟买菜，洗菜时间
        System.out.println("买菜、洗菜已完成");
        return new Vegetable();
    }
}

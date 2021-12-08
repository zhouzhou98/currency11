package com.high.currency11;

import java.util.concurrent.Callable;

public class Cook implements Callable {
    @Override
    public Cook call() throws Exception {
        System.out.println("开始炒菜");
        // 模拟炒菜时间
        Thread.sleep(8000);
        Cook cook = new Cook();
        System.out.println("炒菜完毕");
        return cook;
    }
}

package com.high.currency11;

import java.util.concurrent.Callable;

public class Rice implements Callable {
    @Override
    public Rice call() throws Exception {
        System.out.println("开始煮饭");
        Thread.sleep(5000);  // 模拟煮饭时间
        System.out.println("煮饭完成");
        return new Rice();
    }
}

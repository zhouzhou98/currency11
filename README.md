问题：请用多线程基本知识完成两种情况下的煮饭、准备
菜(买菜、洗菜）、炒菜、开始吃饭的功能。1、准备菜(买菜、洗菜)：1秒，
炒菜：2秒，煮饭：5秒 2、准备菜(买菜、洗菜)：1秒，炒菜：8秒，煮饭：
5秒。说明：只有煮饭完成、准备菜、炒菜完成，才能开始吃饭，两种情况
下，在尽量短的时间内完成所有动作。  

方法：通过FutureTask + Callable + 线程状态的判断加以实现  

煮饭类  
```java
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
```
买菜洗菜类
```java
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
```
炒菜类
```java
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
```
吃饭类
```java
package com.high.currency11;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Dinner {
    public static void haveDinner() throws InterruptedException, ExecutionException {
        Rice rice = new Rice();
        FutureTask<Rice> riceTask = new FutureTask(rice);
        Thread rThread = new Thread(riceTask);
        rThread.start();

        Vegetable veg = new Vegetable();
        FutureTask vegTask = new FutureTask(veg);
        Thread vThread = new Thread(vegTask);
        vThread.start();
        vThread.join();

        Cook cook=new Cook();
        FutureTask<Cook> cookTask = new FutureTask(cook);
        new Thread(cookTask).start();

        if(!cookTask.isDone()){
            System.out.println("菜没炒好，就继续等吧");
        }
        if(!riceTask.isDone()){
            System.out.println("饭没煮好，就继续等吧");
        }

        Rice riceResult= riceTask.get();
        Cook cookResult=cookTask.get();
        if(null!=riceResult&&null!=cookResult) {
            System.out.println("菜炒好了，饭也煮好了，大家一起吃饭吧");

        }
    }
}
```
测试类
```java
package com.high.currency11;
import java.util.concurrent.ExecutionException;


public class Test {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        try {
            Dinner.haveDinner();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("整个过程，共耗时" + (System.currentTimeMillis() - startTime) + "ms");
    }

}
```
测试结果
```
开始煮饭
开始买菜、洗菜
买菜、洗菜已完成
菜没炒好，就继续等吧
饭没煮好，就继续等吧
开始炒菜
煮饭完成
炒菜完毕
菜炒好了，饭也煮好了，大家一起吃饭吧
整个过程，共耗时9009ms
```
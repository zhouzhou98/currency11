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

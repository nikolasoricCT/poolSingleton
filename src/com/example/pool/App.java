package com.example.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor implements Runnable{
    private int id;
    public Processor(int id) {
        this.id = id;
    }
    public void run() {
        System.out.println("Starting: " + id);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }
        System.out.println("Completed: " + id);
    }
}

public class App {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        for(int i=0; i<5; i++) {
            executor.submit(new Processor(i));
        }
        executor.shutdown();

        System.out.println("Start");
        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e){
        }

        System.out.println("All tasks completed.");

        Single obj = Single.getInstance();
    }
}

class Single {
    public static Single obj;
    int i;
    private Single(){
        System.out.println("called");
    }

    public static  Single getInstance(){         //
        if(obj == null) {
            synchronized (Single.class){
                if(obj == null)
                obj = new Single();
            }

        }
        return obj;
    }
}

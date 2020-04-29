package com.sundehui.test;

public class JUC {

    public static void main(String[] args) throws InterruptedException {
        Resource resource = new Resource();
        System.out.println("===================================开始执行==================================");
        Thread t1 = new Thread(() -> {
            synchronized ("new Resource()"){
                for (int i = 0; i < 100; i++) {
                    try {
                        resource.increase(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }


        }, "th_1");

        Thread t2 = new Thread(() -> {
            synchronized ("new Resource()"){
                for (int i = 0; i < 100; i++) {
                    try {
                        resource.increase(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }


        }, "th_2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();


        System.out.println("===================================执行结束==================================");
    }


}


class Resource {

    int i = 0;


     public void increase(int limit) throws InterruptedException {
        i++;
        System.out.println(Thread.currentThread().getName() + " " + i);


    }
}
package com.alred.repo.multithread.communication;

public class GyJoin {
    public static void main(String[] args) {
        System.out.println("主线成开始工作");
        System.out.println("启动工作线程");
        Thread thread = new Thread(new GyWorkThread(),"workThread");
        thread.start();
        try {
            System.out.println("主线程开始等待");
            thread.join();
            System.out.println("主线运行结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class GyWorkThread implements Runnable{

    public void run() {
        for (int i = 0; i < 5; i++) {
            String threadName = Thread.currentThread().getName();
            try {
                System.out.println(threadName+" begin to run : " + i);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

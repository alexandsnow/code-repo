package com.alred.repo.multithread.communication;

public class WaitAndNotify {
    public static void main(String[] args) {
        String shareVariable = "gaoyang";
        Thread waitThread = new Thread(new GyWaitThread(shareVariable),"waitThread");
        waitThread.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread notifyThread = new Thread(new GyNotifyThread(shareVariable),"notifyThread");
        notifyThread.start();
    }
}

class GyWaitThread implements Runnable {

    private Object lock;

    public GyWaitThread(Object lock) {
        super();
        this.lock = lock;
    }

    public void run() {
        String threadName = Thread.currentThread().getName();
        synchronized (lock){
            System.out.println(threadName+ " is waiting");
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadName + " wait end!!!");
        }
    }
}

class GyNotifyThread implements Runnable {

    private Object lock;

    public GyNotifyThread(Object lock) {
        super();
        this.lock = lock;
    }

    public void run() {
        String threadName = Thread.currentThread().getName();
        synchronized (lock){
            System.out.println(threadName+ " is begin running");
            lock.notify();
            System.out.println(threadName + " notify end!!!");
        }
    }
}
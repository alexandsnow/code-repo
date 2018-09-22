package com.alred.repo.multithread.communication;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GyCondition {
    public static void main(String[] args) {
        GyConditionService gyConditionService = new GyConditionService();
        Thread workThread = new Thread(new GyConditionWorkThread(gyConditionService),"workThread");
        workThread.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread signalThread = new Thread(new GyConditionSignalThread(gyConditionService),"signalThread");
        signalThread.start();
    }

}

class GyConditionService {


    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    public void work() {
        String threadName = Thread.currentThread().getName();
        lock.lock();
        System.out.println(threadName + " do business operation");
        try {
            System.out.println(threadName + " begin wait");
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(threadName + " end wait");
        lock.unlock();
    }

    public void signal(){
        lock.lock();
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " begin notify");
        condition.signal();
        System.out.println(threadName + " end notify");
        lock.unlock();
    }
}

class GyConditionWorkThread implements Runnable {

    private GyConditionService gyConditionService;

    public GyConditionWorkThread(GyConditionService gyConditionService) {
        this.gyConditionService = gyConditionService;
    }

    public void run() {
        gyConditionService.work();
    }
}

class GyConditionSignalThread implements Runnable {

    private GyConditionService gyConditionService;

    public GyConditionSignalThread(GyConditionService gyConditionService) {
        this.gyConditionService = gyConditionService;
    }

    public void run() {
        gyConditionService.signal();
    }
}

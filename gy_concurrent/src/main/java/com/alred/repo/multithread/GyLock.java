package com.alred.repo.multithread;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁 ReentrantLock
 */
public class GyLock extends Thread {
    private static final Logger logger = LogManager.getLogger(GyLock.class);

    private DrawUtil drawUtil;

    public GyLock(DrawUtil drawUtil) {
        this.drawUtil = drawUtil;
    }

    @Override
    public void run() {
//        logger.info(Thread.currentThread().getName()+" begin run at : "+System.currentTimeMillis());
        this.drawUtil.showObjectLockThread();
//        logger.info(Thread.currentThread().getName()+" end!"+ System.currentTimeMillis());
    }
}

class DrawUtil{
    private static final Logger logger = LogManager.getLogger(DrawUtil.class);
    private Lock lock = new ReentrantLock();

    public void showObjectLockThread() {
        lock.lock();
        String threadName = Thread.currentThread().getName();
        try {
            Thread.sleep(5000);
            logger.info(threadName + " get lock at: "+System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info(threadName+" release lock at: "+System.currentTimeMillis());

        lock.unlock();
    }
}

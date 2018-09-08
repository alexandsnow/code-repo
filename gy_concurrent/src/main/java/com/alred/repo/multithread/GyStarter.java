package com.alred.repo.multithread;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.CountDownLatch;

public class GyStarter {

    private static final Logger logger = LogManager.getLogger(GyStarter.class);

    public static void main(String[] args) {
        logger.info("main thread begin run!");
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            GyWorker gyWorker = new GyWorker("" + i, "task:" + i, countDownLatch);
            new Thread(gyWorker).start();
        }
        try {
            logger.info("main thread is waiting !!!!!");
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("main thread run over!!!");
    }
}

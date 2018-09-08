package com.alred.repo.multithread;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.CountDownLatch;

/*处理耗时任务*/
public class GyWorker implements Runnable {


    private static final Logger logger = LogManager.getLogger(GyWorker.class);
    String taskId;
    String taskName;
    CountDownLatch countDownLatch;

    public GyWorker(String taskId, String taskName, CountDownLatch countDownLatch) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.countDownLatch = countDownLatch;
    }

    public void run() {
        logger.info("begin run task id : " + taskId + " ,name: " + taskName);
        doSomething(this.taskId);
        this.countDownLatch.countDown();
        logger.info("task " + this.taskName + " end!");
    }

    private void doSomething(String taskId) {
        try {
            Thread.sleep(10000);
            logger.info("do something: " + taskId);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

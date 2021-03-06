package com.ruubypay.rocketmq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author leijiang
 */
public class MainClass {

    private static CountDownLatch latch = new CountDownLatch(1);
    private static Logger logger = LoggerFactory.getLogger(MainClass.class);

    public static void main(String[] args) throws IOException, InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"config-init.xml"});
        context.registerShutdownHook();
        context.start();

        latch.await();
    }
}

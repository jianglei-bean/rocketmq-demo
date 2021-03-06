package com.ruubypay.rocketmq.consumer.aop;

import com.ruubypay.rocketmq.consumer.util.CommonUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class ExtensionAop {
    private static final Logger logger = LoggerFactory.getLogger(ExtensionAop.class);

    /**
     * 方法调用之前调用
     * @param joinPoint 切点
     */
    public void doBefore(JoinPoint joinPoint){
        //添加日志打印
        logger.info("@Before:解析参数");
        MDC.put("requestId", CommonUtil.getUUID());
    }

    /**
     * 方法之后调用
     * @param joinPoint 切点
     * @param returnValue 方法返回值
     */
    public void doAfterReturning(JoinPoint joinPoint){
        // 处理完请求，清除日志
        logger.info("@AfterReturning:解析参数");
        MDC.clear();
    }

    /**
     * 统计方法执行耗时Around环绕通知
     * @param joinPoint 切点
     * @return 返回执行结果对象
     */
    public Object timeAround(ProceedingJoinPoint joinPoint) {
        logger.info("@Around:解析参数");

        Object obj = null;
        try {
            obj = joinPoint.proceed();
        } catch (Throwable e) {
            logger.error("@Around :统计某方法执行耗时环绕通知出错", e);
        }
        // 获取执行结束的时间
        return obj;
    }
}

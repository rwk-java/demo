package com.baizhi.cache;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component      // 交由工厂管理
@Aspect         // 声明这是一个切面
public class RedisAopCache {
    @Autowired
    private RedisTemplate redisTemplate;
    @Around("@annotation(com.baizhi.annotation.AddCache)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 判断是否有缓存 如果有直接返回
        HashOperations hashOperations = redisTemplate.opsForHash();
        // 获取目标对象的名字
        String namespace = proceedingJoinPoint.getTarget().getClass().getName();
        // 获取方法名
        String name = proceedingJoinPoint.getSignature().getName();
        // 获取参数
        Object[] args = proceedingJoinPoint.getArgs();
        // 将方法名和参数拼接 作为小key
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name);
        for (Object arg : args) {
            stringBuilder.append(arg.toString());
        }
        if (hashOperations.hasKey(namespace, stringBuilder)){
            System.out.println("获取缓存");
            Object o = hashOperations.get(namespace, stringBuilder);
            return o;
        }
        System.out.println("添加缓存");
        Object proceed = proceedingJoinPoint.proceed();
        hashOperations.put(namespace, stringBuilder, proceed);
        return proceed;
    }
    @After("@annotation(com.baizhi.annotation.ClearCache)")
    public void after(JoinPoint joinPoint){
        System.out.println("清除缓存");
        String name = joinPoint.getTarget().getClass().getName();
        redisTemplate.delete(name);
    }
}

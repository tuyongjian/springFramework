package com.tu.service.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @Auther: tuyongjian
 * @Date: 2019/11/1 10:00
 * @Description:切面类
 */
@Aspect
@Component
public class AspectJ {

    //定义切点
    @Pointcut("execution(* com.tu.service.aop.IAopService.buy(..))")
    public void myPointCut(){

    }

    /**
     * @Before("myPointCut() && args(gift)")
     * Before指定通知类型 myPointCut() 是上面定义的切点
     * args(gift)是我们指定的参数，可以多个也可以不要参数
     *
     * @param joinPoint
     * @param gift
     */

    //定义通知类型
    @Before("myPointCut() && args(gift)")
    public void myBefore(JoinPoint joinPoint,String gift){
        System.out.println("前置通知："+joinPoint);
        System.out.println("被植入增强处理的目标方法为："+joinPoint.getSignature().getName());
        System.out.println("前置通知接收的参数为："+gift);
    }

    @AfterReturning("myPointCut()  && args(gift)")
    public void myAfterReturning(JoinPoint joinPoint,String gift){
        System.out.println("后置通知："+joinPoint);
        System.out.println("被植入增强处理的目标方法为："+joinPoint.getSignature().getName());
        System.out.println("后置通知接收的参数为："+gift);
    }

    @Around("myPointCut() && args(gift)")
    public Object  myAround(ProceedingJoinPoint proceedingJoinPoint,String gift) throws Throwable {
        System.out.println("环绕开始。。。");
        Object obj = proceedingJoinPoint.proceed();
        System.out.println("环绕通知接收的参数为："+gift);
        if(gift.equals("aa")){
            System.out.println("");
        }
        System.out.println("环绕结束。。。");
        return obj;
    }

    //异常通知，如果切入点的方法报错则会进入到这个地方
    @AfterThrowing(value="myPointCut() && args(gift)",throwing="e")
    public void myAfterThrowing(JoinPoint joinpoint,String gift,Throwable e){
        System.out.println("异常通知："+e.getMessage());
        System.out.println("异常通知接收的参数为："+gift);
    }

    @After("myPointCut() && args(gift)")
    public void myAfter(String gift){
        System.out.println("最终通知");
        System.out.println("最终通知接收的参数为："+gift);
    }


}

package com.coderhouse.ecommerce.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Aspect
@Component
public class AspectAfter {

    //con cada request se deberá registrar un log al finalizar el mismo
    @Pointcut ("execution(* com.coderhouse.ecommerce.controller.*.*(..))")
    void requestLog() {}

    @After("requestLog()")
    void afterAdviceMethod() throws Exception {
        log.info("request done! " + LocalDateTime.now());
    }
}

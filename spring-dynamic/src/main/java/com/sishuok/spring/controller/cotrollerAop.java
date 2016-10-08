package com.sishuok.spring.controller;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class cotrollerAop {

	private ApplicationContext ctx;
	private DefaultListableBeanFactory beanFactory;

	@Autowired
	public void setApplicationContext(ApplicationContext ctx) {
		if (!DefaultListableBeanFactory.class.isAssignableFrom(ctx.getAutowireCapableBeanFactory().getClass())) {
			throw new IllegalArgumentException("BeanFactory must be DefaultListableBeanFactory type");
		}
		this.ctx = ctx;
		this.beanFactory = (DefaultListableBeanFactory) ctx.getAutowireCapableBeanFactory();
	}
	@Pointcut("execution(* com.sishuok.spring.dynamic.DynamicDeployBeans.*(..))")
	public void aspect1() {
	} 
	
	
	@After("aspect1()")
	public void listRegisterBean(){
		for (String s : beanFactory.getBeanDefinitionNames()) {
			System.out.println("aspect of ListRegistBean " + s);
		}
	}
}

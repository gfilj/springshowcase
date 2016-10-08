package com.sishuok.spring.controller;

import com.sishuok.spring.dynamic.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * <p>
 * User: Zhang Kaitao
 * <p>
 * Date: 13-12-13
 * <p>
 * Version: 1.0
 */

@Controller
public class UserController {

	@Autowired
	private ApplicationContext ctx;

	@Autowired
	private DynamicDeployBeans dynamicDeployBeans;

	@Autowired
	private DynamicDeployBeans2 dynamicDeployBeans2;

	@RequestMapping("/hello")
	public String hello() {
		DynamicService2 dynamicService2 = null;
		try {
			dynamicService2 = ctx.getBean(DynamicService2.class);
		} catch (Exception e) {
		}

		System.out.println(dynamicService2);
		if (dynamicService2 != null) {
			System.out.println("dynamicService2 : " + dynamicService2.getMessage());
		}
		return "success";
	}

	@RequestMapping("/registerBean")
	public String registerBean() {
		dynamicDeployBeans.registerBean(DynamicService1.class);
		dynamicDeployBeans.registerBean(DynamicService2.class);
		return "success";
	}
	
	@RequestMapping("/removeBean")
	public String removeBean(){
		dynamicDeployBeans.removeBeans(DynamicService1.class);
		dynamicDeployBeans.removeBeans(DynamicService2.class);
		return "success";
	}

	@RequestMapping("/registerController")
	public String registerController() {
		// for(Field field:DynamicController.class.getDeclaredFields()){
		// for(Annotation annontation:field.getDeclaredAnnotations()){
		// if (annota)
		// }
		// }

		dynamicDeployBeans.registerController(DynamicController.class);
		return "success";
	}
	@RequestMapping("/removeController")
	public String removeController() {
		dynamicDeployBeans.removeController(DynamicController.class);
		return "success";
	}
	

	@RequestMapping("/registerGroovyController")
	public String registerGroovyController() throws IOException {
		// dynamicDeployBeans.registerGroovyController("classpath:com/sishuok/spring/dynamic/GroovyController.groovy");
		dynamicDeployBeans2.registerGroovyController("classpath:com/sishuok/spring/dynamic/GroovyController.groovy");
		return "success";
	}
	

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
		context.setConfigLocation("calsspath:spring-mvc-test.xml");
		Method loadBeanDefinitions = ReflectionUtils.findMethod(AbstractXmlApplicationContext.class,
				"loadBeanDefinitions",DefaultListableBeanFactory.class);
		if (loadBeanDefinitions != null) {
			System.out.println("hello! I'm success!");
		}
//		loadBeanDefinitions.setAccessible(true);
	}

}

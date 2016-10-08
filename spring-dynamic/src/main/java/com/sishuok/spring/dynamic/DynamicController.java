package com.sishuok.spring.dynamic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-3
 * <p>Version: 1.0
 */
@Controller
public class DynamicController {
	
	@Autowired
	DynamicService2 dynamicService2;
	
    @RequestMapping("/controller")
    public String controller() {
    	System.out.println(dynamicService2.getMessage());
        System.out.println(123);
        return "success";
    }
}

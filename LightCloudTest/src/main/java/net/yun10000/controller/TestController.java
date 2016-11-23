package net.yun10000.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.yun10000.model.User;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {
	private static Logger logger=Logger.getLogger(TestController.class);
	@RequestMapping("/getUser")
	@ResponseBody
	public User getUser(){
		User user=new User();
		user.setAge(10);
		user.setId(1);
		user.setPassword("123456");
		user.setUserName("abc");
		return user;
	}
	
	@RequestMapping("/test01")
	
	public void test01(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Enumeration<String> parameterNames = request.getParameterNames();
		System.out.println("请求开始");
		while(parameterNames.hasMoreElements()){
			String key = parameterNames.nextElement();
			String value = request.getParameter(key);
			System.out.println(key+":"+value);
		}
		String returnResult="OK";
		ServletOutputStream outputStream = response.getOutputStream();
		outputStream.write(returnResult.getBytes("utf-8"));
		
		outputStream.close();
	}

}

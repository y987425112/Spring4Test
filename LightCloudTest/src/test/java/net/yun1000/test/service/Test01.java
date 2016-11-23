package net.yun1000.test.service;

import java.util.Date;
import java.util.UUID;

import net.yun10000.dao.UserDao;
import net.yun10000.dao.sys.SysDao;
import net.yun10000.dto.Menu;
import net.yun10000.model.User;
import net.yun10000.po.MenuPO;
import net.yun10000.service.PropertiesService;
import net.yun10000.service.ServiceTest01;
import net.yun10000.service.UserService;
import net.yun10000.service.sys.SysService;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ContextConfiguration(locations={"classpath:spring/ApplicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class Test01 {
	
	private Logger logger=Logger.getLogger(Test01.class); 
	@Autowired
	private ServiceTest01 serviceTest01;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PropertiesService propertiesService; 
	
	@Autowired
	private UserService userService;
	@Autowired
	private SysDao sysDao;
	@Autowired
	private SysService sysService; 
	@Autowired
	private ObjectMapper objectMapper; 
	@Test
	public void test01(){
		serviceTest01.print();
	}
	@Test
	public void test02(){
		String userName = propertiesService.getUserName();
		logger.info(userName);
		
	}
	@Test
	public void test03(){
		User user = userService.getUser(1);
		System.out.println(user);
	}
	
	@Test
	public void test04(){
		User user = userDao.getUser(1);
		System.out.println(user);
	}
	
	@Test
	@Rollback(value=false)
	public void test05(){
		MenuPO menuPO=new MenuPO();
		Date curDate=new Date();
		
		menuPO.setCode(getGuid());
		menuPO.setCreateTime(curDate);
		menuPO.setCreator(getGuid());
		menuPO.setGuid(getGuid());
		menuPO.setName("二级菜单");
		menuPO.setOrderId(1);
		menuPO.setRemark("");
		menuPO.setTreeCode("00010001");
		menuPO.setUpdateTime(curDate);
		menuPO.setUpdator(getGuid());
		menuPO.setUrl("/test/test");

		if(1==1){
			return;
		}
		sysDao.insertMenuPO(menuPO);
	}
	@Test
	public void test06() throws JsonProcessingException{
		Menu topMenu = sysService.getTopMenu();
		String str = objectMapper.writeValueAsString(topMenu);
		System.out.println(str);
	}
	
	private String getGuid(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	

}

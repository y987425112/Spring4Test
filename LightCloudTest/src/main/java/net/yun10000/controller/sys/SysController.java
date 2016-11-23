package net.yun10000.controller.sys;

import java.util.ArrayList;
import java.util.List;

import net.yun10000.dto.JsonMap;
import net.yun10000.dto.Menu;
import net.yun10000.po.MenuPO;
import net.yun10000.service.sys.SysService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping(value="/sys")
@Controller
public class SysController {
	private static final String INDEX_URL="/index.jsp";
	@Autowired
	private SysService sysService;
	
	@RequestMapping(value="/getTopMenu")
	@ResponseBody
	public JsonMap getTopMenu(){
		Menu topMenu = sysService.getTopMenu();
		JsonMap jsonMap=new JsonMap();
		jsonMap.addObject("topMenu", topMenu);
		return jsonMap;
	}
	/**
	 * 
	 * */
	@RequestMapping("/index")
	public ModelAndView index(){
		ModelAndView modelAndView=new ModelAndView("redirect:"+INDEX_URL);
		List<MenuPO> menuPOList = sysService.getMenuPOList();
		List<Menu> menuList=new ArrayList<Menu>();
		
		modelAndView.addObject("menuPOList", menuPOList);
		
		return modelAndView;
	}
	
	

}

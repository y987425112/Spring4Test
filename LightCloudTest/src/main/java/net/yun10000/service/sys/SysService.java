package net.yun10000.service.sys;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import net.yun10000.dao.sys.SysDao;
import net.yun10000.dto.Menu;
import net.yun10000.po.MenuPO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysService {
	
	@Autowired
	private SysDao sysDao;
	
	public Menu getTopMenu(){
		List<MenuPO> menuPOList = sysDao.getMenuPOList();
		Menu topMenu=new Menu();
		if(menuPOList==null||menuPOList.size()==0){
			return topMenu;
		}
		Set<MenuPO> menuPOSet=new TreeSet<MenuPO>(new Comparator<MenuPO>() {

			@Override
			public int compare(MenuPO o1, MenuPO o2) {
				
				return o1.getTreeCode().compareTo(o2.getTreeCode());
			}
		});
		for(MenuPO menuPOTemp:menuPOList ){
			menuPOSet.add(menuPOTemp);
		}
		Map<String, Menu> menuMap=new HashMap<String, Menu>();
		
		for(MenuPO menuPOTemp:menuPOSet){
			String treeCode = menuPOTemp.getTreeCode();
			if(treeCode.length()==4){
				topMenu.setCode(menuPOTemp.getCode());
				topMenu.setGuid(menuPOTemp.getGuid());
				topMenu.setName(menuPOTemp.getName());
				topMenu.setOrderId(menuPOTemp.getOrderId());
				topMenu.setTreeCode(menuPOTemp.getTreeCode());
				topMenu.setUrl(menuPOTemp.getUrl());
				menuMap.put(treeCode, topMenu);
				continue;
			}
			String treeCodeParent=treeCode.substring(0,treeCode.length()-4);
			Menu menuParent = menuMap.get(treeCodeParent);
			Menu menuTemp=new Menu();
			menuTemp.setCode(menuPOTemp.getCode());
			menuTemp.setGuid(menuPOTemp.getGuid());
			menuTemp.setName(menuPOTemp.getName());
			menuTemp.setOrderId(menuPOTemp.getOrderId());
			menuTemp.setTreeCode(menuPOTemp.getTreeCode());
			menuTemp.setUrl(menuPOTemp.getUrl());
			menuParent.addMenu(menuTemp);
			menuMap.put(treeCode, menuTemp);	
		}
		
		return topMenu;
	}
	
	public List<MenuPO> getMenuPOList(){
		return sysDao.getMenuPOList();
	}

}

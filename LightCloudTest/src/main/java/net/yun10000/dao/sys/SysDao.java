package net.yun10000.dao.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.yun10000.dao.JdbcDao;
import net.yun10000.po.MenuPO;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

@Repository(value="sysDao")
public class SysDao extends JdbcDao{
	/**
	 * 插入菜单
	 * */
	public int  insertMenuPO(MenuPO menuPO){
		return insert(menuPO, "sys_menu", "");
	}
	
	public List<MenuPO> getMenuPOList(){
		String sql="select a.* from sys_menu a where 1=1";
		Map<String, Object> paramMap=new HashMap<String, Object>();
		return jdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<MenuPO>(MenuPO.class));
		
	}

}

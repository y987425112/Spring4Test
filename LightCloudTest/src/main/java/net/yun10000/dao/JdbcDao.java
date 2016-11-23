package net.yun10000.dao;


import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.yun10000.model.User;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcDao {

	@Autowired
	protected NamedParameterJdbcTemplate  jdbcTemplate ;
	@Autowired
	protected DBColumnMapper mapper;
	
	private static Logger logger=Logger.getLogger(JdbcDao.class);
	
	public User getUser(Integer id){
		String sql="select a.* from user_t where a.id=:id";
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("id", id);
		return jdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<User>(User.class)).get(0);
				
	}
	
	
	protected <T> int insert(T obj, String tableName, String excludingName)  {
		Class<?> clazz = obj.getClass();
		List<ColumnMapper> columnMapperList = mapper.getColumnMapper(clazz);
		
		StringBuffer insertSQL = new StringBuffer();
		insertSQL.append("INSERT INTO ");
		insertSQL.append(tableName);
		insertSQL.append("(");
		for(int index = 0; index < columnMapperList.size(); index++) {
			ColumnMapper mapper = columnMapperList.get(index);
			if(mapper.getFiled().equalsIgnoreCase(excludingName)) {
				continue;
			}
			insertSQL.append(mapper.getColumn());
			if(index != columnMapperList.size() - 1) {
				insertSQL.append(",");
			}
		}
		insertSQL.append(")");
		insertSQL.append(" ");
		insertSQL.append("VALUES");
		insertSQL.append("(");
		for(int index = 0; index < columnMapperList.size(); index++) {
			ColumnMapper mapper = columnMapperList.get(index);
			if(mapper.getFiled().equalsIgnoreCase(excludingName)) {
				continue;
			}
			insertSQL.append(":");
			insertSQL.append(mapper.getFiled());
			if(index != columnMapperList.size() - 1) {
				insertSQL.append(",");
			}
		}
		insertSQL.append(")");
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Field[] fields = clazz.getDeclaredFields();
		try {
			
		for(Field field : fields) {
			String fieldName = field.getName();
			if(!"serialVersionUID".equalsIgnoreCase(fieldName) && !fieldName.equalsIgnoreCase(excludingName)) {
				field.setAccessible(true);
				Object val = field.get(obj);
				paramMap.put(field.getName(), val);
				field.setAccessible(false);
			}
		}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			throw new RuntimeException("插入错误");
		}
		
		int rowNum = jdbcTemplate.update(insertSQL.toString(), paramMap);
		return rowNum;
	}
}

package net.yun10000.dao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class DBColumnMapper {

	private static Map<String, List<ColumnMapper>> mapper = new HashMap<String, List<ColumnMapper>>();
	
	private static final String UNDERLINE = "_";
	
	public List<ColumnMapper> getColumnMapper(Class<?> clazz) {
		List<ColumnMapper> columnMapperList = mapper.get(clazz.getName());
		if(columnMapperList==null||columnMapperList.size()==0) {
			columnMapperList = generateColumnMapper(clazz);
			mapper.put(clazz.getName(), columnMapperList);
		}
		return columnMapperList;
	}

	private List<ColumnMapper> generateColumnMapper(Class<?> clazz) {
		List<ColumnMapper> columnMapperList = new ArrayList<ColumnMapper>();
		Field[] fields = clazz.getDeclaredFields();
		for(Field field : fields) {
			String fieldName = field.getName();
			if(!"serialVersionUID".equalsIgnoreCase(fieldName)&& !"$jacocoData".equalsIgnoreCase(fieldName)) {
				ColumnMapper columnMapper = new ColumnMapper();
				columnMapper.setFiled(fieldName);
				columnMapper.setColumn(obtainColumn(fieldName));
				columnMapperList.add(columnMapper);
			}
		}
		return columnMapperList;
	}

	private String obtainColumn(String fieldName) {
		StringBuffer builder = new StringBuffer();
		char[] charArray = fieldName.toCharArray();
		for(int index = 0; index < charArray.length; index++) {
			char ch = charArray[index];
			if(Character.isUpperCase(ch)) {
				if(index == 0) {
					builder.append(Character.toLowerCase(ch));
				} else {
					builder.append(UNDERLINE);
					builder.append(Character.toLowerCase(ch));
				}
			} else {
				builder.append(ch);
			}
		}
		return builder.toString();
	}
}

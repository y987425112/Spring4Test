package net.yun10000.config;

import java.text.SimpleDateFormat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class ContextConfig {
	@Bean(name = {"objectMapper"})
	public ObjectMapper getObjectMapper() {
		ObjectMapper	objectMapper = new MyObjectMapper();
		objectMapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
		objectMapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		//设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性  
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		//格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		objectMapper.setDateFormat(sdf);
		return objectMapper;
	}
}

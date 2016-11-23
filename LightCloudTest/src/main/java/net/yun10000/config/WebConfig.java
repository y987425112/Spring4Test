package net.yun10000.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.handler.AbstractHandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class WebConfig {
	@Autowired
	private ObjectMapper objectMapper; 

	@Bean(name="handlerMapping")
	public HandlerMapping getHandlerMapping() {
		AbstractHandlerMapping handlerMapping = new RequestMappingHandlerMapping();
//		handlerMapping.setInterceptors(new Object[]{getLocaleChangeInterceptor()});
		return handlerMapping;
	}

	/**
	 * @return <br/>
	 *         添加文件上传的支持
	 */
	@Bean(name = { "multipartResolver" })
	public MultipartResolver getMultipartResolver() {
		CommonsMultipartResolver mr = new CommonsMultipartResolver();
		mr.setMaxInMemorySize(1);
		return mr;
	}
	

	
	
//	@Bean(name = {"fastDFSManager"})
//	public FastDFSManager getFastDfsClient() throws IOException {
//		FastDFSManager manager = new FastDFSManager();
//	    return manager;
//	}
	
	/**
	 * @return
	 */
	@Bean(name="handlerAdapter")
	public HandlerAdapter getHandlerAdapter() {
//		ObjectMapper objectMapper = getObjectMapper();
		RequestMappingHandlerAdapter  adapter = new RequestMappingHandlerAdapter();
		//添加controller的json支持
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		
		converter.setObjectMapper(objectMapper);
		
		List list=new ArrayList<MappingJackson2HttpMessageConverter>();
		list.add(converter);
		adapter.setMessageConverters(list);
		//设置数据提交转换器
//		adapter.setWebBindingInitializer(new CustomWebBindingInitializer());
		return adapter;
	}
	
	
	
	
	
	@Bean(name={"viewResolver"})
	public ViewResolver getJspViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setOrder(1);
		viewResolver.setPrefix("/page/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
}

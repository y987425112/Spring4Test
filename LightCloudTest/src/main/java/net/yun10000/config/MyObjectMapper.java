package net.yun10000.config;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class MyObjectMapper extends ObjectMapper {

	Logger logger=Logger.getLogger(MyObjectMapper.class);
	  @Override
	    public void writeValue(JsonGenerator jgen, Object value)
	        throws IOException, JsonGenerationException, JsonMappingException
	    {
		  super.writeValue(jgen,value);
		  String str = writeValueAsString(value);
		  logger.info(str);
//	        SerializationConfig config = copySerializationConfig();
//	        if (config.isEnabled(SerializationConfig.Feature.CLOSE_CLOSEABLE) && (value instanceof Closeable)) {
//	            _writeCloseableValue(jgen, value, config);
//	        } else {
//	            _serializerProvider.serializeValue(config, jgen, value, _serializerFactory);
//	            if (config.isEnabled(SerializationConfig.Feature.FLUSH_AFTER_WRITE_VALUE)) {
//	                jgen.flush();
//	            }
//	        }
	    }
}

package com.wanwei.common.base.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.wanwei.common.base.exception.BaseException;
import com.wanwei.common.base.exception.ServerCommonException;
import org.apache.commons.codec.binary.Base64;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;


/**
 * json工具类
 * @author qbk
 * @version 1.0 2015-6-16
 * @since 1.0
 */
public class JsonUtils {
	private ObjectMapper objectMapper;
	private static JsonUtils instance = new JsonUtils();
	public static JsonUtils getInstance(){
		return instance;
	}
	
	private JsonUtils(){
		this.objectMapper = new ObjectMapper();
		this.objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		this.objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("pageFilter",
                  SimpleBeanPropertyFilter.serializeAllExcept("number"));
		this.objectMapper.setFilterProvider(filterProvider);

		
	}
	 
	/**
	 * 实体转化为json
	 * @author qbk
	 * @date 2015-6-16
	 * @param obj
	 * @return
	 * @see
	 */
	public String convertToJson(Object obj){
		
			try {
				return this.objectMapper.writeValueAsString(obj);
			} catch (JsonProcessingException e) {
				throw new ServerCommonException();
			}
		
	}

	/**
	 * json 转化为实体
	 * @author qbk
	 * @date 2015-6-16
	 * @param clazz
	 * @param content
	 * @return
	 * @see
	 */
	public <T> T convertToBean(Class<T> clazz, String content){
		try {
			return this.objectMapper.readValue(content,clazz);
		} catch (IOException e) {
			throw new ServerCommonException();
		}
	}
	public <T> T convertToBean(JavaType javaType, String content){
		try {
			return this.objectMapper.readValue(content,javaType);
		} catch (IOException e) {
			throw new BaseException();
		}
	}
	/**
	 * json 串中取result节点业务数据，仅限格式{"state" : "1","message" : "操作成功",
	 *			                  "result" : {"number" : 10,"page" : 1,"records" : 1,"total" : 1,"rows" : []}}
	 *                             存在result节点的json串
	 * @author qbk
	 * @date 2015-6-16
	 * @param content
	 *               webservice接口返回json数据
	 * @return
	 * @see
	 */
    public String getJsonResult(String content){
    	 try {
			JsonNode node = objectMapper.readTree(content);
			return  node.get("result").toString();
		} catch (Exception e) {
			 throw new ServerCommonException();
		} 
	}
    /**
	 * json 串中取state节点业务数据，仅限格式{"state" : "1","message" : "操作成功",
	 *			                  "result" : null},存在state节点的json串
	 * @author qbk
	 * @date 2015-7-3
	 * @param content
	 *               webservice接口返回json数据
	 * @return
	 * @see
	 */
    public String getJsonState(String content){
   	 try {
			JsonNode node = objectMapper.readTree(content);
			return  node.get("state").asText();
		} catch (Exception e) {
		 throw new ServerCommonException();
		} 
	}
	/**
	 * 获取json 串中指定  节点 字符串
	 * @author qbk
	 * @date 2015-6-24
	 * @param content json字符串
	 * @param nodeName  要获取的节点名称
	 * @return
	 * @see
	 */
    public String getjsonNodeText(String content,String nodeName){
    	 try {
 			JsonNode node = objectMapper.readTree(content);
 			return  node.get(nodeName).asText();
 		} catch (Exception e) {
			 throw new ServerCommonException();
 		} 
    }
    /**
     * 获取json 串中指点节点的json串
     * @author qbk
     * @date 2015-7-3
     * @param content   json字符串
     * @param nodeName  要获取的节点名称
     * @return
     * @see
     */
    public String getjsonNodeString(String content,String nodeName){
   	 try {
			JsonNode node = objectMapper.readTree(content);
			return  node.get(nodeName).toString();
		} catch (Exception e) {
		 throw new ServerCommonException();
		} 
   }
    /**
     * json串中添加 节点
     */
    public String addJsonNodeToArrayNode(String content,HashMap<String,Object> node){
    	
		List<HashMap<String,Object>> list = convertToBean(List.class, content);
		list.add(0, node);
		String returnStr = convertToJson(list);
    	return returnStr;
    }
    
    public <T> T getJsonNodeToBean(Class<T> clazz,String content,String nodeName){
    	
    	String nodeJson = this.getjsonNodeString(content,nodeName);
    	return this.convertToBean(clazz, nodeJson);
    	
    }
	public JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
		return this.objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
	}

    /**
     * DcloudMsg Decode
     * @author qbk
     * @date 2017年5月4日
     * @param msg
     * @return
     * @throws UnsupportedEncodingException
     * @see
     */
    public static String getDecodeDcloudMsg(String msg) throws UnsupportedEncodingException {
        byte[] decodeBase64 = Base64.decodeBase64(Base64.decodeBase64(msg.getBytes("UTF-8")));
        return new String(decodeBase64, "UTF-8");

    }
}

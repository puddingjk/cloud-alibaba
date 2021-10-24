//package com.wanwei.common.base.util;
//
//import com.wanwei.common.base.annotation.SnowflakeIdAnnotation;
//import org.apache.commons.lang3.StringUtils;
//
//import java.lang.reflect.Field;
//import java.util.Date;
//import java.util.Set;
//
///**
// * EntityUtils
// *  实体类相关工具类
// *  解决问题： 1、快速对实体的常驻字段，如：crtUser、crtHost、updUser等值快速注入
// * @author qbk
// * @version 1.0 2018/6/13
// * @since 1.0
// */
//
//public class EntityUtils {
//
//	public static final String  CREATE_TIME = "createTime";
//
//	/**
//	 * 快速将bean的createTime附上相关值
//	 *
//	 * @param entity 实体bean
//	 *
//	 */
//	public static <T> void setCreatInfo(T entity) {
//
//		setCreateInfo(entity);
//		//setUpdatedInfo(entity);
//	}
//	public static <T> void setDefaultId(T entity){
//		if(!ReflectionUtils.hasField(entity, "id")) {
//			return ;
//		}
//		String type = ReflectionUtils.getFieldType(entity, "id");
//		if(StringUtils.isBlank(type)) {
//			return;
//		}
//		Object value =  ReflectionUtils.getFieldValue(entity, "id");
//
//		if(value != null && StringUtils.isNotBlank(value.toString())){
//		    //有值，则不进行设置
//			return;
//		}
//		Field idField = ReflectionUtils.getAccessibleField(entity, "id");
//		if(type.contains("java.lang.String")){
//		    //uuid
//			if(StringUtils.isBlank((String)value) && idField.isAnnotationPresent(SnowflakeIdAnnotation.class)){
//				ReflectionUtils.setFieldValue(entity,"id",SnowflakeIdUtils.genIdStr());
//			}else{
//				ReflectionUtils.setFieldValue(entity,"id",UUIDUtils.generate32UUidString());
//			}
//		}else if(idField.isAnnotationPresent(SnowflakeIdAnnotation.class)){
//            //雪花算法设置id
//			ReflectionUtils.setFieldValue(entity,"id",SnowflakeIdUtils.genId());
//		}
//	}
//	/**
//	 * 快速将bean的crtUser、crtHost、crtTime附上相关值
//	 *
//	 * @param entity 实体bean
//	 *
//	 */
//	public static <T> void setCreateInfo(T entity){
//		// 默认属性
//		String[] fields = {CREATE_TIME};
//		Field field = ReflectionUtils.getAccessibleField(entity, CREATE_TIME);
//		// 默认值
//		Object [] value = null;
//		if(field!=null&&field.getType().equals(Date.class)){
//			value = new Object []{new Date()};
//		}
//		// 填充默认属性值
//		setDefaultValues(entity, fields, value);
//	}
//
//	/**
//	 * 快速将bean的updUser、updHost、updTime附上相关值
//	 *
//	 * @param entity 实体bean
//	 *
//	 */
//	public static <T> void setUpdatedInfo(T entity){
//		String hostIp = "";
//		String name = "";
//		String id = "";
//		// 默认属性
//		String[] fields = {"updName","updUser","updHost","updTime"};
//		Field field = ReflectionUtils.getAccessibleField(entity, "updTime");
//		Object [] value = null;
//		if(field!=null&&field.getType().equals(Date.class)){
//			value = new Object []{name,id,hostIp,new Date()};
//		}
//		// 填充默认属性值
//		setDefaultValues(entity, fields, value);
//	}
//	/**
//	 * 依据对象的属性数组和值数组对对象的属性进行赋值
//	 *
//	 * @param entity 对象
//	 * @param fields 属性数组
//	 * @param value 值数组
//	 *
//	 */
//	private static <T> void setDefaultValues(T entity, String[] fields, Object[] value) {
//		for(int i=0;i<fields.length;i++){
//			String field = fields[i];
//			if(ReflectionUtils.hasField(entity, field)){
//				ReflectionUtils.invokeSetter(entity, field, value[i]);
//			}
//		}
//	}
//	/**
//	 * 根据主键属性，判断主键是否值为空
//	 *
//	 * @param entity
//	 * @param field
//	 * @return 主键为空，则返回false；主键有值，返回true
//	 */
//	public static <T> boolean isPKNotNull(T entity,String field){
//		if(!ReflectionUtils.hasField(entity, field)) {
//			return false;
//		}
//		Object value = ReflectionUtils.getFieldValue(entity, field);
//		return value!=null&&!"".equals(value);
//	}
//
//	/**
//	 * @Author zhangjing
//	 * @Description //主键值类型转换为实体对应类型
//	 * @Date 16:24 2018/9/8
//	 * @Param [clazz]
//	 * @return java.lang.String
//	 **/
//	public  static Object getPKProVal(Class clazz,Object idVal){
//		Set<EntityColumn> columns = EntityHelper.getPKColumns(clazz);
//		StringBuffer columnName = new StringBuffer();
//		EntityColumn column = columns.iterator().next();
//		Class colClass = column.getJavaType();
//		if(colClass.equals(Integer.class)){
//			return Integer.parseInt(idVal.toString());
//		}
//		return idVal;
//	}
//}

package com.simpleserver.web.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**   
 * @Title: 基础类 格式化json  
 * @author: huangyb  
 * @date:2018年9月5日 下午3:42:09     
 */ 
public class JsonUtil {

	private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
	private static final SerializeConfig config = new SerializeConfig();

	static {
		config.put(Date.class, new SimpleDateFormatSerializer(DEFAULT_DATE_PATTERN));
		config.put(Timestamp.class, new SimpleDateFormatSerializer(DEFAULT_DATE_PATTERN));
		config.put(java.sql.Date.class, new SimpleDateFormatSerializer(DEFAULT_DATE_PATTERN));
	}

	private static final SerializerFeature[] features = { SerializerFeature.WriteMapNullValue, // 输出map空值字段
			SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，输出为[]，而不是null
			SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null，输出为0，而不是null
			SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null
			SerializerFeature.WriteNullStringAsEmpty, // 字符类型字段如果为null，输出为""，而不是null
			SerializerFeature.WriteDateUseDateFormat };

	/**
	 * 功能描述：把对象序列化为json字符串
	 * 
	 * @param obj 要转为json串的java对象
	 * @return json字符串
	 */
	public static String toJson(Object obj) {
		return JSON.toJSONString(obj, config, features);
	}

	public static String toJSONNoFeatures(Object object) {
		return JSON.toJSONString(object, config);
	}

	public static <T> T toBean(String text, Class<T> clazz) {
		return JSON.parseObject(text, clazz);
	}

	// 转换为数组
	public static <T> Object[] toArray(String text, Class<T> clazz) {
		return JSON.parseArray(text, clazz).toArray();
	}

	// 转换为List
	public static <T> List<T> toList(String text, Class<T> clazz) {
		return JSON.parseArray(text, clazz);
	}

	/**
	 * 功能描述：把JSON数据转换成普通字符串列表
	 * 
	 * @param jsonData JSON数据
	 * @return
	 * @throws Exception
	 */
	public static List<String> getStringList(String jsonData) throws Exception {
		return JSON.parseArray(jsonData, String.class);
	}

	/**
	 * 功能描述：把JSON数据转换成指定的java对象
	 * 
	 * @param jsonData JSON数据
	 * @param clazz 指定的java对象
	 * @return
	 * @throws Exception
	 */
	public static <T> T getSingleBean(String jsonData, Class<T> clazz) throws Exception {
		return JSON.parseObject(jsonData, clazz);
	}

	/**
	 * 功能描述：把JSON数据转换成指定的java对象列表
	 * 
	 * @param jsonData JSON数据
	 * @param clazz 指定的java对象
	 * @return
	 * @throws Exception
	 */
	public static <T> List<T> getBeanList(String jsonData, Class<T> clazz) throws Exception {
		return JSON.parseArray(jsonData, clazz);
	}

	/**
	 * 功能描述：把JSON数据转换成较为复杂的java对象列表
	 * 
	 * @param jsonData JSON数据
	 * @return
	 * @throws Exception
	 */
	public static List<Map<String, Object>> getBeanMapList(String jsonData) throws Exception {
		return JSON.parseObject(jsonData, new TypeReference<List<Map<String, Object>>>() {
		});
	}

	/**
	 * @Title: 将json数据转化为map对象
	 * @author bang
	 * @param jsonData json串
	 * @param features
	 * @return
	 * @throws Exception
	 */
	public static JSONObject toMap(String jsonData, Feature... features) {
		return (JSONObject) JSON.parse(jsonData, features);
	}

	/**
	 * @Title: 将json数据转化为map对象
	 * @author bang
	 * @param jsonData
	 * @return
	 * @throws Exception
	 */
	public static JSONObject toMap(String jsonData) {
		return (JSONObject) JSON.parse(jsonData);
	}

	public static JSONObject toMap(String jsonData, SerializeFilter sf) {
		JSONObject jo = toMap(jsonData);
		String str = JSON.toJSONString(jo, sf);
		return toMap(str);
	}

	public static <T> T toObject(String jsonData, Class<T> clazz) {
		return JSON.parseObject(jsonData, new TypeReference<T>() {
		});
	}

	/**
	 * 将网络请求下来的数据用fastjson处理空的情况，并将时间戳转化为标准时间格式
	 * 
	 * @param result
	 * @return
	 */
	public static String dealResponseResult(String result) {
		result = JSONObject.toJSONString(result, SerializerFeature.WriteClassName, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullBooleanAsFalse,
				SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteDateUseDateFormat,
				SerializerFeature.WriteEnumUsingToString, SerializerFeature.WriteSlashAsSpecial);
		return result;
	}

}

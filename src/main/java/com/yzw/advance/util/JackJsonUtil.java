package com.yzw.advance.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.json.JsonMapper.Builder;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class JackJsonUtil {
    public JackJsonUtil() {
    }

    private static class SingletonHolder {
        private static final ObjectMapper mapper;

        private SingletonHolder() {
        }

        static {
            mapper = ((Builder) ((Builder) ((Builder) JsonMapper.builder()
                    .enable(new JsonReadFeature[]{JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS})
                    .enable(new JsonReadFeature[]{JsonReadFeature.ALLOW_JAVA_COMMENTS})
                    .disable(new DeserializationFeature[]{DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES}))
                    .defaultTimeZone(TimeZone.getTimeZone("GMT+8")))
                    .defaultDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINESE))).build();
        }
    }

    public static ObjectMapper getMapper() {
        return JackJsonUtil.SingletonHolder.mapper;
    }

    /**
     * 把Object转json字符串
     *
     * @param obj
     * @return
     * @Title objectToJson
     * @Description 把Object转json字符串
     * @version
     */
    public static String toJson(Object obj) {
        try {
            return getMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * json字符串转换为对象
     *
     * @param json json字符串
     * @param obj 转换对象
     * @return
     * @Title jsonToObject
     * @Description json字符串转换为对象
     * @version
     */
    public static <T> T jsonToObject(String json, Class<T> obj) {
        try {
            return getMapper().readValue(json, obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param jsonString json字符串
     * @param typeReference 转换引用对象
     * @param <T>
     * @return
     */
    public static <T> T jsonToObject(String jsonString, TypeReference<T> typeReference) {
        if (StringUtils.isNotBlank(jsonString)) {
            try {
                return getMapper().readValue(jsonString, typeReference);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 将json字符串转换成集合对象
     * @param jsonStr json字符串
     * @param collectionClass 集合对象
     * @param elementClass 数据对象
     * @param <T>
     * @return
     */
    public static <T> T jsonToObject(String jsonStr, Class<?> collectionClass, Class<?>... elementClass) {
        if (!StringUtils.isNotEmpty(jsonStr)) {
            return null;
        } else {
            JavaType javaType = getMapper().getTypeFactory().constructParametricType(collectionClass, elementClass);
            try {
                return getMapper().readValue(jsonStr, javaType);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}

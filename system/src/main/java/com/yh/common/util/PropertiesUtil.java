/**
 *
 */
package com.yh.common.util;

import cn.hutool.core.io.IoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Objects;
import java.util.Properties;

/**
 * @author YH
 * @ClassName: PropertiesUtil
 * @Description Properties类线程安全，无需进行外部同步
 * @date 2020年9月28日 上午10:16:23
 */
@Slf4j
public class PropertiesUtil {

    /**
     * 获取指定前缀的map集合,并去除前缀为key
     * */
    public static HashMap<String, String> getPropertyOfMap(String fileName) {
        HashMap<String, String> resultMap = new HashMap<>();

        Properties properties = new Properties();
        InputStream resourceAsStream = null;
        try {
            resourceAsStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(resourceAsStream, "utf-8");
            properties.load(inputStreamReader);
            for (Object o : properties.keySet()) {
                String keyStr = o.toString();
                resultMap.put(keyStr, String.valueOf(properties.getProperty(keyStr)));
            }
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        } finally {
            IoUtil.close(resourceAsStream);
        }
        return resultMap;
    }

    /**
     * 获取指定前缀的map集合,并去除前缀为key
     * */
    public static HashMap<String, String> getPropertyOfMap(String fileName, String prefix) {
        HashMap<String, String> resultMap = new HashMap<>();
        if (StringUtils.isEmpty(prefix)) {
            return resultMap;
        }
        Properties properties = new Properties();
        InputStream resourceAsStream = null;
        try {
            resourceAsStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(resourceAsStream, "utf-8");
            properties.load(inputStreamReader);
            for (Object o : properties.keySet()) {
                String keyStr = o.toString();
                String prefixReal = keyStr.substring(0, keyStr.lastIndexOf('.'));
                if (Objects.equals(prefix, prefixReal)) {
                    resultMap.put(keyStr.substring(keyStr.lastIndexOf('.') + 1), String.valueOf(properties.getProperty(keyStr)));
                }
            }
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        } finally {
            IoUtil.close(resourceAsStream);
        }
        return resultMap;
    }

    /**
     * 获取指定properties文件中的key-value
     * */
    public static String getPropertyOfValue(String fileName, String key) {

        if (StringUtils.isEmpty(key)) {
            return "";
        }
        Properties properties = new Properties();
        InputStream resourceAsStream = null;
        String value = "";
        try {
            resourceAsStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(resourceAsStream, "utf-8");
            properties.load(inputStreamReader);
            value = properties.getProperty(key);
            if (value == null) {
                value = "";
            }
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        } finally {
            IoUtil.close(resourceAsStream);
        }
        return value;
    }

    //添加properties文件的key-value
    public static void setPropertyOfValue(String fileName, String key, String value) {
        Properties properties = new Properties();
        InputStream resourceAsStream = null;

        try {
            resourceAsStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(resourceAsStream, "utf-8");
            properties.load(inputStreamReader);
            properties.setProperty(key, value);//获取整个配置文件数据，并进行键值对修改、添加
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        } finally {
            IoUtil.close(resourceAsStream);
        }

        //将修改后的数据重新写入配置文件
        FileOutputStream fileOutputStream = null;
        try {
            URL resource = PropertiesUtil.class.getClassLoader().getResource(fileName);
            fileOutputStream = new FileOutputStream(new File(resource.getPath()));
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "utf-8");
            properties.store(outputStreamWriter, null);//可以添加注释
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        } finally {
            IoUtil.close(fileOutputStream);
        }
    }
}

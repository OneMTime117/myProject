package com.yh.common.util.poi;


import cn.hutool.poi.excel.ExcelReader;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yuanhuan
 * @description excel导入工具，基于hutool工具类，将List<Map>数据通过Bean上的@Excel注解，填充映射为Bean
 * <p>
 * 默认对所有字段值，转换为String类型，然后进行trim
 * @date 2021/11/8 10:37
 */
@Slf4j
public class ExcelUtil {

	public static <T> List<T> toBeanList(InputStream inputStream, Class<T> clazz) {
		ExcelReader reader = cn.hutool.poi.excel.ExcelUtil.getReader(inputStream);
		List<Map<String, Object>> maps = reader.readAll();
		List<T> resultList = new ArrayList<>();
		for (Map<String, Object> map : maps) {
			T o = null;
			try {
				o = (T) clazz.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				log.warn("class对象无法通过反射进行实例化", e);
			}

			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				Excel annotation = field.getAnnotation(Excel.class);
				if (annotation != null) {
					Object o1 = map.get(annotation.value());
					String value = o1 != null ? o1.toString().trim() : "";
					field.setAccessible(true);
					try {
						field.set(o, value);
					} catch (IllegalAccessException e) {
						log.warn("class实例化对象字段赋值失败", e);
					}
				}
			}
			resultList.add(o);
		}
		return resultList;
	}

}

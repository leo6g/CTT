package test.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
/**
 * 
 * @author lbb
 *
 */
public class BeanUtil{
	public static void copyProperties(Object source,Object target) {
		BeanUtils.copyProperties(source, target);
	}
	public static <T> List<T> convertList(List<?> source, Class<T> clazz) throws Exception {
		List<T> list = new ArrayList<T>();
		for (int i = 0; i < source.size(); i++) {
			T obj = clazz.newInstance();
			BeanUtils.copyProperties(source.get(i), obj);
			list.add(obj);
		}
		return list;
	}
}

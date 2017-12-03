package br.com.marteleto.framework.core.util;


import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

@SuppressWarnings({"rawtypes"})
public class ObjectUtil implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static void  copy(Object destinationObject, Object sourceObject) throws Exception {
		BeanUtils.copyProperties(destinationObject,sourceObject);
	}
	
	public static Field getFieldByName(Class clazz, String name) {
		List<Field> fields = new ArrayList<Field>();
		Class temp = clazz;
		while (temp != null) {
			fields.addAll(Arrays.asList(temp.getDeclaredFields()));
			temp = temp.getSuperclass();
		}
		for (Field field : fields) {
			if (field.getName().toLowerCase().equals(name.toLowerCase())) {
				return field;
			}
		}
		return null;
	}
	
	public static Method findMethod(Object object, String methodName, Class<?> returnType, Class<?> parameterType) throws Exception {
		Method result = null;
		Method[] methods = object.getClass().getMethods();
		for (Method method : methods) {
			if (methodName.toLowerCase().equals(method.getName().toLowerCase())) {
				if (returnType != null || parameterType != null) {
					if (parameterType != null) {
						Method temp = object.getClass().getMethod(method.getName(),parameterType);
						if (temp != null) {
							result = temp;
							break;
						}
					}
					if (returnType != null) {
						if (method.getReturnType().equals(returnType)) {
							result = method;
							break;
						}
					}
				} else {
					result = method;
					break;
				}
			}
		}
		return result;
	}
	
	public static Method findMethodGet(Object object, String methodName) throws Exception {
		return findMethod(object, "get" + methodName, null, null);
	}

	public static Method findMethodSet(Object object, String methodName) throws Exception {
		return findMethod(object, "set" + methodName, null, null);
	}
	
	public static Method findMethodSet(Object object, String methodName, Class<?> clazz) throws Exception {
		return findMethod(object, "set" + methodName, null ,clazz);
	}
	
	public static void setObjectValue(Object object, String methodName, Object value) throws Exception {
		setObjectValue(object,methodName,null,value);
	}
	
	public static void setObjectValue(Object object, String methodName, Class<?> clazz, Object value) throws Exception {
		if (object != null && methodName != null) {
			Method method = findMethodSet(object,methodName,clazz);
			if (method != null) {
				method.invoke(object, value);
			}
		}
	}
	
	public static Object getObjectValue(Object object, String methodName) throws Exception {
		if (object != null && methodName != null) {
			Method method = findMethodGet(object,methodName);
			if (method != null) {
				return method.invoke(object);
			}
		}
		return null;
	}
}
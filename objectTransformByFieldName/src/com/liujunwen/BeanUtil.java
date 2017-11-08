package com.liujunwen;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.liujunwen.entity.Person;
import com.liujunwen.entity.PersonChild;
import com.liujunwen.entity.Student;

/**
 * 对象数据映射（根据字段名称匹配）
 * @author jwliu
 *
 */
public class BeanUtil {

	@SuppressWarnings("unchecked")
	public static <T> T toRight(Object oldObject, Class<T> clazz) throws Exception {

		if (oldObject == null) {
			return null;
		}
		T newOject = clazz.newInstance();

		Field[] newFields = clazz.getDeclaredFields();

		for (Field field : newFields) {
			System.out.println("field "+field);
			String fieldName = field.getName();
			Method oldMethod = oldObject.getClass()
					.getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));
			oldMethod = oldMethod == null ? oldObject.getClass()
					.getMethod("is" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1)) : oldMethod;
			if (oldMethod == null) {
				continue;
			}
			Object oldValue = oldMethod.invoke(oldObject);
			if (oldValue == null) {
				continue;
			}
			if(!isBasicType(oldValue.getClass()) && !(oldValue instanceof List)) {
				System.out.println("...1");
				oldValue = toRight(oldValue, field.getType());
			}
			else if ((oldValue instanceof List) && !isBasicType(((List<Object>)oldValue).get(0).getClass())){
				System.out.println("...2");
				oldValue = arrayToList(oldValue,field);
			}
			System.out.println("...3");
			//赋值给新对象
			System.out.println("value = "+oldValue);
			Method newMethod = clazz
					.getMethod("set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1),field.getType());
			if(newMethod == null) {
				continue;
			}
			newMethod.invoke(newOject, oldValue);
		}
		return newOject;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static List<Object> arrayToList(Object oldValue, Field field) throws Exception {
		System.out.println("arrayToList...."+oldValue+"...."+field);
		ParameterizedType pt = (ParameterizedType)field.getGenericType(); // 获取列表的类型
		Class clz = (Class)pt.getActualTypeArguments()[0]; // 获取元素类型
		List<Object> oldValues = (List)oldValue;
		List<Object> newValues = new ArrayList<Object>();
		for(Object childObject : oldValues){
			Object newValue = toRight(childObject, clz);
			newValues.add(newValue);
		}
		return newValues;
		
	}

	/**
	 * 基本类型的判断
	 * @param clazz
	 * @return
	 */
	public static boolean isBasicType(Class<?> clazz) {

		return (clazz.equals(String.class) || clazz.equals(Integer.class) || clazz.equals(Short.class)
				|| clazz.equals(Long.class) || clazz.equals(Float.class) || clazz.equals(Double.class)
				|| clazz.equals(BigDecimal.class) || clazz.equals(BigInteger.class) || clazz.equals(Date.class)
				|| clazz.equals(Character.class) || clazz.equals(UUID.class) || clazz.isPrimitive());

	}
	
	public static void main(String[] args) {
		Person person = new Person();
		
		//infos
		List<String> infos = new ArrayList<String>();
		infos.add("hello");
		person.setInfos(infos);
		//name
		person.setName("1");
		//child
		PersonChild child = new PersonChild();
		child.setChildName("childName");
		person.setChild(child);
		//childs
		List<PersonChild> childs = new ArrayList<PersonChild>();
		PersonChild childInner = new PersonChild();
		childInner.setChildName("childNameIn");
		childs.add(childInner);
		person.setChilds(childs);
		
		try {
			Student student = toRight(person, Student.class);
			System.out.println(student);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

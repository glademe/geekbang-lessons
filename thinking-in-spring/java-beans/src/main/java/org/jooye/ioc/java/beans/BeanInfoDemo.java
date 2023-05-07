package org.jooye.ioc.java.beans;

import java.beans.*;
import java.util.stream.Stream;

/**
 * {@link java.beans.BeanInfo}示例
 *
 * @author :Jone
 * @date : 2023/5/7 9:32
 * @Describe:类的描述信息
 */
public class BeanInfoDemo {
    public static void main(String[] args) throws IntrospectionException {

        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class, Object.class);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

        Stream.of(propertyDescriptors).forEach(propertyDescriptor -> {

            //PropertyDescriptor允许添加属性编辑器   PropertyEditor
            System.out.println("propertyDescriptor = " + propertyDescriptor);
            //获取属性类型
            Class<?> propertyType = propertyDescriptor.getPropertyType();
            String propertyName = propertyDescriptor.getName();
            if ("age".equals(propertyName)) {//为age字段增加PropertyEditor
                propertyDescriptor.setPropertyEditorClass(String2IntegerPropertyEditor.class);
//                propertyDescriptor.createPropertyEditor()
            }
        });
    }

    static class String2IntegerPropertyEditor extends PropertyEditorSupport {
        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            Integer value = Integer.valueOf(text);
            setValue(value);
        }
    }
}

package org.jooye.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 层次性查找
 *
 * @author :Jone
 * @date : 2023/5/8 21:49
 * @Describe:类的描述信息
 */
public class HierarchicalDependencyLookup {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ObjectProvider.class);

        //获取 HierarchicalBeanFactory
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        System.out.println("当前BeanFactory的Parent BeanFactory" + beanFactory.getParentBeanFactory());

        //设置parent BeanFactory
        HierarchicalBeanFactory parentBeanFactory = createParentBeanFactory();
        beanFactory.setParentBeanFactory(parentBeanFactory);
        System.out.println("当前BeanFactory的Parent BeanFactory" + beanFactory.getParentBeanFactory());
        displayLocalBean(beanFactory, "user");
        displayLocalBean(parentBeanFactory, "user");
        applicationContext.refresh();
        applicationContext.close();
    }


    private static void displayContainsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("当前 BeanFactory [%s] 是否包含 [%s] : %s\n", beanFactory, beanName, containsBean(beanFactory, beanName));


    }

    private static boolean containsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
        if (parentBeanFactory instanceof HierarchicalBeanFactory) {
            HierarchicalBeanFactory hierarchicalBeanFactory = HierarchicalBeanFactory.class.cast(parentBeanFactory);
            if (containsBean(hierarchicalBeanFactory, beanName)) {
                return true;
            }
        }
        return beanFactory.containsLocalBean(beanName);
    }

    private static void displayLocalBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("当前 BeanFactory [%s] 是否包含 [%s] : %s\n", beanFactory, beanName, beanFactory.containsLocalBean(beanName));
    }


    private static HierarchicalBeanFactory createParentBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String location = "classpath:/META-INF/dependency-Injection-context.xml";
        reader.loadBeanDefinitions(location);

        return beanFactory;
    }
}

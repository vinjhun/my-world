package com.example.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App
{
    public static void main(String[] args)
    {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        
        /**================================IOC Inner Method ================================***/
        //Component annotation @Service @Controller @Repository @Component
        // <context:annotation-config /> //enable annotation only, required to scan package as well
        // <context:component-scan base-package=""/>
        //SAXReader (read xml)
        //dom4j (access)
//        SAXReader reader = new SAXReader();
//        try {
//            Document doc = reader.read(Farmer.class.getClassLoader().getResourceAsStream("test"));
//            //key and path of the ns
//            Map<String,String> nsMap = new HashMap<String,String>();
//            nsMap.put("ns", "http://www.springframework.org/schema/beans");
//            XPath xpath  = doc.createXPath("//ns:beans/ns:bean");
//            xpath.setNamespaceURIs(nsMap);
//            
//            List<Node> nodes = xpath.selectNodes(doc);
//            
//            //loop nodes to get the element
//            
//        } catch (DocumentException e1) {
//            e1.printStackTrace();
//        }
        
        /**================================DI Method ================================***/
        //@Autowired @Qualifier,@Resource, <property name="" ref="ref_bean_id" />
        //Introspector
        //PropertyDescriptor
//        try {
//           PropertyDescriptor[] pdList = Introspector.getBeanInfo(Farmer.class).getPropertyDescriptors();
//           
//           for(PropertyDescriptor pd :pdList)
//           {
//               //obj-- 1st the current class 
//               //args--value to be pass, in applicationContext.xml, the ref is refer to a previous bean value;
//               pd.getWriteMethod().invoke(obj, args)    
//           }
//        } catch (IntrospectionException e1) {
//            // TODO Auto-generated catch block
//        }
        
        
        /**================================PROXY (CGLIB,JDK) ================================***/
        //CGLIB ( MethodInterceptor  @Override intercept)
//        Farmer farmer = (Farmer)ctx.getBean("farmer");
//        System.out.println(farmer.findIdByRank(null));
//        System.out.println(farmer.getTaskDescription(null));
//        System.out.println("Farmer salary is " + farmer.getSalaryPerHour(null));
        
//        ctx.close();
        
        //JDK ( InvocationHandler  @Override invoke Object test = object.invoke(arg))
//        IFarmer farmer = (IFarmer)ctx.getBean("farmer");
//        System.out.println(farmer.findIdByRank(null));
//        System.out.println(farmer.getTaskDescription(null));
//        System.out.println("Farmer salary is " + farmer.getSalaryPerHour(null));
        
        //Theories
        /**
         * <aop:aspectj-autoproxy>
         *  (1) classes that has added proxy ( Require 
         *      @Configuration && 
         *      @EnableAspectJAutoProxy (if no applicationContext.xml to defined-> meaning using AnnotationConfigApplicationContext) 
         *      to instantiate as object)
         *  (2) while the class that will provide the method for aop 
         *      ( Require @Aspect , and method like @Before,@AfterReturn,@AfterThrowing,@After,@Around and @PointCuts.....)
         *      *Note: @Aspect and @Before etc.. must together
         *  (3) Based on point 1, most of the class are the configuration... and interface
         * **/
        Farmer farmer = (Farmer) ctx.getBean("farmer");
        System.out.println("Farmer salary is " + farmer.getSalaryPerHour(null));
        
        /**================================ CONFIGURE PROPERTIES ================================***/
        //<context:property-placeholder ignore-unresolvable="true" location="classpath:test.properties"/>
        
        /**================================ DATASOURCE ================================***/
        //this does not provide pools properties
        //4 types of data source for spring
        /**
         *  (1) DriverManagerDataSource : this one is just a simple connection without pool factory.
         *  (2) DBCP : 1st standard of connection with pool factory (using BasicDataSource)
         *  (3) C3P0 : 2nd open source with pool factory, and more settings
         *  (4) JNDI : suppose to be the one that are currently using now
         * 
         * **/       
        
        BasicDataSource ds = (BasicDataSource) ctx.getBean("dataSource");
        
        //Here is using db2jcc.jar class..
        try {
            Connection conn = ds.getConnection();
            Statement stmt = conn.createStatement();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
}

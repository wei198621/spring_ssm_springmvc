b站  编程不良人 

springmvc 从入门到精通   https://www.bilibili.com/video/BV1pE411E7fv


1. 引入jar包

2. 新增完善目录结构 src/main/java/**      下的 entity  dao service (controller 暂时可以不写)
   新增完善目录结构 src/main/resource/**  下的**Mapper.xml
3. 新增完善目录结构 src/main/resource  下的spring.xml
4. 新增完善目录结构 src/test/***    下的TestUserService

           以上步骤完成后,表明spring  mybatis 在平台已经配置完成
**************************************************************
           下面配置  spring  spring-mvc

1.  新增完善目录结构 src/main/resource  下的springmvc.xml
2.  修改 src/main/webapp/WEB-INF/web.xml
         <!--配置工厂监听器-->
         <!--配置工厂配置文件-->          以上两步 相当于 TestUserService 的启动spring
         <!--配置springmvc核心servlet-->      配置spring 关联 spring-mvc
         <!--配置post请求参数的中文乱码问题-->     这块在本机没有成功

3. 配置tomcat 服务 并运行
      http://localhost:8091/ssm_springmvc/


4.  http://localhost:8091/ssm_springmvc/user/findAll



src/main/resource  下的spring.xml
    <!--开启注解扫描-->
    <context:component-scan base-package="com.baizhi"/>
    <!--创建数据源对象-->
    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
        <property name="url" value="jdbc:mysql://localhost:3306/2001?characterEncoding=UTF-8"/>
        <property name="username" value="root"/>
        <property name="password" value="root"/>
    </bean>
    <!--创建sqlSessionFactory-->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <property name="mapperLocations" value="classpath:com/baizhi/mapper/*.xml"/>
        <property name="typeAliasesPackage" value="com.baizhi.entity"/>
    </bean>
    <!--创建DAO-->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
        <property name="basePackage" value="com.baizhi.dao"/>
    </bean>
    <!--创建事务管理器-->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"/>
    </bean>
    <!--开启注解式事务生效-->
    <tx:annotation-driven transaction-manager="transactionManager"/>
</beans>



src/test/***    下的TestUserService
public class TestUserService {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        UserService userService = (UserService) context.getBean("userService");
        userService.findAll().forEach(user-> System.out.println("user"+user));
    }
}
# spring AOP（xml配置方式）

## 目录<br/>
<a href="#一说明">一、说明</a><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<a href="#11-项目结构说明">1.1 项目结构说明</a><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<a href="#12-依赖说明">1.2 依赖说明</a><br/>
<a href="#二spring-aop">二、spring aop</a><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#21-创建待切入接口及其实现类">2.1 创建待切入接口及其实现类</a><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#22-创建自定义切面类">2.2 创建自定义切面类</a><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#23-配置切面">2.3 配置切面</a><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#24-测试切面">2.4 测试切面</a><br/>
<a href="#附-关于切面表达式的说明">附： 关于切面表达式的说明</a><br/>
## 正文<br/>


## 一、说明

### 1.1 项目结构说明

切面配置位于resources下的aop.xml文件，其中CustomAdvice是自定义切面类，OrderService是待切入的方法。

<div align="center"> <img src="https://github.com/qshomewy/SpringNotes/blob/master/pictures/spring-aop.png"/> </div>



### 1.2 依赖说明

除了spring的基本依赖外，需要导入aop依赖包

```xml
 <!--aop 相关依赖-->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-aop</artifactId>
    <version>${spring-base-version}</version>
</dependency>
```



## 二、spring aop

#### 2.1 创建待切入接口及其实现类

```java
public interface OrderService {

	Order queryOrder(Long id);
	
	Integer createOrder(Order order);
}
```

```java
public class OrderServiceImpl implements OrderService {

	public Order queryOrder(Long id) {
		return new Order(1001L, "苹果", new Date());
	}

	public Integer createOrder(Order order) {
		// 模拟异常
		int i = 1 / 0;
		return i;
	}
}
```

#### 2.2 创建自定义切面类

```java
public class CustomAdvice {

	public void before(JoinPoint joinPoint) {
		String name = joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		System.out.println("before前置通知, 方法" + name + ", 参数"+Arrays.toString(args));
	}
	
	// 可以访问返回值, 抛出异常后不会被执行
	public void afterReturning(JoinPoint joinPoint, Object result) {
		System.out.println("afterReturning后置通知, 返回" + result);
	}
	
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("around环绕通知, 前");
        // 调用目标方法
		Object proceed = joinPoint.proceed();
        System.out.println("around环绕通知, 后");
        return proceed;
	}
	
	public void afterException(JoinPoint joinPoint, Exception exception) {
		System.out.println("afterException异常通知, " + exception);
	}
	
	// 总会执行，但是不能访问到返回值
	public void after(JoinPoint joinPoint) {
		System.out.println("after后置通知");
	}
}
```

#### 2.3 配置切面

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        http://www.springframework.org/schema/aop/spring-aop.xsd">

    <!--开启后允许使用Spring AOP的@AspectJ注解 如果是纯xml配置 可以不用开启这个声明-->
    <aop:aspectj-autoproxy/>

    <!-- 1.配置目标对象 -->
    <bean name="orderService" class="com.qs.service.OrderServiceImpl"/>
    <!-- 2.声明切面 -->
    <bean name="myAdvice" class="com.qs.advice.CustomAdvice"/>
    <!-- 3.配置将通知织入目标对象 -->
    <aop:config>
        <!--命名切入点 关于切入点更多表达式写法可以参见README.md-->
        <aop:pointcut expression="execution(* com.qs.service.OrderService.*(..))" id="cutPoint"/>
        <aop:aspect ref="myAdvice">
            <!-- 前置通知 -->
            <aop:before method="before" pointcut-ref="cutPoint"/>
            <!-- 后置通知 如果需要拿到返回值 则要指明返回值对应的参数名称-->
            <aop:after-returning method="afterReturning" pointcut-ref="cutPoint" returning="result"/>
            <!-- 环绕通知 -->
            <aop:around method="around" pointcut-ref="cutPoint"/>
            <!-- 后置异常 如果需要拿到异常 则要指明异常对应的参数名称 -->
            <aop:after-throwing method="afterException" pointcut-ref="cutPoint" throwing="exception"/>
            <!-- 最终通知 -->
            <aop:after method="after" pointcut-ref="cutPoint"/>
        </aop:aspect>
    </aop:config>

</beans>
```

#### 2.4 测试切面

```java
@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:aop.xml")
public class AopTest {

	@Autowired
    private OrderService orderService;

    @Test
    public void queryOrder() {
 		orderService.queryOrder(1001L);
    }
  
    @Test
    public void createOrder() {
    	orderService.createOrder(new Order(1001L, "苹果", new Date()));
    }
}
```

#### 2.5 执行结果

```
before前置通知, 方法queryOrder, 参数[1001]
around环绕通知, 前
after后置通知
around环绕通知, 后
afterReturning后置通知, 返回Order(id=1001, productName=苹果, orderTime=Fri May 03 12:53:50 CST 2019)
```
```
before前置通知, 方法createOrder, 参数[Order(id=1001, productName=苹果, orderTime=Fri May 03 12:54:19 CST 2019)]
around环绕通知, 前
after后置通知
afterException异常通知, java.lang.ArithmeticException: / by zero
```

## 附： 关于切面表达式的说明

切面表达式遵循以下格式：

```shell
execution(modifiers-pattern? ret-type-pattern declaring-type-pattern?name-pattern(param-pattern)
            throws-pattern?)
```

- 除了返回类型模式，名字模式和参数模式以外，所有的部分都是可选的;
-  `*`，它代表了匹配任意的返回类型; 
- `()` 匹配了一个不接受任何参数的方法， 而 `(..)` 匹配了一个接受任意数量参数的方法（零或者更多）。 模式 `(*)` 匹配了一个接受一个任何类型的参数的方法。 模式 `(*,String)` 匹配了一个接受两个参数的方法，第一个可以是任意类型，第二个则必须是String类型。

下面给出一些常见切入点表达式的例子。

- 任意公共方法的执行：

  ```java
  execution(public * *(..))
  ```

- 任何一个以“set”开始的方法的执行：

  ```java
  execution(* set*(..))
  ```

- `AccountService` 接口的任意方法的执行：

  ```java
  execution(* com.xyz.service.AccountService.*(..))
  ```

- 定义在service包里的任意方法的执行：

  ```java
  execution(* com.xyz.service.*.*(..))
  ```

- 定义在service包或者子包里的任意方法的执行：

  ```java
  execution(* com.xyz.service..*.*(..))
  ```

- 在service包里的任意连接点（在Spring AOP中只是方法执行） ：

  ```java
  within(com.xyz.service.*)
  ```

- 在service包或者子包里的任意连接点（在Spring AOP中只是方法执行） ：

  ```
  within(com.xyz.service..*)
  ```

- 实现了 `AccountService` 接口的代理对象的任意连接点（在Spring AOP中只是方法执行） ：

  ```
  this(com.xyz.service.AccountService)
  ```

更多表达式可以参考官方文档：[Declaring a Pointcut](https://docs.spring.io/spring/docs/5.1.3.RELEASE/spring-framework-reference/core.html#aop-pointcuts)
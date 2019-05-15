# spring AOP（注解方式）

## 目录<br/>
<a href="#一说明">一、说明</a><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<a href="#11-项目结构说明">1.1 项目结构说明</a><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<a href="#12-依赖说明">1.2 依赖说明</a><br/>
<a href="#二spring-aop">二、spring aop</a><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#21-创建待切入接口及其实现类">2.1 创建待切入接口及其实现类</a><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#22-创建自定义切面类">2.2 创建自定义切面类</a><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#23-配置切面">2.3 配置切面</a><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#24-测试切面">2.4 测试切面</a><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#25--切面执行顺序">2.5  切面执行顺序</a><br/>
<a href="#附-关于切面表达式的说明">附： 关于切面表达式的说明</a><br/>
## 正文<br/>


## 一、说明

### 1.1 项目结构说明

1. 切面配置位于com.qs.config下AopConfig.java文件；
2. 自定义切面位于advice下，其中CustomAdvice是标准的自定义切面，FirstAdvice和SecondAdvice用于测试多切面共同作用于同一个被切入点时的执行顺序；
3. OrderService是待切入方法。

<div align="center"> <img src="https://github.com/qshomewy/SpringNotes/blob/master/pictures/spring-aop-annotation.png"/> </div>



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
	
	Integer deleteOrder(Long id);
}
```

```java
@Service
public class OrderServiceImpl implements OrderService {

	public Order queryOrder(Long id) {
		return new Order(id, "苹果", new Date());
	}

	public Integer createOrder(Order order) {
	    // 模拟异常
		int i = 1 / 0;
		return i;
	}

	public Integer deleteOrder(Long id) {
		return 1;
	}
}
```

#### 2.2 创建自定义切面类

注：@Pointcut的值可以是多个切面表达式的组合。

```java
@Aspect
@Component// 除了加上@Aspect外，还需要声明为spring的组件 @Aspect只是一个切面声明
public class CustomAdvice {
	
	/**
     * 使用 || , or  表示或
     * 使用 && , and 表示与
     * ! 表示非
     */
	@Pointcut("execution(* com.qs.service.OrderService.*(..)) && !execution(* com.qs.service.OrderService.deleteOrder(..))")
	private void pointCut() {
		
	}

	@Before("pointCut()")
	public void before(JoinPoint joinPoint) {
		String name = joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		System.out.println("before前置通知, 方法" + name + ", 传参" + Arrays.toString(args));
	}
	
	// 抛出异常后不会被执行
	@AfterReturning(pointcut = "pointCut()", returning = "result")
	// returning 参数用于指定返回结果与哪一个参数绑定
	public void afterReturning(JoinPoint joinPoint, Object result) {
		System.out.println("afterReturning后置结果通知, 返回" + result);
	}
	
	@Around("pointCut()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("around环绕通知, 前");
        // 调用目标方法
		Object proceed = joinPoint.proceed();
        System.out.println("around环绕通知, 后");
        return proceed;
	}
	
	@AfterThrowing(pointcut = "pointCut()", throwing = "exception")
	public void afterException(JoinPoint joinPoint, Exception exception) {
		System.out.println("afterException异常通知, " + exception);
	}
	
	//  总会执行 但是不能访问到返回值
	@After("pointCut()")
	public void after(JoinPoint joinPoint) {
		System.out.println("after后置通知");
	}
}
```

#### 2.3 配置切面

```java
@Configuration
@ComponentScan("com.qs.*")
@EnableAspectJAutoProxy// 开启@Aspect注解支持, 等价于<aop:aspectj-autoproxy>
public class AopConfig {
}
```

#### 2.4 测试切面

```java
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AopConfig.class)
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
	
	/**
     * 多个切面作用于同一个切入点时, 可以用@Order指定切面的执行顺序
     * 优先级高的切面在切入方法前执行的通知(before)会优先执行,
     * 但是位于方法后执行的通知(after,afterReturning)反而会延后执行
     */
    @Test
    public void deleteOrder() {
        orderService.deleteOrder(1001L);
    }
}
```

#### 2.5  切面执行顺序

- 多个切面作用于同一个切入点时，可以用@Order指定切面的执行顺序

- 优先级高的切面在切入方法前执行的通知(before)会优先执行，但是位于方法后执行的通知(after,afterReturning)反而会延后执行，类似于同心圆原理。

  <div align="center"> <img src="https://github.com/qshomewy/SpringNotes/blob/master/pictures/aop执行顺序.png"/> </div>



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
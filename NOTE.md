# 介绍
- 实体类与数据库字段可以不显式的定义，是**约定大于配置**的一种体现
- Mybatis 做到了代码与sql语句的分离，更加灵活、清晰、维护更方便（区别与JdbcTemplate）
- Mybatis需要开发人员自己编写sql （区别与Hibernate）

![images](http://www.coderandyli.com/mybatis01.png)

## 目录结构

### 
```aidl
.
├── annotations               --- 注解包
├── binding
├── builder                   --- Base package for the Configuration building code
│   ├── annotation
│   └── xml
├── cache                     --- 缓存模块
│   ├── decorators            --- 缓存的装饰者类： FifoCache、LruCache等
│   └── impl                  --- 缓存的最基本实现 PerpetualCache
├── cursor
│   └── defaults
├── datasource                --- 数据源
│   ├── jndi
│   ├── pooled                --- 简单的单线程池数据源
│   └── unpooled              --- 简单数据源
├── exceptions                
├── executor                  --- SQL执行器（真正与数据库交互的）
│   ├── keygen
│   ├── loader
│   │   ├── cglib
│   │   └── javassist
│   ├── parameter             --- SQL参数
│   ├── result                 
│   ├── resultset             --- SQL结果集
│   └── statement             --- SQL语句执行
├── io                        --- IO的工具包
├── jdbc
├── lang
├── logging
│   ├── commons
│   ├── jdbc
│   ├── jdk14
│   ├── log4j
│   ├── log4j2
│   ├── nologging
│   ├── slf4j
│   └── stdout
├── mapping
├── parsing
├── plugin                        --- 插件(sql拦截)
├── reflection
│   ├── factory
│   ├── invoker
│   ├── property
│   └── wrapper
├── scripting
│   ├── defaults
│   └── xmltags                 --- xml 动态SQL语法标签及解释器， SqlNode为最小语法单元, 解释器入口DynamicSqlSource.getBoundSql 
├── session                     --- SQL 会话
│   └── defaults
├── transaction                 --- 事务
│   ├── jdbc
│   └── managed
└── type

```

# Session 模块

- SqlSessionFactoryBuilder
    - 使用建造者模式创建SqlSessionFactory（非标准的建造者模式）
    > 构建SqlSessionFactory前，需要先构建Configuration，而构建Configuration非常复杂
比如：配置的读取、解析、创建n多对象等，为了隐藏Configuration的创建过程，对程序员透明，引入了SqlSessionFactoryBuilder

- SqlSessionFactory
    >  通过重载多个 openSession() 函数，支持通过组合 autoCommit、Executor、Transaction 等不同参数，来创建 SqlSession 对象
 标准的工厂模式通过type类创建继承同一父类的不同子类对象，而这里通过传递不同的参数创建同一个对象，所以更像建造者模式。

- SqlSessionFactoryBuilder 与 SqlSessionFactory 的作用为了创建 SqlSession，没有其他作用，
> 所以，我更建议参照 Spring 的设计思路，把 SqlSessionFactoryBuilder 和 SqlSessionFactory 的逻辑，放到一个叫“ApplicationContext”的类中。让这个类来全权负责读入配置文件，创建 Congfiguration，生成 SqlSession。 

-  SqlSession
    - 对外提供了用户和数据库之间交互所需的所有方法，隐藏了底层的细节，默认实现类是**DefaultSqlSession**
    - Mybatis工作的主要顶层API，表示和数据库交互时的会话，完成数据库的CURD操作， SqlSession将执行sql的业务逻辑委托给了**Executur**（查看Executor的唯一实现类DefaultSqlSession）, 很多操作都委托给了Executor
    
- Configuration：MyBatis所有的配置信息都保存在Configuration对象之中，配置文件中的大部分配置都会存储到该类中

# Executor
- Executor: MyBatis执行器，Mybatis调度的核心，负责SQL语句的生成和查询缓存的维护
- Executor 相关类的主要作用是执行sql的业务逻辑
- Executor是个接口，BaseExecutor是其抽象类
- SimpleExecutor、BatchExecutor、、ReuseExecutor是BaseExecutor的三个继承类
- 使用了**模板模式**，BatchExecutor作为一个抽象类，一个算法骨架，将某些步骤退出到其继承类中实现

> 具体执行逻辑待补充

## ErrorContext
- 基于 ThreadLocal 实现的线程唯一的单例
```

public class ErrorContext {

  private static final ThreadLocal<ErrorContext> LOCAL = new ThreadLocal<ErrorContext>();

  private ErrorContext() {
  }

  public static ErrorContext instance() {
    ErrorContext context = LOCAL.get();
    if (context == null) {
      context = new ErrorContext();
      LOCAL.set(context);
    }
    return context;
  }
}

```

# cache
- Cache接口是整个缓存功能的定义
- PerpetualCache 是最起初的缓存类
- decorators包下是包裹PerpetualCache类的装饰器类


# scripting 
- 基于**解释器模式**对Mybatis自定义的标签进行解析
- 解释器入口DynamicSqlSource.getBoundSql


# Plugin 模块
- Mybatis Plugin 主要拦截MyBatis 在sql执行过程中涉及的一些方法
## 实现原理
- 基于职责链模式+动态代理实现
> 1. SQL执行过程中， Mybatis会创建Executor（newExecutor()）、StatementHandler()、ParameterHandler(newParameterHandler())、ResultSetHandler（newResultSetHandler()） 这几个类的对象
> 会调用interceptorChain.pluginAll()方法，通过动态代理，生成代理对象。

## @Intercepts
- 标注拦截的目标方法

### 默认允许拦截的类和方法
类 | 方法 | 备注
---|---|---
Executor | update、query、flushStatements、commit、rollback、getTransaction、close、isClosed | sql执行者，会创建以下三个对象
ParameterHandler |  getParameterObject、setParameters | 设置 SQL 中的占位符参数
StatementHandler | prepare、parameterize、batch、update、query | 执行 SQL 语句
ResultSetHandler | handleResultSets、handleCursorResultSets | 封装执行结果

# logging 日志模块
Mybatis的日志模块并没有直接采用Slf4j(简单日志门面)，而是通过**适配器模式**，适配各个不同日志框架（Log4j、Logback）

# 一级缓存和二级缓存
- 查询执行流程: 二级缓存 -> 一级缓存 -> 数据库。
## 一级缓存
### 概述
- 即本地缓存，默认开启，仅仅会对一个会话(sqlSession)中的数据进行缓存
- 一级缓存不能关闭，但其提供了两个不同的的作用域 
    - SESSION(数据库会话内部共享), 其生命周期与SqlSession一致
    - STATEMENT（只对当前的statement有效）;
- 集群环境下可能会引起脏数据，建议设定作用范围为Statement。

### 执行时序图
![images](https://pic4.zhimg.com/v2-abec086d662525dba2502a541d944ec3_r.jpg)

### 本地缓存存在的问题
1. SESSION作用域时多节点查询问题
- 假如同一查询sql，在两个节点查询时，两个节点都有自己的一级缓存，两个节点都不在查库，此时节点1对数据update，那么节点1本地缓存会被刷新，
但节点2并没有刷新，此时节点2查询的数据是错误的。
- 解决方式: 将本地缓存的作用域设置为STATEMENT（可以理解为关闭了本地缓存）

## 二级缓存
![images](https://pic4.zhimg.com/80/v2-65b50fa087add440f70e29ce85aa624b_1440w.jpg)
- 利用【装饰者模式】，通过CachingExecutor对BaseExecutor装饰，实现二级缓存
- 二级缓存默认不开启，需要在对应的sql映射文件中声明<cache/>
- 【二级缓存】源码阅读入口：CachingExecutor#query

# datasource （数据源）

# 执行过程

 
# Reference
- 《设计模式》
- [Mybatis运行原理](https://zhuanlan.zhihu.com/p/97879019)
- [Mybatis](https://mybatis.org/mybatis-3/zh/configuration.html)
- [聊聊MyBatis缓存机制(美团)](https://zhuanlan.zhihu.com/p/33179093)
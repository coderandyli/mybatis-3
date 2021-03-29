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
├── builder
│   ├── annotation
│   └── xml
├── cache                     --- 缓存模块
│   ├── decorators            --- 缓存的装饰者类： FifoCache、LruCache等
│   └── impl                  --- 缓存的最基本实现 PerpetualCache
├── cursor
│   └── defaults
├── datasource
│   ├── jndi
│   ├── pooled
│   └── unpooled
├── exceptions                
├── executor                   --- SQL执行器
│   ├── keygen
│   ├── loader
│   │   ├── cglib
│   │   └── javassist
│   ├── parameter              --- SQL参数
│   ├── result                 
│   ├── resultset              --- SQL结果集
│   └── statement              --- SQL语句执行
├── io
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


```
.
├── annotations
│   ├── Arg.java
│   ├── AutomapConstructor.java
│   ├── CacheNamespace.java
│   ├── CacheNamespaceRef.java
│   ├── Case.java
│   ├── ConstructorArgs.java
│   ├── Delete.java
│   ├── DeleteProvider.java
│   ├── Flush.java
│   ├── Insert.java
│   ├── InsertProvider.java
│   ├── Lang.java
│   ├── Many.java
│   ├── MapKey.java
│   ├── Mapper.java
│   ├── One.java
│   ├── Options.java
│   ├── Param.java
│   ├── Property.java
│   ├── Result.java
│   ├── ResultMap.java
│   ├── ResultType.java
│   ├── Results.java
│   ├── Select.java
│   ├── SelectKey.java
│   ├── SelectProvider.java
│   ├── TypeDiscriminator.java
│   ├── Update.java
│   ├── UpdateProvider.java
│   └── package-info.java
├── binding
│   ├── BindingException.java
│   ├── MapperMethod.java
│   ├── MapperProxy.java
│   ├── MapperProxyFactory.java
│   ├── MapperRegistry.java
│   └── package-info.java
├── builder
│   ├── BaseBuilder.java
│   ├── BuilderException.java
│   ├── CacheRefResolver.java
│   ├── IncompleteElementException.java
│   ├── InitializingObject.java
│   ├── MapperBuilderAssistant.java
│   ├── ParameterExpression.java
│   ├── ResultMapResolver.java
│   ├── SqlSourceBuilder.java
│   ├── StaticSqlSource.java
│   ├── annotation
│   │   ├── MapperAnnotationBuilder.java
│   │   ├── MethodResolver.java
│   │   ├── ProviderContext.java
│   │   ├── ProviderMethodResolver.java
│   │   ├── ProviderSqlSource.java
│   │   └── package-info.java
│   ├── package-info.java
│   └── xml
│       ├── XMLConfigBuilder.java
│       ├── XMLIncludeTransformer.java
│       ├── XMLMapperBuilder.java
│       ├── XMLMapperEntityResolver.java
│       ├── XMLStatementBuilder.java
│       ├── mybatis-3-config.dtd
│       ├── mybatis-3-mapper.dtd
│       ├── mybatis-config.xsd
│       ├── mybatis-mapper.xsd
│       └── package-info.java
├── cache
│   ├── Cache.java
│   ├── CacheException.java
│   ├── CacheKey.java
│   ├── NullCacheKey.java
│   ├── TransactionalCacheManager.java
│   ├── decorators
│   │   ├── BlockingCache.java
│   │   ├── FifoCache.java
│   │   ├── LoggingCache.java
│   │   ├── LruCache.java
│   │   ├── ScheduledCache.java
│   │   ├── SerializedCache.java
│   │   ├── SoftCache.java
│   │   ├── SynchronizedCache.java
│   │   ├── TransactionalCache.java
│   │   ├── WeakCache.java
│   │   └── package-info.java
│   ├── impl
│   │   ├── PerpetualCache.java
│   │   └── package-info.java
│   └── package-info.java
├── cursor
│   ├── Cursor.java
│   ├── defaults
│   │   ├── DefaultCursor.java
│   │   └── package-info.java
│   └── package-info.java
├── datasource
│   ├── DataSourceException.java
│   ├── DataSourceFactory.java
│   ├── jndi
│   │   ├── JndiDataSourceFactory.java
│   │   └── package-info.java
│   ├── package-info.java
│   ├── pooled
│   │   ├── PoolState.java
│   │   ├── PooledConnection.java
│   │   ├── PooledDataSource.java
│   │   ├── PooledDataSourceFactory.java
│   │   └── package-info.java
│   └── unpooled
│       ├── UnpooledDataSource.java
│       ├── UnpooledDataSourceFactory.java
│       └── package-info.java
├── exceptions
│   ├── ExceptionFactory.java
│   ├── IbatisException.java
│   ├── PersistenceException.java
│   ├── TooManyResultsException.java
│   └── package-info.java
├── executor
│   ├── BaseExecutor.java
│   ├── BatchExecutor.java
│   ├── BatchExecutorException.java
│   ├── BatchResult.java
│   ├── CachingExecutor.java
│   ├── ErrorContext.java
│   ├── ExecutionPlaceholder.java
│   ├── Executor.java
│   ├── ExecutorException.java
│   ├── ResultExtractor.java
│   ├── ReuseExecutor.java
│   ├── SimpleExecutor.java
│   ├── keygen
│   │   ├── Jdbc3KeyGenerator.java
│   │   ├── KeyGenerator.java
│   │   ├── NoKeyGenerator.java
│   │   ├── SelectKeyGenerator.java
│   │   └── package-info.java
│   ├── loader
│   │   ├── AbstractEnhancedDeserializationProxy.java
│   │   ├── AbstractSerialStateHolder.java
│   │   ├── CglibProxyFactory.java
│   │   ├── JavassistProxyFactory.java
│   │   ├── ProxyFactory.java
│   │   ├── ResultLoader.java
│   │   ├── ResultLoaderMap.java
│   │   ├── WriteReplaceInterface.java
│   │   ├── cglib
│   │   │   ├── CglibProxyFactory.java
│   │   │   ├── CglibSerialStateHolder.java
│   │   │   └── package-info.java
│   │   ├── javassist
│   │   │   ├── JavassistProxyFactory.java
│   │   │   ├── JavassistSerialStateHolder.java
│   │   │   └── package-info.java
│   │   └── package-info.java
│   ├── package-info.java
│   ├── parameter
│   │   ├── ParameterHandler.java
│   │   └── package-info.java
│   ├── result
│   │   ├── DefaultMapResultHandler.java
│   │   ├── DefaultResultContext.java
│   │   ├── DefaultResultHandler.java
│   │   ├── ResultMapException.java
│   │   └── package-info.java
│   ├── resultset
│   │   ├── DefaultResultSetHandler.java
│   │   ├── ResultSetHandler.java
│   │   ├── ResultSetWrapper.java
│   │   └── package-info.java
│   └── statement
│       ├── BaseStatementHandler.java
│       ├── CallableStatementHandler.java
│       ├── PreparedStatementHandler.java
│       ├── RoutingStatementHandler.java
│       ├── SimpleStatementHandler.java
│       ├── StatementHandler.java
│       ├── StatementUtil.java
│       └── package-info.java
├── io
│   ├── ClassLoaderWrapper.java
│   ├── DefaultVFS.java
│   ├── ExternalResources.java
│   ├── JBoss6VFS.java
│   ├── ResolverUtil.java
│   ├── Resources.java
│   ├── VFS.java
│   └── package-info.java
├── jdbc
│   ├── AbstractSQL.java
│   ├── Null.java
│   ├── RuntimeSqlException.java
│   ├── SQL.java
│   ├── ScriptRunner.java
│   ├── SelectBuilder.java
│   ├── SqlBuilder.java
│   ├── SqlRunner.java
│   └── package-info.java
├── lang
│   ├── UsesJava7.java
│   ├── UsesJava8.java
│   └── package-info.java
├── logging
│   ├── Log.java
│   ├── LogException.java
│   ├── LogFactory.java
│   ├── commons
│   │   ├── JakartaCommonsLoggingImpl.java
│   │   └── package-info.java
│   ├── jdbc
│   │   ├── BaseJdbcLogger.java
│   │   ├── ConnectionLogger.java
│   │   ├── PreparedStatementLogger.java
│   │   ├── ResultSetLogger.java
│   │   ├── StatementLogger.java
│   │   └── package-info.java
│   ├── jdk14
│   │   ├── Jdk14LoggingImpl.java
│   │   └── package-info.java
│   ├── log4j
│   │   ├── Log4jImpl.java
│   │   └── package-info.java
│   ├── log4j2
│   │   ├── Log4j2AbstractLoggerImpl.java
│   │   ├── Log4j2Impl.java
│   │   ├── Log4j2LoggerImpl.java
│   │   └── package-info.java
│   ├── nologging
│   │   ├── NoLoggingImpl.java
│   │   └── package-info.java
│   ├── package-info.java
│   ├── slf4j
│   │   ├── Slf4jImpl.java
│   │   ├── Slf4jLocationAwareLoggerImpl.java
│   │   ├── Slf4jLoggerImpl.java
│   │   └── package-info.java
│   └── stdout
│       ├── StdOutImpl.java
│       └── package-info.java
├── mapping
│   ├── BoundSql.java
│   ├── CacheBuilder.java
│   ├── DatabaseIdProvider.java
│   ├── DefaultDatabaseIdProvider.java
│   ├── Discriminator.java
│   ├── Environment.java
│   ├── FetchType.java
│   ├── MappedStatement.java
│   ├── ParameterMap.java
│   ├── ParameterMapping.java
│   ├── ParameterMode.java
│   ├── ResultFlag.java
│   ├── ResultMap.java
│   ├── ResultMapping.java
│   ├── ResultSetType.java
│   ├── SqlCommandType.java
│   ├── SqlSource.java
│   ├── StatementType.java
│   ├── VendorDatabaseIdProvider.java
│   └── package-info.java
├── package-info.java
├── parsing
│   ├── GenericTokenParser.java
│   ├── ParsingException.java
│   ├── PropertyParser.java
│   ├── TokenHandler.java
│   ├── XNode.java
│   ├── XPathParser.java
│   └── package-info.java
├── plugin
│   ├── Interceptor.java
│   ├── InterceptorChain.java
│   ├── Intercepts.java
│   ├── Invocation.java
│   ├── NOTE.md
│   ├── Plugin.java
│   ├── PluginException.java
│   ├── Signature.java
│   └── package-info.java
├── reflection
│   ├── ArrayUtil.java
│   ├── DefaultReflectorFactory.java
│   ├── ExceptionUtil.java
│   ├── Jdk.java
│   ├── MetaClass.java
│   ├── MetaObject.java
│   ├── OptionalUtil.java
│   ├── ParamNameResolver.java
│   ├── ParamNameUtil.java
│   ├── ReflectionException.java
│   ├── Reflector.java
│   ├── ReflectorFactory.java
│   ├── SystemMetaObject.java
│   ├── TypeParameterResolver.java
│   ├── factory
│   │   ├── DefaultObjectFactory.java
│   │   ├── ObjectFactory.java
│   │   └── package-info.java
│   ├── invoker
│   │   ├── AmbiguousMethodInvoker.java
│   │   ├── GetFieldInvoker.java
│   │   ├── Invoker.java
│   │   ├── MethodInvoker.java
│   │   ├── SetFieldInvoker.java
│   │   └── package-info.java
│   ├── package-info.java
│   ├── property
│   │   ├── PropertyCopier.java
│   │   ├── PropertyNamer.java
│   │   ├── PropertyTokenizer.java
│   │   └── package-info.java
│   └── wrapper
│       ├── BaseWrapper.java
│       ├── BeanWrapper.java
│       ├── CollectionWrapper.java
│       ├── DefaultObjectWrapperFactory.java
│       ├── MapWrapper.java
│       ├── ObjectWrapper.java
│       ├── ObjectWrapperFactory.java
│       └── package-info.java
├── scripting
│   ├── LanguageDriver.java
│   ├── LanguageDriverRegistry.java
│   ├── ScriptingException.java
│   ├── defaults
│   │   ├── DefaultParameterHandler.java
│   │   ├── RawLanguageDriver.java
│   │   ├── RawSqlSource.java
│   │   └── package-info.java
│   ├── package-info.java
│   └── xmltags
│       ├── ChooseSqlNode.java
│       ├── DynamicContext.java
│       ├── DynamicSqlSource.java
│       ├── ExpressionEvaluator.java
│       ├── ForEachSqlNode.java
│       ├── IfSqlNode.java
│       ├── MixedSqlNode.java
│       ├── OgnlCache.java
│       ├── OgnlClassResolver.java
│       ├── OgnlMemberAccess.java
│       ├── SetSqlNode.java
│       ├── SqlNode.java
│       ├── StaticTextSqlNode.java
│       ├── TextSqlNode.java
│       ├── TrimSqlNode.java
│       ├── VarDeclSqlNode.java
│       ├── WhereSqlNode.java
│       ├── XMLLanguageDriver.java
│       ├── XMLScriptBuilder.java
│       └── package-info.java
├── session
│   ├── AutoMappingBehavior.java
│   ├── AutoMappingUnknownColumnBehavior.java
│   ├── Configuration.java
│   ├── ExecutorType.java
│   ├── LocalCacheScope.java
│   ├── ResultContext.java
│   ├── ResultHandler.java
│   ├── RowBounds.java
│   ├── SqlSession.java
│   ├── SqlSessionException.java
│   ├── SqlSessionFactory.java
│   ├── SqlSessionFactoryBuilder.java
│   ├── SqlSessionManager.java
│   ├── TransactionIsolationLevel.java
│   ├── defaults
│   │   ├── DefaultSqlSession.java
│   │   ├── DefaultSqlSessionFactory.java
│   │   └── package-info.java
│   └── package-info.java
├── transaction
│   ├── Transaction.java
│   ├── TransactionException.java
│   ├── TransactionFactory.java
│   ├── jdbc
│   │   ├── JdbcTransaction.java
│   │   ├── JdbcTransactionFactory.java
│   │   └── package-info.java
│   ├── managed
│   │   ├── ManagedTransaction.java
│   │   ├── ManagedTransactionFactory.java
│   │   └── package-info.java
│   └── package-info.java
└── type
    ├── Alias.java
    ├── ArrayTypeHandler.java
    ├── BaseTypeHandler.java
    ├── BigDecimalTypeHandler.java
    ├── BigIntegerTypeHandler.java
    ├── BlobByteObjectArrayTypeHandler.java
    ├── BlobInputStreamTypeHandler.java
    ├── BlobTypeHandler.java
    ├── BooleanTypeHandler.java
    ├── ByteArrayTypeHandler.java
    ├── ByteArrayUtils.java
    ├── ByteObjectArrayTypeHandler.java
    ├── ByteTypeHandler.java
    ├── CharacterTypeHandler.java
    ├── ClobReaderTypeHandler.java
    ├── ClobTypeHandler.java
    ├── DateOnlyTypeHandler.java
    ├── DateTypeHandler.java
    ├── DoubleTypeHandler.java
    ├── EnumOrdinalTypeHandler.java
    ├── EnumTypeHandler.java
    ├── FloatTypeHandler.java
    ├── InstantTypeHandler.java
    ├── IntegerTypeHandler.java
    ├── JapaneseDateTypeHandler.java
    ├── JdbcType.java
    ├── LocalDateTimeTypeHandler.java
    ├── LocalDateTypeHandler.java
    ├── LocalTimeTypeHandler.java
    ├── LongTypeHandler.java
    ├── MappedJdbcTypes.java
    ├── MappedTypes.java
    ├── MonthTypeHandler.java
    ├── NClobTypeHandler.java
    ├── NStringTypeHandler.java
    ├── ObjectTypeHandler.java
    ├── OffsetDateTimeTypeHandler.java
    ├── OffsetTimeTypeHandler.java
    ├── ShortTypeHandler.java
    ├── SimpleTypeRegistry.java
    ├── SqlDateTypeHandler.java
    ├── SqlTimeTypeHandler.java
    ├── SqlTimestampTypeHandler.java
    ├── SqlxmlTypeHandler.java
    ├── StringTypeHandler.java
    ├── TimeOnlyTypeHandler.java
    ├── TypeAliasRegistry.java
    ├── TypeException.java
    ├── TypeHandler.java
    ├── TypeHandlerRegistry.java
    ├── TypeReference.java
    ├── UnknownTypeHandler.java
    ├── YearMonthTypeHandler.java
    ├── YearTypeHandler.java
    ├── ZonedDateTimeTypeHandler.java
    └── package-info.java
```

# 使用步骤


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

-  SqlSession Mybatis工作的主要顶层API，表示和数据库交互时的会话，完成数据库的CURD操作， SqlSession将执行sql的业务逻辑委托给了**Executur**（查看Executor的唯一实现类DefaultSqlSession）, 很多操作都委托给了Executor

- Configuration：MyBatis所有的配置信息都保存在Configuration对象之中，配置文件中的大部分配置都会存储到该类中

# Executor
- Executor: MyBatis执行器，Mybatis调度的核心，负责SQL语句的生成和查询缓存的维护
- Executor 相关类的主要作用是执行sql的业务逻辑
- Executor是个接口，BaseExecutor是其抽象类
- BatchExecutor、SimpleExecutor、ReuseExecutor是BaseExecutor的三个继承类
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
## 一级缓存
- 即本地缓存，默认开启，仅仅会对一个会话(sqlSession)中的数据进行缓存

## 二级缓存

 
# Reference
- 《设计模式》
- [Mybatis运行原理](https://zhuanlan.zhihu.com/p/97879019)
- [Mybatis](https://mybatis.org/mybatis-3/zh/configuration.html)
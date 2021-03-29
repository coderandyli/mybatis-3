/**
 *    Copyright 2009-2020 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.plugin;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.transaction.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 拦截器链
 *
 * @author Clinton Begin
 */
public class InterceptorChain {

  /**
   * 拦截器集合
   */
  private final List<Interceptor> interceptors = new ArrayList<>();

  /**
   * 生成动态代理对象
   *
   * 被 {@code {@link org.apache.ibatis.session.Configuration#newExecutor(Transaction, ExecutorType)}}调用
   * 被 {@code {@link org.apache.ibatis.session.Configuration#newParameterHandler(MappedStatement, Object, BoundSql)}}调用
   * 被 {@code {@link org.apache.ibatis.session.Configuration#newStatementHandler(Executor, MappedStatement, Object, RowBounds, ResultHandler, BoundSql)}}调用
   * 被 {@code {@link org.apache.ibatis.session.Configuration#newResultSetHandler(Executor, MappedStatement, RowBounds, ParameterHandler, ResultHandler, BoundSql)}}调用
   * @param target
   * @return
   */
  public Object pluginAll(Object target) {
    for (Interceptor interceptor : interceptors) {
      target = interceptor.plugin(target);
      // 上面这行代码等于下面这行代码，target(代理对象)=target(目标对象)+interceptor(拦截器功能)
      // target = Plugin.wrap(target, interceptor);
    }
    return target;
  }

  /**
   * 添加拦截器
   * 被 {@code {@link org.apache.ibatis.session.Configuration#addInterceptor(Interceptor)}} 调用
   *
   * @param interceptor
   */
  public void addInterceptor(Interceptor interceptor) {
    interceptors.add(interceptor);
  }

  /**
   *  获取拦截器集合
   *
   * @return
   */
  public List<Interceptor> getInterceptors() {
    return Collections.unmodifiableList(interceptors);
  }

}

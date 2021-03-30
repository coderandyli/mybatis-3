/**
 *    Copyright 2009-2015 the original author or authors.
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
package org.apache.ibatis.session;

/**
 * 本地缓存作用范围
 * - SESSION: 数据库会话内部(sqlSession)共享，默认级别
 * - STATEMENT: 只对当前执行的statement有效（可以理解为关闭本地缓存）
 *
 * @author Eduardo Macarron
 */
public enum LocalCacheScope {
  SESSION,STATEMENT
}

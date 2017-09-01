/**
 * Copyright 2016-2017 ZTE Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onap.aai.esr.handle;

import com.google.gson.Gson;

import org.onap.aai.esr.dao.BaseDao;
import org.onap.aai.esr.dao.DaoManager;
import org.onap.aai.esr.entity.aai.BaseData;
import org.onap.aai.esr.exception.ExtsysException;
import org.onap.aai.esr.util.ExtsysUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * an abstract class for wrapper class.<br>
 * provide the common methods to process the DB request
 * 
 * @author 10159474
 */
public abstract class BaseHandler<T extends BaseData> {
  private static final Logger logger = LoggerFactory.getLogger(BaseHandler.class);

  public Gson gson = new Gson();

  /**
   * create entity.
   * 
   * @param data entity
   * @param resouceType resource type
   * @return entity
   * @throws ExtsysException when  DAO exception
   */
  @SuppressWarnings({"unchecked", "rawtypes"})
  public T create(T data, String resouceType) throws ExtsysException {
    T rtnData = null;
    boolean check = true;
    logger.info("BaseHandler:start create data.info:" + ExtsysUtil.objectToString(data));
    try {
      check = validity(data);
      if (check) {
        logger.error("validity check success!");
      } else {
        logger.error("validity check failed");
        throw new ExtsysException("", "validity check failed");
      }
    } catch (ExtsysException error) {
      logger.error("validity check failed,errorMsg:" + error.getErrorMsg());
      throw error;
    }
    try {      
      data.setId(ExtsysUtil.generateId());
      data.setCreateTime(ExtsysUtil.getNowTime());
      data.setCategory(resouceType);
      BaseDao dao = DaoManager.getInstance().getDao(resouceType);
      rtnData = (T) dao.create(data);
    } catch (ExtsysException error) {
      logger.error("BaseHandler:error while creating " + resouceType, error);
      throw error;
    }
    logger.info("BaseHandler:create data end.info:" + ExtsysUtil.objectToString(data));
    return rtnData;
  }

  /**
   * delete entity.
   * @param data entity
   * @param resouceType resource type
   * @throws ExtsysException  when  DAO exception
   */
  @SuppressWarnings({"rawtypes", "unchecked"})
  public void delete(T data, String resouceType) throws ExtsysException {
    logger.info("BaseHandler:start delete data.info:" + ExtsysUtil.objectToString(data));
    try {
      BaseDao dao = DaoManager.getInstance().getDao(resouceType);
      dao.delete(data);
    } catch (ExtsysException error) {
      logger.error("BaseHandler:error while deleting " + resouceType, error);
      throw error;
    }
    logger.info("BaseHandler:delete data end");
  }

  /**
   * delete entity by query condition.
   * @param queryParam query parameter
   * @param resouceType resource type
   * @throws ExtsysException when  DAO exception
   */
  @SuppressWarnings({"rawtypes", "unchecked"})
  public void delete(Map<String, String> queryParam, String resouceType) throws ExtsysException {
    logger.info("BaseHandler:start delete data by condition.info:"
        + ExtsysUtil.objectToString(queryParam));
    List<T> datas;
    try {
      BaseDao dao = DaoManager.getInstance().getDao(resouceType);
      datas = dao.query(queryParam);
      for (T data : datas) {
        delete(data, resouceType);
      }
    } catch (ExtsysException error) {
      logger.error("BaseHandler:error while deleting " + resouceType, error);
      throw error;
    }

  }

  /**
   * update entity.
   * @param data entity
   * @param filter update condition
   * @param resouceType resource type
   * @throws ExtsysException when  DAO exception
   */
  @SuppressWarnings({"rawtypes", "unchecked"})
  public void update(T data, String filter, String resouceType) throws ExtsysException {
    logger.info("BaseHandler:start update data .info:" + ExtsysUtil.objectToString(data)
        + " filter:" + filter);
    try {
      BaseDao dao = DaoManager.getInstance().getDao(resouceType);
      data.setId(null);
      dao.update(data, filter);

    } catch (ExtsysException error) {
      logger.error("BaseHandler:error while updating " + resouceType, error);
      throw error;
    }
    logger.info("BaseHandler:update data end ");
  }

  /**
   * query entity.
   * @param queryParam query parameter
   * @param resouceType resource type
   * @return List entity list
   * @throws ExtsysException when  DAO exception
   */
  @SuppressWarnings({"rawtypes", "unchecked"})
  public List<T> query(Map<String, String> queryParam, String resouceType) throws ExtsysException {
    logger.info("BaseHandler:start query data .info:" + ExtsysUtil.objectToString(queryParam));
    List<T> datas = null;
    try {
      BaseDao dao = DaoManager.getInstance().getDao(resouceType);
      datas = dao.query(queryParam);

    } catch (ExtsysException error) {
      logger.error("BaseHandler:error while querying " + resouceType, error);
      throw error;
    }
    logger.info("BaseHandler: query data end .info:" + ExtsysUtil.objectToString(datas));
    return datas;
  }

  /** 
   * query entity.
   * @param filter query condition
   * @param resouceType resource type
   * @return list entity
   * @throws ExtsysException when  DAO exception
   */
  @SuppressWarnings({"rawtypes", "unchecked"})
  public List<T> unionQuery(String filter, String resouceType) throws ExtsysException {
    logger.info("BaseHandler:start union query data.fliter:" + filter);
    List<T> datas = null;
    try {
      BaseDao dao = DaoManager.getInstance().getDao(resouceType);
      datas = dao.unionQuery(filter);

    } catch (ExtsysException error) {
      logger.error("BaseHandler:error while union querying " + resouceType, error);
      throw error;
    }
    logger.info("BaseHandler:union query data end .info:" + ExtsysUtil.objectToString(datas));
    return datas;
  }

  /** 
   * delete entity.
   * @param filter delete condition
   * @param resouceType resource type
   * @return delete entity num
   * @throws ExtsysException when  DAO exception
   */
  @SuppressWarnings({"rawtypes", "unchecked"})
  public int unionDelete(String filter, String resouceType) throws ExtsysException {
    logger.info("BaseHandler:start delete query data.fliter:" + filter);
    int num;
    try {
      BaseDao dao = DaoManager.getInstance().getDao(resouceType);
      num = dao.unionDelete(filter);

    } catch (ExtsysException error) {
      logger.error("BaseHandler:error while union delete " + resouceType, error);
      throw error;
    }
    logger.info("BaseHandler:union delete data end .num:" + num);
    return num;
  }

  /**
   * check if the esr parameter is correct in the  external system.
   * @param data esr entity
   * @return  if esr parameter  correct return true else return false
   * @throws ExtsysException ExtsysException when external system error
   */
  
  public abstract boolean validity(T data) throws ExtsysException;

}

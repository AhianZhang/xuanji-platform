/*
 * Copyright (c) 2020.  ahianzhang@gmail.com
 */

package com.ahianzhang.auth.domain.client;

/**
 * @author ahianzhang
 */
public interface ClientService {
   /**
    *
    * 创建 client 应用
    * @see  <a href=https://www.oauth.com/oauth2-servers/client-registration/client-id-secret/>
    * @param client 应用
    * @return client
    */
   Client create(Client client);

   /**
    * 查询应用
    * @param id id
    * @return client
    */
   Client findClientById(Integer id);

   /**
    * 查询应用
    * @param clientId 应用 id
    * @return client 应用信息
    */
   Client findClientByClientId(String clientId);

   /**
    * 删除应用
    * @param id id
    */
   void delete(Integer id);

   /**
    * 更新应用
    * @param client 应用
    */
   void update(Client client);

   Client findByClientName(String clientName);
}

package com.qs.springboot.constant;

import javax.websocket.Session;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * @author : qshomewy
 * @description :
 */
public interface Constant {

    String USER_NAME="username";

    Map<String, Session> nameAndSession = new ConcurrentHashMap<>();
}

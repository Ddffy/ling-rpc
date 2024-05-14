package com.ling.example.common.service;

import com.ling.example.common.model.User;

/**
 * @author lingcode
 * @version 1.0
 * i
 */
public interface UserService {

    /**
     *
     * @param user
     * @return
     */
    User getUser(User user);

    /**
     * 测试mock
     * @return
     */
    default short getNumber(){
        return 1;
    }

}

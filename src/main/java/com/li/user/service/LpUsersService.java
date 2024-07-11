package com.li.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.li.user.model.LpUsers;
import com.li.user.model.qo.LoginQo;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
* @author lzx
* @description 针对表【lp_users】的数据库操作Service
* @createDate 2024-06-21 18:18:39
*/
public interface LpUsersService extends IService<LpUsers> {

    Map<String, String> login(LoginQo loginQo);

    boolean register(LpUsers users);

    LpUsers getUserInfo(HttpServletRequest request);

    boolean addUser(LpUsers users);

    boolean deleteUserById(Integer id);
}

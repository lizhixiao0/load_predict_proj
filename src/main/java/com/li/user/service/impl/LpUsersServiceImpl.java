package com.li.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.li.user.mapper.LpUsersMapper;
import com.li.user.model.LpUsers;
import com.li.user.model.qo.LoginQo;
import com.li.user.service.LpUsersService;
import com.li.user.utils.result.ResultCodeEnum;
import com.li.user.utils.utils.JwtUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lzx
 * @description 针对表【lp_users】的数据库操作Service实现
 * @createDate 2024-06-21 18:18:39
 */
@Service
public class LpUsersServiceImpl extends ServiceImpl<LpUsersMapper, LpUsers>
        implements LpUsersService {

    @Override
    public Map<String, String> login(LoginQo loginQo) {
        String username = loginQo.getUsername();
        String password = loginQo.getPassword();
        String role = loginQo.getRole();
        QueryWrapper<LpUsers> wrapper = new QueryWrapper<>();
        Map<String, String> msg = new HashMap<>();        // 信息不完整
        msg.put("code", "");
        msg.put("data", "");
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password) || StringUtils.isEmpty(role)) {
            msg.put("code", String.valueOf(ResultCodeEnum.PARAM_ERROR.getCode()));
            msg.put("data",ResultCodeEnum.PARAM_ERROR.getMessage());
            return msg;
        }

        wrapper.eq("username", username);
        LpUsers one = this.getOne(wrapper);
        //用户信息不完整&找不到用户
        if (one == null) {
            msg.put("code", String.valueOf(ResultCodeEnum.FETCH_USERINFO_ERROR.getCode()));
            msg.put("data",ResultCodeEnum.FETCH_USERINFO_ERROR.getMessage());
            return msg;
        }
        //检测密码和权限是否正确
        if (!one.getPassword().equals(password) || !one.getRole().equals(role)) {
            msg.put("code", String.valueOf(ResultCodeEnum.PARAM_ERROR.getCode()));
            msg.put("data",ResultCodeEnum.PARAM_ERROR.getMessage());
            return msg;
        }
        //密码正确，身份权限正确
        String jwtToken = JwtUtils.getJwtToken(username, role);
        msg.put("code", String.valueOf(ResultCodeEnum.SUCCESS.getCode()));
        msg.put("data",jwtToken);
        return msg;
    }

    @Override
    public boolean register(LpUsers users) {
        return this.save(users);
    }

    @Override
    public LpUsers getUserInfo(HttpServletRequest request) {
        Integer id= JwtUtils.getIdByJwtToken(request);
        QueryWrapper<LpUsers> wrapper=new QueryWrapper<>();
        wrapper.eq("id",id);
        LpUsers one = this.getOne(wrapper);
        return one;
    }

    @Override
    public boolean addUser(LpUsers users) {
        if (users==null){
            return false;
        }
        if (StringUtils.isBlank(users.getUsername())){
            return false;
        }
        boolean save = this.save(users);
        return save;
    }

    @Override
    public boolean deleteUserById(Integer id) {
        return this.removeById(id);
    }
}





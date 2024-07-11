package com.li.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.li.user.model.LpUsers;
import com.li.user.model.qo.LoginQo;
import com.li.user.model.qo.searchUserQo;
import com.li.user.service.LpUsersService;
import com.li.user.utils.result.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("user")
public class UserController {
    @Resource
    private LpUsersService lpUsersService;


    /**
     * 登录
     * @return code ,data
     */
    @PostMapping("login")
    public Result login(@RequestBody LoginQo loginQo){
        Map<String,String>  msg=lpUsersService.login(loginQo);
        return Result.ok(msg);
    }
    //2.根据token获取用户信息
    @GetMapping("getMemberInfo")
    public Result getUserInfo(HttpServletRequest request) {   //获取token，解码用户信息
        LpUsers users=lpUsersService.getUserInfo(request);

        return Result.ok(users);
    }

    /**
     * 注册
     * @param users
     * @return
     */
    @PostMapping("register")
    public Result register(@RequestBody LpUsers users){
        boolean b=lpUsersService.register(users);
        return b?Result.ok():Result.fail();
    }

    /**
     * 根据id获取用户
     * @param id 用户id
     * @return 用户实体
     */
    @GetMapping("getById/{id}")
    public Result<LpUsers> getById(@PathVariable int id){
        LpUsers byId = lpUsersService.getById(id);
        return Result.ok(byId);
    }

    /**
     * 添加用户
     * @param users
     * @return
     */
    @PostMapping("addUser")
    public Result addUser(@RequestBody LpUsers users ){
        boolean b=lpUsersService.addUser(users);
        return b?Result.ok():Result.fail();
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @GetMapping("deleteUser/{id}")
    public Result deleteUser(@PathVariable Integer id){
        boolean b=lpUsersService.deleteUserById(id);
        return b?Result.ok():Result.fail();
    }

    /**
     * 条件分页查询
     * @param searchUserQo 查询条件
     * @param current 当前页
     * @param limit 每页显示个数
     * @return
     */
    @PostMapping("searchUser/{current}/{limit}")
    public Result searchUser(@RequestBody(required = false) searchUserQo searchUserQo,
                             @PathVariable long current,
                             @PathVariable long limit){

        Page<LpUsers> page = new Page<>(current, limit);
        QueryWrapper<LpUsers> queryWrapper = new QueryWrapper<>();

        if (searchUserQo.getUsername() != null && !searchUserQo.getUsername().isEmpty()) {
            queryWrapper.like("username", searchUserQo.getUsername());
        }
        if (searchUserQo.getRole() != null && !searchUserQo.getRole().isEmpty()) {
            queryWrapper.eq("status", searchUserQo.getRole());
        }
        if (searchUserQo.getStatus() != null && !searchUserQo.getStatus().isEmpty()) {
            queryWrapper.eq("status", searchUserQo.getStatus());
        }

        Page<LpUsers> userPage = lpUsersService.page(page, queryWrapper);
        return Result.ok(userPage);
    }

    /**
     * 更新用户信息（管理员）
     * @param users 用户实体
     * @return
     */
    @PostMapping("editUserInfo")
    public Result editUserInfo(@RequestBody LpUsers users){
        return lpUsersService.updateById(users)?Result.ok():Result.fail();
    }



}

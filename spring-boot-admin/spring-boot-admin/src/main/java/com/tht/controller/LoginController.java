package com.tht.controller;

import com.tht.entity.User;
import com.tht.utils.Result;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户登录
 * @author tht
 * @date 2024-01-21 15:55
 */
@RestController
@RequestMapping("user")
public class LoginController {
    /**
     * 登录
     * @return 用户登录
     */
    @PostMapping
    public Result<Map<String, Object>> login(@RequestBody User user) {

        System.out.println("=============="+user);

        Map<String, Object> map = new HashMap<>();
        map.put("token","admin");
        return Result.ok(map);
    }
    /**
     * 获取用户信息
     * @return 用户信息
     */
    @GetMapping("/info")
    public Result<Map<String, Object>> info() {
        Map<String, Object> map = new HashMap<>();
        map.put("roles","[admin]");
        map.put("name","admin");
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return Result.ok(map);
    }
    /**
     * 退出
     * @return 退出登录
     */
    @PostMapping("/logout")
    public Result logout(){
        return Result.ok();
    }
}

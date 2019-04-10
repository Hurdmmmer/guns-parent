package com.stylefeng.guns.rest.modular.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.api.model.UserInfoModel;
import com.stylefeng.guns.api.model.UserModel;
import com.stylefeng.guns.api.user.UserAPI;
import com.stylefeng.guns.rest.common.CurrentUser;
import com.stylefeng.guns.rest.common.exception.BizExceptionEnum;
import com.stylefeng.guns.rest.modular.vo.ResponseVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    @Reference(interfaceClass = UserAPI.class)
    private UserAPI userAPI;

    @PostMapping("/registry")
    public ResponseVo registry(@RequestBody @Valid UserModel userModel) {

        boolean success = this.userAPI.registry(userModel);
        if (success) {
            return ResponseVo.ok("注册成功");
        }
        return ResponseVo.serviceFail(BizExceptionEnum.REGISTRY_FAIL);
    }

    @PostMapping("/check")
    public ResponseVo check(String username) {
        if (StringUtils.isEmpty(username)) {
            return ResponseVo.serviceFail("用户名不能为空");
        }

        boolean notExist = userAPI.checkUsername(username);
        if (notExist) {
            return ResponseVo.ok("用户名不存在");
        }
        return ResponseVo.serviceFail("用户名已存在");
    }

    @GetMapping("/logout")
    public ResponseVo logout() {
        return ResponseVo.ok("用户退出成功");
    }

    @GetMapping("/getUserInfo")
    public ResponseVo getUserInfo() {
        String uuid = CurrentUser.currentUserId();
        if (StringUtils.isEmpty(uuid)) {
            return ResponseVo.serviceFail("用户未登录");
        }
        UserInfoModel userInfo = userAPI.getUserInfo(Integer.valueOf(uuid));
        if (userInfo != null) {
            return ResponseVo.ok(userInfo);
        }
        return ResponseVo.serviceFail("获取用户信息失败");
    }

    @PostMapping("/updateUserInfo")
    public ResponseVo updateUser(UserInfoModel userInfoModel) {
        String uuid = CurrentUser.currentUserId();
        if (StringUtils.isEmpty(uuid)) {
            return ResponseVo.serviceFail("用户未登录");
        }

        if (!uuid.equals(userInfoModel.getId()+"")) {
            return ResponseVo.serviceFail("请修改个人用户信息");
        }

        UserInfoModel userInfo = userAPI.updateUserInfo(userInfoModel);
        if (userInfo != null) {
            return ResponseVo.ok(userInfo);
        }
        return ResponseVo.serviceFail("获取用户信息失败");
    }

}

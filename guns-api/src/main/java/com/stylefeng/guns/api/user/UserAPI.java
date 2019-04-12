package com.stylefeng.guns.api.user;

import com.stylefeng.guns.api.user.model.UserInfoModel;
import com.stylefeng.guns.api.user.model.UserModel;

public interface UserAPI {
    int login(String username, String password);

    boolean registry(UserModel userModel);

    boolean checkUsername(String username);

    UserInfoModel getUserInfo(int uuid);

    UserInfoModel updateUserInfo(UserInfoModel userInfoModel);
}

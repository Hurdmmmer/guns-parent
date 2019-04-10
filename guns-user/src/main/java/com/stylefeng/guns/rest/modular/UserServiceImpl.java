package com.stylefeng.guns.rest.modular;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.api.model.UserInfoModel;
import com.stylefeng.guns.api.model.UserModel;
import com.stylefeng.guns.api.user.UserAPI;
import com.stylefeng.guns.core.util.MD5Util;
import com.stylefeng.guns.rest.common.persistence.dao.MoocUserTMapper;
import com.stylefeng.guns.rest.common.persistence.model.MoocUserT;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
@Service(interfaceClass = UserAPI.class)
public class UserServiceImpl implements UserAPI {

    @Autowired
    private MoocUserTMapper moocUserTMapper;

    @Override
    public int login(String username, String password) {
        MoocUserT moocUserT = new MoocUserT();
        moocUserT.setUserName(username);
        MoocUserT dbUser = moocUserTMapper.selectOne(moocUserT);
        if (dbUser != null) {
            String encrypt = MD5Util.encrypt(password);
            if (dbUser.getUserPwd().equals(encrypt)) {
                return dbUser.getUuid();
            }
        }
        return 0;
    }

    @Override
    @Transactional
    public boolean registry(UserModel userModel) {

        MoocUserT moocUserT = new MoocUserT();

        BeanUtils.copyProperties(userModel, moocUserT);

        moocUserT.setUserPwd(MD5Util.encrypt(moocUserT.getUserPwd()));

        Integer result = moocUserTMapper.insert(moocUserT);

        return result > 0;
    }

    @Override
    public boolean checkUsername(String username) {

        EntityWrapper<MoocUserT> wrapper = new EntityWrapper<>();

        wrapper.eq("user_name", username);

        return !(moocUserTMapper.selectCount(wrapper) > 0);
    }

    @Override
    public UserInfoModel getUserInfo(int uuid) {
        MoocUserT dbUser = moocUserTMapper.selectById(uuid);
        if (dbUser == null) {
            return null;
        }
        return do2UserInfo(dbUser);
    }

    @Override
    @Transactional
    public UserInfoModel updateUserInfo(UserInfoModel userInfoModel) {
        Integer result = moocUserTMapper.updateById(do2User(userInfoModel));
        if (result > 0) {
            MoocUserT dbUser = moocUserTMapper.selectById(userInfoModel.getId());
            return do2UserInfo(dbUser);
        }
        return userInfoModel;
    }

    private MoocUserT do2User(UserInfoModel userInfoModel) {
        MoocUserT moocUserT = new MoocUserT();

        moocUserT.setUserSex(userInfoModel.getSex());
        moocUserT.setUserPhone(userInfoModel.getPhone());
        moocUserT.setUpdateTime(new Date());
        moocUserT.setNickName(userInfoModel.getNickname());
        moocUserT.setLifeState(userInfoModel.getLifeState());
        moocUserT.setHeadUrl(userInfoModel.getHeadAddress());
        moocUserT.setEmail(userInfoModel.getEmail());
        moocUserT.setBirthday(userInfoModel.getBirthday());
        moocUserT.setBiography(userInfoModel.getBiography());
        moocUserT.setAddress(userInfoModel.getAddress());
        moocUserT.setUuid(userInfoModel.getId());

        return moocUserT;
    }

    private UserInfoModel do2UserInfo(MoocUserT moocUserT) {
        UserInfoModel userInfoModel = new UserInfoModel();

        userInfoModel.setUsername(moocUserT.getUserName());
        userInfoModel.setUpdateTime(moocUserT.getUpdateTime().getTime());
        userInfoModel.setSex(moocUserT.getUserSex());
        userInfoModel.setPhone(moocUserT.getUserPhone());
        userInfoModel.setNickname(moocUserT.getNickName());
        userInfoModel.setLifeState(moocUserT.getLifeState());
        userInfoModel.setId(moocUserT.getUuid());
        userInfoModel.setHeadAddress(moocUserT.getHeadUrl());
        userInfoModel.setEmail(moocUserT.getEmail());
        userInfoModel.setCreateTime(moocUserT.getBeginTime().getTime());
        userInfoModel.setBirthday(moocUserT.getBirthday());
        userInfoModel.setBiography(moocUserT.getBiography());
        userInfoModel.setAddress(moocUserT.getAddress());

        return userInfoModel;
    }
}

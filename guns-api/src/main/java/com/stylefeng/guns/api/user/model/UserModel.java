package com.stylefeng.guns.api.user.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class UserModel implements Serializable {
    @NotBlank(message = "用户名不能为空")
    @Length(min = 5, message = "用户名长度应大于 5 个字符")
    private String userName;
    @NotBlank(message = "密码不能为空")
    @Length(min = 8, message = "密码长度应大于 8 个字符")
    private String userPwd;
    @Pattern(regexp = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", message = "邮箱格式不正确")
    private String email;
    private String userPhone;
    private String address;
}

package com.uniview.project0719.dto;

import com.uniview.project0719.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDTO {
    private Integer id;
    private String nickName;
    private String wxId;
    private String avatar;
    private String gender;

    public UserInfoDTO(UserInfo userInfo){
        BeanUtils.copyProperties(userInfo,this);
    }
}

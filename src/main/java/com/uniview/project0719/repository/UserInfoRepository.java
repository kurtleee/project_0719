package com.uniview.project0719.repository;

import com.uniview.project0719.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer>, JpaSpecificationExecutor<UserInfo> {
    UserInfo findUserInfoById(Integer id);
    UserInfo findUserInfoByWxId(String wxId);
    UserInfo findUserInfoByWxIdAndPassword(String phone, String password);
}
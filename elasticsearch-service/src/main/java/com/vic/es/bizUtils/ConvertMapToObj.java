package com.vic.es.bizUtils;

import com.vic.es.entity.fm.FmMemberBaseResponse;

import java.util.Map;

public class ConvertMapToObj {
    public static FmMemberBaseResponse toFmMemberBaseModel(Map<String, Object> item) {
        FmMemberBaseResponse response = new FmMemberBaseResponse();
        response.setMemberId((String) item.get("memberId"));
        response.setNickName((String) item.get("nickName"));
        response.setMobile((String) item.get("mobile"));
        response.setRegisterTime((String) item.get("registerTime"));
        response.setPhotoUrl((String) item.get("photoUrl"));
        return response;
    }
}

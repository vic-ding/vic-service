package com.vic.es.feign.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberBaseQuery {
    // 注册时间-开始时间
    private String startTime;
    // 注册时间-结束时间
    private String endTime;


    private String partnerId;

    // 手机号
    private String mobile;

    // 微信昵称
    private String nickName;

    private List<String> appIds;

    // 会员卡
    private String paidId;

    private Integer filterMobileStatus;

    private Integer pageIndex;

    private Integer pageSize;

    // 渠道来源
    private String registerChannel;

    private String firstOrderBeginDate;
    private String firstOrderEndDate;
    private String firstStoreName;

    private String startUpdateTime;

    private String endUpdateTime;


    // 生日
    private String birthday;

    private List<Object> sortValues;

    // 会员等级
    private String memberLevel;
    private String memberLevelCode;
    private Boolean isDefaultMemberLevel;


    private String statusFlag;

    // 会员类型：0-非付费会员，1-付费会员
    private String memberType;

    // 会员卡集合
    private List<String> paidIdList;

    private String totalConsumeNumLow;
    private String totalConsumeNumHigh;
    private String totalConsumeAmtLow;
    private String totalConsumeAmtHigh;
    private BigDecimal cardAmtLow;
    private BigDecimal cardAmtHigh;


    // 会员id
    private String memberId;

    // 智能搜索功能：会员ID、手机号码、会员昵称筛选
    private String multiField;

}

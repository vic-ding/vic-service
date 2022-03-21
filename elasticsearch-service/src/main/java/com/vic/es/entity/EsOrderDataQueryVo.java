package com.vic.es.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EsOrderDataQueryVo {

    // 商户号
    private String partnerId;

    // 门店号
    private String storeId;

    // 创建时间-开始时间
    private String createTimeStart;

    // 创建时间-结束时间
    private String createTimeEnd;

    // 支付状态
    private String payState;

    // 业务类型
    private List<Integer> bizTypeList;

    // 订单类型
    private List<Integer> orderTypeList;

    // 售后单状态
    private List<Integer> afterSalesStatusList;

}

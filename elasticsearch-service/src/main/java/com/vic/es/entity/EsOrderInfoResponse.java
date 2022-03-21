package com.vic.es.entity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EsOrderInfoResponse {

    private String key;

    // 订单金额
    private double orderAmt;

    // 订单数
    private long ordersNum;

    // 客单价
    private double customerUnitPrice;

}

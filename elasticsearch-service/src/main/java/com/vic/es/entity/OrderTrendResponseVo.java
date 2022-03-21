package com.vic.es.entity;

import lombok.Data;

import java.util.List;

@Data
public class OrderTrendResponseVo {

    private List<EsOrderInfoResponse> esOrderInfoResponseList;

}

package com.vic.es.config.es.entity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EsGroupByDataResponse {

    private String key;

    private long validOrdersNum;

    private double settlementAmount;

}

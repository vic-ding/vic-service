package com.vic.es.service;

import com.vic.es.entity.EsOrderDataQueryVo;
import com.vic.es.entity.OrderTrendResponseVo;


public interface EsService {

    OrderTrendResponseVo queryGroupByData(EsOrderDataQueryVo queryVo);

}

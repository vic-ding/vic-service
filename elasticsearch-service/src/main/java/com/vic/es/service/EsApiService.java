package com.vic.es.service;

import com.vic.es.config.es.entity.CreateIndexRequest;
import com.vic.es.entity.EsOrderDataQueryVo;
import com.vic.es.entity.OrderTrendResponseVo;


public interface EsApiService {

    Boolean createIndex(CreateIndexRequest request);

    OrderTrendResponseVo queryGroupByData(EsOrderDataQueryVo queryVo);

}

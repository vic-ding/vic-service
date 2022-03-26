package com.vic.es.service;

import com.vic.es.config.es.entity.DocumentUserResponse;
import com.vic.es.config.es.entity.IndexRequestVo;
import com.vic.es.config.es.entity.UserDocumentRequestVo;
import com.vic.es.entity.EsOrderDataQueryVo;
import com.vic.es.entity.OrderTrendResponseVo;


public interface EsApiService {

    String createIndex(IndexRequestVo request);

    Boolean isExistsIndex(IndexRequestVo request);

    Boolean deleteIndex(IndexRequestVo request);

    String addDocument(UserDocumentRequestVo request);

    DocumentUserResponse getDocument(UserDocumentRequestVo request);

    OrderTrendResponseVo queryGroupByData(EsOrderDataQueryVo queryVo);

}

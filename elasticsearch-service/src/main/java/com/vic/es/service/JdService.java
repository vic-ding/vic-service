package com.vic.es.service;

import com.vic.es.entity.jd.BulkAddGoodsDocumentRequest;
import com.vic.es.entity.jd.SearchAllRequest;
import com.vic.es.entity.jd.SearchAllResponse;


public interface JdService {

    String bulkAddDocument(BulkAddGoodsDocumentRequest request);

    SearchAllResponse searchAll(SearchAllRequest request);

}

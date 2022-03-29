package com.vic.es.service;

import com.vic.es.entity.BulkAddDocumentRequest;
import com.vic.es.entity.jd.SearchAllRequest;
import com.vic.es.entity.jd.SearchAllResponse;


public interface FmService {

    String bulkAddDocument(BulkAddDocumentRequest request);

    SearchAllResponse searchAll(SearchAllRequest request);

}

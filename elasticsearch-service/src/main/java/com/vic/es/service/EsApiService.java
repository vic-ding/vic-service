package com.vic.es.service;

import com.vic.es.entity.*;


public interface EsApiService {

    String createIndex(IndexRequestVo request);

    Boolean isExistsIndex(IndexRequestVo request);

    Boolean deleteIndex(IndexRequestVo request);

    String addDocument(AddUserDocumentRequestVo request);

    GetDocumentResponse getDocument(GetDocumentRequestVo request);

    Boolean isExistsDocument(IsExistsDocumentRequestVo request);

    String deleteDocument(DeleteDocumentRequestVo request);

    String updateDocument(UpdateDocumentRequestVo request);

    String bulkDocument(BulkAddUserDocumentRequestDto request);

}

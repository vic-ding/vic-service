package com.vic.es.service;

import com.vic.es.entity.BulkAddDocumentRequest;
import com.vic.es.entity.fm.FmMemberBaseRequestVo;
import com.vic.es.entity.fm.FmMemberBaseResponse;
import com.vic.es.utils.PageResult;

import java.util.List;


public interface FmService {

    void bulkAddDocument(BulkAddDocumentRequest request);

    PageResult<List<FmMemberBaseResponse>> search(FmMemberBaseRequestVo request);

}

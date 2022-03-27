package com.vic.es.service.impl;

import com.alibaba.fastjson.JSON;
import com.vic.base.util.MapUtils;
import com.vic.es.config.es.EsService;
import com.vic.es.entity.*;
import com.vic.es.service.EsApiService;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EsApiServiceImpl implements EsApiService {

    private EsService esService;

    @Autowired
    private void setEsService(EsService esService) {
        this.esService = esService;
    }

    @Override
    public String createIndex(IndexRequestVo request) {
        return esService.createIndex(request.getIndex());
    }

    @Override
    public Boolean isExistsIndex(IndexRequestVo request) {
        return esService.isExistsIndex(request.getIndex());
    }

    @Override
    public Boolean deleteIndex(IndexRequestVo request) {
        return esService.deleteIndex(request.getIndex());
    }

    @Override
    public String addDocument(AddUserDocumentRequestVo request) {
        AddUserDocumentRequestDto requestDto = new AddUserDocumentRequestDto();
        BeanUtils.copyProperties(request, requestDto);
        return esService.addDocument(requestDto.getIndex(), JSON.toJSONString(request.getUserDocumentInfo()));
    }

    @Override
    public GetDocumentResponse getDocument(GetDocumentRequestVo request) {
        GetDocumentRequestDto requestDto = new GetDocumentRequestDto();
        BeanUtils.copyProperties(request, requestDto);
        Map<String, Object> sourceAsMap = esService.getDocument(requestDto.getIndex(), requestDto.getId());
        GetDocumentResponse response = new GetDocumentResponse();
        if (sourceAsMap != null) {
            try {
                UserDocumentInfo userDocumentInfo = (UserDocumentInfo) MapUtils.mapToObject(sourceAsMap, UserDocumentInfo.class);
                BeanUtils.copyProperties(userDocumentInfo, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return response;
    }

    @Override
    public Boolean isExistsDocument(IsExistsDocumentRequestVo request) {
        IsExistsDocumentRequestDto requestDto = new IsExistsDocumentRequestDto();
        BeanUtils.copyProperties(request, requestDto);
        return esService.isExistsDocument(requestDto.getIndex(), requestDto.getId());
    }

    @Override
    public String deleteDocument(DeleteDocumentRequestVo request) {
        DeleteDocumentRequestDto requestDto = new DeleteDocumentRequestDto();
        BeanUtils.copyProperties(request, requestDto);
        return esService.deleteDocument(requestDto.getIndex(), requestDto.getId());
    }

    @Override
    public String updateDocument(UpdateDocumentRequestVo request) {
        UpdateDocumentRequestDto requestDto = new UpdateDocumentRequestDto();
        BeanUtils.copyProperties(request, requestDto);
        return esService.updateDocument(requestDto.getIndex(), requestDto.getId(), JSON.toJSONString(request.getUserDocumentInfo()));
    }

    @Override
    public String bulkDocument(BulkAddUserDocumentRequestDto request) {
        BulkAddUserDocumentRequestDto requestDto = new BulkAddUserDocumentRequestDto();
        BeanUtils.copyProperties(request, requestDto);

        BulkRequest bulkRequest = new BulkRequest();
        for (UserDocumentInfo obj : requestDto.getUserDocumentInfoList()) {
            bulkRequest.add(new IndexRequest(requestDto.getIndex()).source(JSON.toJSONString(obj), XContentType.JSON));
        }
        Boolean bulkDocumentBoolean = esService.bulkDocument(bulkRequest);
        if (bulkDocumentBoolean) {
            return "批量添加文档失败";
        }
        return "批量添加文档成功";
    }

}

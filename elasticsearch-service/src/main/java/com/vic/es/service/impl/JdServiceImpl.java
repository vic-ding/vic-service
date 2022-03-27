package com.vic.es.service.impl;

import com.alibaba.fastjson.JSON;
import com.vic.es.config.es.EsService;
import com.vic.es.entity.BulkAddUserDocumentRequestDto;
import com.vic.es.entity.UserDocumentInfo;
import com.vic.es.entity.jd.BulkAddGoodsDocumentRequest;
import com.vic.es.entity.jd.JdGoodsResponse;
import com.vic.es.service.JdService;
import com.vic.es.utils.HtmlParseUtil;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class JdServiceImpl implements JdService {

    private EsService esService;

    @Autowired
    private void setEsService(EsService esService) {
        this.esService = esService;
    }

    @Override
    public String bulkAddDocument(BulkAddGoodsDocumentRequest request) {
        List<JdGoodsResponse> jdGoodsResponseList = null;
        try {
            jdGoodsResponseList = HtmlParseUtil.parseJD(request.getKeyword());
        } catch (IOException e) {
            e.printStackTrace();
        }

        BulkRequest bulkRequest = new BulkRequest();
        if (jdGoodsResponseList != null) {
            for (JdGoodsResponse obj : jdGoodsResponseList) {
                bulkRequest.add(new IndexRequest(request.getIndex()).source(JSON.toJSONString(obj), XContentType.JSON));
            }
        }
        Boolean bulkDocumentBoolean = esService.bulkDocument(bulkRequest);
        if (bulkDocumentBoolean) {
            return "批量添加文档失败";
        }
        return "批量添加文档成功";
    }
}

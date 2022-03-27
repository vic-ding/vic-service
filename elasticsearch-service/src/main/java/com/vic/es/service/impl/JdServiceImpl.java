package com.vic.es.service.impl;

import com.alibaba.fastjson.JSON;
import com.vic.es.config.es.EsService;
import com.vic.es.constant.IndexConstant;
import com.vic.es.entity.jd.BulkAddGoodsDocumentRequest;
import com.vic.es.entity.jd.JdGoodsResponse;
import com.vic.es.entity.jd.SearchAllRequest;
import com.vic.es.entity.jd.SearchAllResponse;
import com.vic.es.service.JdService;
import com.vic.es.utils.HtmlParseUtil;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Override
    public SearchAllResponse searchAll(SearchAllRequest request) {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        if (StringUtils.isNotEmpty(request.getGoodsName())) {
            boolQueryBuilder.must(QueryBuilders.termQuery("goodsName", request.getGoodsName()));
        }
        if (StringUtils.isNotEmpty(request.getMaxPrice())) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("price").lte(request.getMaxPrice()));
        }
        if (StringUtils.isNotEmpty(request.getMinPrice())) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("price").gte(request.getMinPrice()));
        }
        if (StringUtils.isNotEmpty(request.getShopName())) {
            boolQueryBuilder.must(QueryBuilders.termQuery("shopName", request.getShopName()));
        }
        List<Map<String, Object>> objects = esService.searchAll(IndexConstant.JD_INDEX, boolQueryBuilder);

        List<JdGoodsResponse> goodsResponseList = objects.stream().map(this::convertToBaseRowModel).collect(Collectors.toList());

        SearchAllResponse searchAllResponse = new SearchAllResponse();
        searchAllResponse.setGoodsResponseList(goodsResponseList);
        return searchAllResponse;
    }


    private JdGoodsResponse convertToBaseRowModel(Map<String, Object> item) {
        JdGoodsResponse response = new JdGoodsResponse();
        response.setGoodsName((String) item.get("goodsName"));
        response.setPrice((Double) item.get("price"));
        response.setShopName((String) item.get("shopName"));
        return response;
    }
}

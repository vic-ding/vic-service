package com.vic.es.service.impl;

import com.vic.es.config.es.EsService;
import com.vic.es.constant.IndexConstant;
import com.vic.es.entity.BulkAddDocumentRequest;
import com.vic.es.entity.jd.JdGoodsResponse;
import com.vic.es.entity.jd.SearchAllRequest;
import com.vic.es.entity.jd.SearchAllResponse;
import com.vic.es.feign.client.FmFeignClient;
import com.vic.es.feign.entity.MemberBaseInfoResponse;
import com.vic.es.feign.entity.MemberBaseQuery;
import com.vic.es.service.FmService;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FmServiceImpl implements FmService {

    private FmFeignClient fmFeignClient;

    @Autowired
    private void setFmFeignClient(FmFeignClient fmFeignClient) {
        this.fmFeignClient = fmFeignClient;
    }

    private EsService esService;

    @Autowired
    private void setEsService(EsService esService) {
        this.esService = esService;
    }


    @Override
    public void bulkAddDocument(BulkAddDocumentRequest request) {
        MemberBaseQuery requestDto = new MemberBaseQuery();
        BeanUtils.copyProperties(request, requestDto);
        MemberBaseInfoResponse memberBaseInfoResponse = fmFeignClient.searchMemberInfo(requestDto);
        esService.bulkDocument(IndexConstant.FM_MEMBER_BASE, memberBaseInfoResponse.getMemberBase());
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
        //List<Map<String, Object>> objects = esService.searchAll(IndexConstant.JD_INDEX, boolQueryBuilder);

        //List<JdGoodsResponse> goodsResponseList = objects.stream().map(this::convertToBaseRowModel).collect(Collectors.toList());

        SearchAllResponse searchAllResponse = new SearchAllResponse();
        //searchAllResponse.setGoodsResponseList(goodsResponseList);
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

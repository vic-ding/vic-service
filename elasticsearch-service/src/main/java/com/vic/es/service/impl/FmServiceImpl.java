package com.vic.es.service.impl;

import com.vic.base.response.BaseResponse;
import com.vic.es.bizUtils.ConvertMapToObj;
import com.vic.es.config.es.EsService;
import com.vic.es.config.es.PageRequest;
import com.vic.es.config.es.SearchResult;
import com.vic.es.constant.FmMemberBaseConstant;
import com.vic.es.entity.BulkAddDocumentRequest;
import com.vic.es.entity.fm.FmMemberBaseRequestVo;
import com.vic.es.entity.fm.FmMemberBaseResponse;
import com.vic.es.feign.client.FmFeignClient;
import com.vic.es.feign.entity.MemberBaseInfoResponse;
import com.vic.es.feign.entity.MemberBaseQuery;
import com.vic.es.service.FmService;
import com.vic.es.utils.PageResult;
import com.vic.es.utils.PageUtil;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        BaseResponse<MemberBaseInfoResponse> memberBaseInfoResponse = fmFeignClient.searchMemberInfo(requestDto);
        if (memberBaseInfoResponse.getData() != null) {
            esService.bulkDocument(FmMemberBaseConstant.FM_MEMBER_BASE, memberBaseInfoResponse.getData().getMemberBase());
        } else {
        }
    }

    @Override
    public PageResult<List<FmMemberBaseResponse>> search(FmMemberBaseRequestVo request) {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        if (StringUtils.isNotEmpty(request.getPartnerId())) {
            boolQueryBuilder.must(QueryBuilders.termQuery(FmMemberBaseConstant.PARTNER_ID, request.getPartnerId()));
        }

        PageRequest pageRequest = new PageRequest();
        pageRequest.setPageIndex(PageUtil.convertPageIndex(request.getPageIndex(), request.getPageSize()) - 1);
        pageRequest.setPageSize(PageUtil.checkPageSize(request.getPageSize()));

        SearchResult searchResult = esService.search(FmMemberBaseConstant.FM_MEMBER_BASE, boolQueryBuilder, pageRequest);
        List<FmMemberBaseResponse> collect = searchResult.getMapList().stream().map(ConvertMapToObj::toFmMemberBaseModel).collect(Collectors.toList());

        PageResult<List<FmMemberBaseResponse>> result = new PageResult<>();
        result.setResult(collect);
        result.setTotal(searchResult.getTotal());
        return result;
    }


}

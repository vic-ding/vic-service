package com.vic.es.controller;


import com.alibaba.fastjson.JSONObject;
import com.vic.base.response.BaseResponse;
import com.vic.es.entity.BulkAddDocumentRequest;
import com.vic.es.entity.jd.BulkAddGoodsDocumentRequest;
import com.vic.es.entity.jd.SearchAllRequest;
import com.vic.es.entity.jd.SearchAllResponse;
import com.vic.es.service.FmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/fm", produces = "application/json")
@Slf4j
public class FmController {

    private FmService fmService;

    @Autowired
    private void setEsService(FmService fmService) {
        this.fmService = fmService;
    }


    @PostMapping(value = "/bulkAddDocument")
    public BaseResponse<String> bulkAddDocument(@RequestBody BulkAddDocumentRequest request) {
        try {
             fmService.bulkAddDocument(request);
        } catch (Exception e) {
            log.error("批量添加文档-失败，参数为 {}.", JSONObject.toJSONString(request));
            log.error("批量添加文档-失败，异常信息为 {}.", JSONObject.toJSONString(e));
            return BaseResponse.error("批量添加文档-失败");
        }
        return BaseResponse.success();
    }

    @PostMapping(value = "/searchAll")
    public BaseResponse<SearchAllResponse> searchAll(@RequestBody SearchAllRequest request) {
        SearchAllResponse response;
        try {
            response = fmService.searchAll(request);
        } catch (Exception e) {
            log.error("查询数据-失败，参数为 {}.", JSONObject.toJSONString(request));
            log.error("查询数据-失败，异常信息为 {}.", JSONObject.toJSONString(e));
            return BaseResponse.error("查询数据-失败");
        }
        return BaseResponse.success(response);
    }


}

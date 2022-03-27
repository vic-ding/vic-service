package com.vic.es.controller;


import com.alibaba.fastjson.JSONObject;
import com.vic.base.response.BaseResponse;
import com.vic.es.entity.jd.BulkAddGoodsDocumentRequest;
import com.vic.es.entity.jd.SearchAllRequest;
import com.vic.es.entity.jd.SearchAllResponse;
import com.vic.es.service.EsApiService;
import com.vic.es.service.JdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/jd", produces = "application/json")
@Slf4j
public class JdController {

    private JdService jdService;

    @Autowired
    private void setEsService(JdService jdService) {
        this.jdService = jdService;
    }


    @PostMapping(value = "/bulkAddDocument")
    public BaseResponse<String> bulkAddDocument(@RequestBody BulkAddGoodsDocumentRequest request) {
        String response;
        try {
            response = jdService.bulkAddDocument(request);
        } catch (Exception e) {
            log.error("批量添加文档-失败，参数为 {}.", JSONObject.toJSONString(request));
            log.error("批量添加文档-失败，异常信息为 {}.", JSONObject.toJSONString(e));
            return BaseResponse.error("批量添加文档-失败");
        }
        return BaseResponse.success(response);
    }

    @PostMapping(value = "/searchAll")
    public BaseResponse<SearchAllResponse> searchAll(@RequestBody SearchAllRequest request) {
        SearchAllResponse response;
        try {
            response = jdService.searchAll(request);
        } catch (Exception e) {
            log.error("查询数据-失败，参数为 {}.", JSONObject.toJSONString(request));
            log.error("查询数据-失败，异常信息为 {}.", JSONObject.toJSONString(e));
            return BaseResponse.error("查询数据-失败");
        }
        return BaseResponse.success(response);
    }


}

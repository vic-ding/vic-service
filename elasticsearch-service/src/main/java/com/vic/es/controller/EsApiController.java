package com.vic.es.controller;


import com.alibaba.fastjson.JSONObject;
import com.vic.base.response.BaseResponse;
import com.vic.es.entity.*;
import com.vic.es.service.EsApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/esApi", produces = "application/json")
@Slf4j
public class EsApiController {

    private EsApiService esApiService;

    @Autowired
    private void setEsService(EsApiService esApiService) {
        this.esApiService = esApiService;
    }

    @PostMapping(value = "/createIndex")
    public BaseResponse<String> createIndex(@RequestBody IndexRequestVo request) {
        String response;
        try {
            response = esApiService.createIndex(request);
        } catch (Exception e) {
            log.error("创建索引-失败，参数为 {}.", JSONObject.toJSONString(request));
            log.error("创建索引-失败，异常信息为 {}.", JSONObject.toJSONString(e));
            return BaseResponse.error("创建索引-失败");
        }
        return BaseResponse.success(response);
    }

    @PostMapping(value = "/isExistsIndex")
    public BaseResponse<Boolean> isExistsIndex(@RequestBody IndexRequestVo request) {
        Boolean response;
        try {
            response = esApiService.isExistsIndex(request);
        } catch (Exception e) {
            log.error("查询索引是否存在-失败，参数为 {}.", JSONObject.toJSONString(request));
            log.error("查询索引是否存在-失败，异常信息为 {}.", JSONObject.toJSONString(e));
            return BaseResponse.error("查询索引是否存在-失败");
        }
        return BaseResponse.success(response);
    }

    @PostMapping(value = "/deleteIndex")
    public BaseResponse<Boolean> deleteIndex(@RequestBody IndexRequestVo request) {
        Boolean response;
        try {
            response = esApiService.deleteIndex(request);
        } catch (Exception e) {
            log.error("删除索引-失败，参数为 {}.", JSONObject.toJSONString(request));
            log.error("删除索引-失败，异常信息为 {}.", JSONObject.toJSONString(e));
            return BaseResponse.error("删除索引-失败");
        }
        return BaseResponse.success(response);
    }

    @PostMapping(value = "/addDocument")
    public BaseResponse<String> addDocument(@RequestBody AddUserDocumentRequestVo request) {
        String response;
        try {
            response = esApiService.addDocument(request);
        } catch (Exception e) {
            log.error("添加文档-失败，参数为 {}.", JSONObject.toJSONString(request));
            log.error("添加文档-失败，异常信息为 {}.", JSONObject.toJSONString(e));
            return BaseResponse.error("添加文档-失败");
        }
        return BaseResponse.success(response);
    }

    @PostMapping(value = "/getDocument")
    public BaseResponse<GetDocumentResponse> getDocument(@RequestBody GetDocumentRequestVo request) {
        GetDocumentResponse response;
        try {
            response = esApiService.getDocument(request);
        } catch (Exception e) {
            log.error("获取文档-失败，参数为 {}.", JSONObject.toJSONString(request));
            log.error("获取文档-失败，异常信息为 {}.", JSONObject.toJSONString(e));
            return BaseResponse.error("获取文档-失败");
        }
        return BaseResponse.success(response);
    }

    @PostMapping(value = "/isExistsDocument")
    public BaseResponse<Boolean> isExistsDocument(@RequestBody IsExistsDocumentRequestVo request) {
        Boolean response;
        try {
            response = esApiService.isExistsDocument(request);
        } catch (Exception e) {
            log.error("判断文档是否存在-失败，参数为 {}.", JSONObject.toJSONString(request));
            log.error("判断文档是否存在-失败，异常信息为 {}.", JSONObject.toJSONString(e));
            return BaseResponse.error("判断文档是否存在-失败");
        }
        return BaseResponse.success(response);
    }

    @PostMapping(value = "/deleteDocument")
    public BaseResponse<String> deleteDocument(@RequestBody DeleteDocumentRequestVo request) {
        String response;
        try {
            response = esApiService.deleteDocument(request);
        } catch (Exception e) {
            log.error("删除文档-失败，参数为 {}.", JSONObject.toJSONString(request));
            log.error("删除文档-失败，异常信息为 {}.", JSONObject.toJSONString(e));
            return BaseResponse.error("删除文档-失败");
        }
        return BaseResponse.success(response);
    }

    @PostMapping(value = "/updateDocument")
    public BaseResponse<String> updateDocument(@RequestBody UpdateDocumentRequestVo request) {
        String response;
        try {
            response = esApiService.updateDocument(request);
        } catch (Exception e) {
            log.error("更新文档-失败，参数为 {}.", JSONObject.toJSONString(request));
            log.error("更新文档-失败，异常信息为 {}.", JSONObject.toJSONString(e));
            return BaseResponse.error("更新文档-失败");
        }
        return BaseResponse.success(response);
    }

    @PostMapping(value = "/bulkDocument")
    public BaseResponse<String> bulkDocument(@RequestBody BulkAddUserDocumentRequestDto request) {
        String response;
        try {
            response = esApiService.bulkDocument(request);
        } catch (Exception e) {
            log.error("批量添加文档-失败，参数为 {}.", JSONObject.toJSONString(request));
            log.error("批量添加文档-失败，异常信息为 {}.", JSONObject.toJSONString(e));
            return BaseResponse.error("批量添加文档-失败");
        }
        return BaseResponse.success(response);
    }


}

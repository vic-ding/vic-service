package com.vic.es.controller;


import com.alibaba.fastjson.JSONObject;
import com.vic.base.response.BaseResponse;
import com.vic.es.entity.EsOrderDataQueryVo;
import com.vic.es.entity.OrderTrendResponseVo;
import com.vic.es.service.EsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/es", produces = "application/json")
@Slf4j
public class EsController {

    private EsService esService;

    @Autowired
    private void setEsService(EsService esService) {
        this.esService = esService;
    }

    @PostMapping(value = "/queryGroupByData")
    public BaseResponse<OrderTrendResponseVo> queryGroupByData(@RequestBody EsOrderDataQueryVo queryVo) {

        OrderTrendResponseVo response;
        try {
            response = esService.queryGroupByData(queryVo);
        } catch (Exception e) {
            log.error("es聚合查询-失败，参数为 {}.", JSONObject.toJSONString(queryVo));
            return BaseResponse.error("es聚合查询-失败");
        }
        return BaseResponse.success(response);
    }

}

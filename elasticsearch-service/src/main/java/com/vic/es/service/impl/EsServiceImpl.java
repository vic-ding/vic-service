package com.vic.es.service.impl;

import com.vic.es.config.es.EsConstant;
import com.vic.es.config.es.EsOrderService;
import com.vic.es.config.es.entity.EsGroupByDataResponse;
import com.vic.es.entity.EsOrderDataQueryVo;
import com.vic.es.entity.EsOrderInfoResponse;
import com.vic.es.entity.OrderTrendResponseVo;
import com.vic.es.service.EsService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EsServiceImpl implements EsService {

    private EsOrderService esOrderService;

    @Autowired
    private void setEsService(EsOrderService esOrderService) {
        this.esOrderService = esOrderService;
    }

    @Override
    public OrderTrendResponseVo queryGroupByData(EsOrderDataQueryVo queryVo) {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        Script script = new Script(ScriptType.INLINE, "painless", "doc['partnerId'].value+':'+doc['storeId'].value", new HashMap<>());
        String indexName = "freemud-order-v4-*";
        List<EsGroupByDataResponse> groupByDataResponseList = esOrderService.getGroupByData(boolQueryBuilder, script, EsConstant.SETTLEMENT_AMOUNT, indexName);

        List<EsOrderInfoResponse> orderInfoResponseList = groupByDataResponseList.stream().map(t -> {
                    EsOrderInfoResponse esOrderInfoResponse = new EsOrderInfoResponse();
                    esOrderInfoResponse.setOrderAmt(t.getSettlementAmount());
                    esOrderInfoResponse.setOrdersNum(t.getValidOrdersNum());
                    esOrderInfoResponse.setKey(t.getKey());
                    return esOrderInfoResponse;
                }
        ).collect(Collectors.toList());

        OrderTrendResponseVo responseVo = new OrderTrendResponseVo();
        responseVo.setEsOrderInfoResponseList(orderInfoResponseList);
        return responseVo;
    }
}

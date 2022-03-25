package com.vic.es.service.impl;

import com.vic.es.config.es.EsService;
import com.vic.es.config.es.entity.CreateIndexRequest;
import com.vic.es.entity.EsOrderDataQueryVo;
import com.vic.es.entity.OrderTrendResponseVo;
import com.vic.es.service.EsApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EsApiServiceImpl implements EsApiService {

    private EsService esService;

    @Autowired
    private void setEsService(EsService esService) {
        this.esService = esService;
    }

    @Override
    public Boolean createIndex(CreateIndexRequest request) {
        return esService.createIndex(request.getIndex());
    }

    @Override
    public OrderTrendResponseVo queryGroupByData(EsOrderDataQueryVo queryVo) {

//        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
//        Script script = new Script(ScriptType.INLINE, "painless", "doc['partnerId'].value+':'+doc['storeId'].value", new HashMap<>());
//        String indexName = "freemud-order-v4-*";
//        List<EsGroupByDataResponse> groupByDataResponseList = esOrderService.getGroupByData(boolQueryBuilder, script, EsConstant.SETTLEMENT_AMOUNT, indexName);
//
//        List<EsOrderInfoResponse> orderInfoResponseList = groupByDataResponseList.stream().map(t -> {
//                    EsOrderInfoResponse esOrderInfoResponse = new EsOrderInfoResponse();
//                    esOrderInfoResponse.setOrderAmt(t.getSettlementAmount());
//                    esOrderInfoResponse.setOrdersNum(t.getValidOrdersNum());
//                    esOrderInfoResponse.setKey(t.getKey());
//                    return esOrderInfoResponse;
//                }
//        ).collect(Collectors.toList());

        OrderTrendResponseVo responseVo = new OrderTrendResponseVo();
//        responseVo.setEsOrderInfoResponseList(orderInfoResponseList);
        return responseVo;
    }
}

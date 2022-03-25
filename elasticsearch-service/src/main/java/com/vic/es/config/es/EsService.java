package com.vic.es.config.es;


import com.alibaba.fastjson.JSONObject;
import com.vic.es.config.es.entity.EsGroupByDataResponse;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class EsService {

    private RestHighLevelClient localClient;

    @Autowired
    @Qualifier("localClient")
    private void setOrderClient(RestHighLevelClient localClient) {
        this.localClient = localClient;
    }

    /**
     * 创建索引
     * @param index 索引名称
     */
    public boolean createIndex(String index)  {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(index);
        CreateIndexResponse createIndexResponse = null;
        try {
            createIndexResponse = localClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Objects.requireNonNull(createIndexResponse).isAcknowledged();
    }


//
//    public List<EsGroupByDataResponse> getGroupByData(BoolQueryBuilder boolQueryBuilder, Script script, String field, String indices) {
//
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.size(0);
//        searchSourceBuilder.query(boolQueryBuilder);
//
//
//          Script script = new Script(ScriptType.INLINE, "painless", "doc['partnerId'].value+':'+doc['storeId'].value", new HashMap<>());
//
//        TermsAggregationBuilder termsAggregationBuilder = AggregationBuilders.terms("result").script(script).subAggregation(AggregationBuilders.sum(field).field(field));
//        searchSourceBuilder.aggregation(termsAggregationBuilder);
//
//        SearchRequest searchRequest = new SearchRequest(indices);
//        searchRequest.source(searchSourceBuilder);
//
//          ignore_unavailable ：是否忽略不可用的索引
//          allow_no_indices：是否允许索引不存在
//          expandToOpenIndices ：通配符表达式将扩展为打开的索引
//          expandToClosedIndices ：通配符表达式将扩展为关闭的索引
//
//        searchRequest.indicesOptions(IndicesOptions.fromOptions(true, true, true, false));
//
//        SearchResponse searchResponse = null;
//        try {
//            searchResponse = orderClient.search(searchRequest, RequestOptions.DEFAULT);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        List<EsGroupByDataResponse> responseList = new ArrayList<>();
//
//        if (searchResponse != null && searchResponse.getAggregations() != null) {
//            Terms oneTerm = (Terms) searchResponse.getAggregations().asMap().get("result");
//            for (Terms.Bucket bucket : oneTerm.getBuckets()) {
//                EsGroupByDataResponse dataResponse = new EsGroupByDataResponse();
//                dataResponse.setKey(bucket.getKey().toString());
//                dataResponse.setValidOrdersNum(bucket.getDocCount());
//
//                Aggregation amount = bucket.getAggregations().get(field);
//                JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(amount));
//                double value = (double) jsonObject.getLongValue("value") / 100;
//                dataResponse.setSettlementAmount(value);
//
//                responseList.add(dataResponse);
//            }
//        }
//        return responseList;
//    }


}

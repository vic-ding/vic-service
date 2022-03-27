package com.vic.es.config.es;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
     *
     * @param index 索引名称
     */
    public String createIndex(String index) {
        boolean existsIndex = isExistsIndex(index);// 判断索引是否存在
        String response = "";
        if (existsIndex) {
            response = "索引已存在，无需创建";
        } else {
            CreateIndexRequest createIndexRequest = new CreateIndexRequest(index);
            try {
                CreateIndexResponse createIndexResponse = localClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
                if (createIndexResponse.isAcknowledged()) {
                    response = "创建索引成功";
                } else {
                    response = "创建索引失败";
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return response;
    }

    /**
     * 判断索引是否存在
     *
     * @param index 索引名称
     */
    public boolean isExistsIndex(String index) {
        GetIndexRequest getIndexRequest = new GetIndexRequest(index);
        boolean response = false;
        try {
            response = localClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 删除索引
     *
     * @param index 索引名称
     */
    public boolean deleteIndex(String index) {
        boolean existsIndex = isExistsIndex(index);// 判断索引是否存在
        boolean response = false;
        if (existsIndex) {
            DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(index);
            AcknowledgedResponse acknowledgedResponse;
            try {
                acknowledgedResponse = localClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
                response = acknowledgedResponse.isAcknowledged();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return response;
    }

    /**
     * 添加文档
     *
     * @param index 索引名称
     */
    public String addDocument(String index, String jsonString) {
        String response = "";

        IndexRequest indexRequest = new IndexRequest(index);
        //indexRequest.id("1");
        indexRequest.source(jsonString, XContentType.JSON);
        try {
            IndexResponse indexResponse = localClient.index(indexRequest, RequestOptions.DEFAULT);
            response = JSON.toJSONString(indexResponse.status());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 获取文档
     *
     * @param index 索引名称
     */
    public Map<String, Object> getDocument(String index, String id) {
        GetRequest getRequest = new GetRequest(index, id);
        Map<String, Object> sourceAsMap = null;
        try {
            GetResponse getResponse = localClient.get(getRequest, RequestOptions.DEFAULT);
            sourceAsMap = getResponse.getSourceAsMap();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sourceAsMap;
    }

    /**
     * 判断文档是否存在
     *
     * @param index 索引名称
     */
    public Boolean isExistsDocument(String index, String id) {
        GetRequest getRequest = new GetRequest(index, id);
        boolean response = false;
        try {
            response = localClient.exists(getRequest, RequestOptions.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 删除文档
     *
     * @param index 索引名称
     */
    public String deleteDocument(String index, String id) {
        DeleteRequest deleteRequest = new DeleteRequest(index, id);
        String response = "删除文档成功";
        try {
            DeleteResponse deleteResponse = localClient.delete(deleteRequest, RequestOptions.DEFAULT);
            // 文档不存在的情况
            if (deleteResponse.getResult() == DocWriteResponse.Result.NOT_FOUND) {
                response = "文档不存在";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 更新文档
     *
     * @param index 索引名称
     */
    public String updateDocument(String index, String id, String jsonString) {
        UpdateRequest updateRequest = new UpdateRequest(index, id).doc(jsonString, XContentType.JSON);
        String response = "更新文档成功";

        UpdateResponse updateResponse;
        try {
            try {
                updateResponse = localClient.update(updateRequest, RequestOptions.DEFAULT);
                log.info("更新文档成功,详细信息为 {}", JSON.toJSONString(updateResponse));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (ElasticsearchException e) {
            if (e.status() == RestStatus.NOT_FOUND) {
                response = "索引不存在";
            }
        }
        return response;
    }

    /**
     * 批量添加文档
     */
    public Boolean bulkDocument(BulkRequest bulkRequest) {
        boolean response = false;
        try {
            BulkResponse bulkResponse = localClient.bulk(bulkRequest, RequestOptions.DEFAULT);
            response = bulkResponse.hasFailures();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 根据条件查询所有数据
     *
     * @param index 索引名称
     * @param query 查询条件
     * @return List<Map < String, Object>>
     */
    public List<Map<String, Object>> searchAll(String index, QueryBuilder query) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(query);

        SearchRequest searchRequest = new SearchRequest(index);
        searchRequest.source(searchSourceBuilder);

        List<Map<String, Object>> response = new ArrayList<>();
        try {
            SearchResponse searchResponse = localClient.search(searchRequest, RequestOptions.DEFAULT);
            for (SearchHit searchHit : searchResponse.getHits().getHits()) {
                Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();
                response.add(sourceAsMap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
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

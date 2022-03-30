package com.vic.es.config.es;

import java.util.List;
import java.util.Map;

public class SearchResult {
    private List<Map<String, Object>> mapList;
    private Integer total;

    public List<Map<String, Object>> getMapList() {
        return mapList;
    }

    public void setMapList(List<Map<String, Object>> mapList) {
        this.mapList = mapList;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}

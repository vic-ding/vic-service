package com.vic.es.entity.jd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchAllRequest {
    private String goodsName;
    private String shopName;
    private String minPrice;
    private String maxPrice;
}

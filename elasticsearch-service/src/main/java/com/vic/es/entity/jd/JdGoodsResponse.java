package com.vic.es.entity.jd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JdGoodsResponse {
    private String goodsName;
    private String shopName;
    private String price;
}

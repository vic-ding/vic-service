package com.vic.es.entity.jd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BulkAddGoodsDocumentRequest {
    private String index;
    private String keyword;
}

package com.vic.es.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IsExistsDocumentRequestVo {
    private String index;
    private String id;
}

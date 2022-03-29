package com.vic.es.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BulkAddDocumentRequest {
    // 注册时间-开始时间
    private String startTime;
    // 注册时间-结束时间
    private String endTime;
}

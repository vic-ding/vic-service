package com.vic.es.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BulkAddUserDocumentRequestDto {
    private String index;
    private String id;

    private List<UserDocumentInfo> userDocumentInfoList;
}

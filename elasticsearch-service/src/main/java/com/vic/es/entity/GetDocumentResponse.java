package com.vic.es.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetDocumentResponse {
    private String name;
    private String mobile;
    private String sex;
    private String age;
}

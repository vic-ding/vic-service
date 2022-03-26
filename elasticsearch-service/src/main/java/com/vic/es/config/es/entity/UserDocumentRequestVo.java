package com.vic.es.config.es.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDocumentRequestVo {
    private String name;
    private String mobile;
    private String sex;
    private String age;
}

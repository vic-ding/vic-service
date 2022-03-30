package com.vic.es.entity.fm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FmMemberBaseRequestVo {
    private String partnerId;
    private Integer pageIndex;
    private Integer pageSize;
}

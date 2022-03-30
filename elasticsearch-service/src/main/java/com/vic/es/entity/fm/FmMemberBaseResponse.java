package com.vic.es.entity.fm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FmMemberBaseResponse {
    private String memberId;
    private String nickName;
    private String mobile;
    private String registerTime;
    private String photoUrl;
}

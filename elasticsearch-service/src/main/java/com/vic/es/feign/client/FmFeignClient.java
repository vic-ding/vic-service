package com.vic.es.feign.client;

import com.vic.es.feign.entity.MemberBaseInfoResponse;
import com.vic.es.feign.entity.MemberBaseQuery;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(value = "fm", url = "${fm.feign.client}")
public interface FmFeignClient {
    @PostMapping("/vic/searchMemberInfo")
    MemberBaseInfoResponse searchMemberInfo(@RequestBody MemberBaseQuery baseQuery);

}

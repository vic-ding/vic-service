package com.vic.es.feign.client;

import com.vic.base.response.BaseResponse;
import com.vic.es.feign.entity.MemberBaseInfoResponse;
import com.vic.es.feign.entity.MemberBaseQuery;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(value = "fm", url = "81.69.195.11:10010")
public interface FmFeignClient {
    @PostMapping(value = "/vic/searchMemberInfo")
    BaseResponse<MemberBaseInfoResponse> searchMemberInfo(@RequestBody MemberBaseQuery baseQuery);

}

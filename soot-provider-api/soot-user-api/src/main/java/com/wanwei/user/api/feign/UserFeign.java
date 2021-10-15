package com.wanwei.user.api.feign;

import com.wanwei.user.api.fallback.UserFeignFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(
        value = "service-product",
        fallbackFactory = UserFeignFallbackFactory.class
)
public interface UserFeign {

    @RequestMapping("/product/{pid}")
    Object findByPid(@PathVariable Integer pid);

}

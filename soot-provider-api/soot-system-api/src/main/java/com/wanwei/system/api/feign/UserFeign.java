package com.wanwei.system.api.feign;

import com.wanwei.system.api.system.base.api.fallback.UserFeignFallbackFactory;
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

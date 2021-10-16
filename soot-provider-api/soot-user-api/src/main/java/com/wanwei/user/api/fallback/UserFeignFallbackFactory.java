package com.wanwei.user.api.fallback;//package com.itheima.service.fallback;

import com.wanwei.user.api.feign.UserFeign;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/***
 * 这是容错类,他要求我们要是实现一个FallbackFactory<要为哪个接口产生容错类>
 * @author luoHongyu
 * @date 2021-10-15 11:23
 * @return null
 */
@Slf4j
@Service
public class UserFeignFallbackFactory implements FallbackFactory<UserFeign> {

    @Override
    public UserFeign create(Throwable throwable) {
        return pid -> {
            log.error("{}",throwable);
            return null;
        };
    }
}

package com.wanwei.system.api.fallback;

import com.wanwei.common.base.response.ObjectRestResponse;
import com.wanwei.system.api.service.RemoteUserService;
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
public class RemoteUserServiceFallbackFactory implements FallbackFactory<RemoteUserService> {

    @Override
    public RemoteUserService create(Throwable throwable) {
        return new RemoteUserService() {
            @Override
            public ObjectRestResponse getUserInfo(String userName, String source) {
                log.error("{}",throwable);
                return null;
            }

            @Override
            public ObjectRestResponse registerUserInfo(Object registerUser, String source) {
                log.error("{}",throwable);
                return null;
            }
        };
    }
}

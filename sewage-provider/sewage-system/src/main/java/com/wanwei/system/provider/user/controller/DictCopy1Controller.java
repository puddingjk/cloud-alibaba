package com.wanwei.system.provider.user.controller;

import com.wanwei.common.base.controller.BaseController;
import com.wanwei.system.provider.user.entity.DictCopy1;
import com.wanwei.system.provider.user.service.DictCopy1Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName : DictCopy1Controller
 * @Description :
 * @Author : admin
 * @Date: 2021-10-18
 */
@RestController
@RequestMapping("/dictCopy1")
@RequiredArgsConstructor
public class DictCopy1Controller extends BaseController<DictCopy1Service, DictCopy1> {


}

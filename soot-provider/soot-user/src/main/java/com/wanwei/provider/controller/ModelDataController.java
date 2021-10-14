package com.wanwei.provider.controller;

import com.wanwei.cbase.common.controller.BaseController;
import com.wanwei.provider.entity.ModelData;
import com.wanwei.provider.service.ModelDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName : ModelDataController
 * @Description :
 * @Author : admin
 * @Date: 2021-10-14
 */
@RestController
@RequestMapping("/modelData")
@RequiredArgsConstructor
public class ModelDataController extends BaseController<ModelDataService, ModelData> {


}

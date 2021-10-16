package com.wanwei.provider.service;


import org.springframework.stereotype.Service;
import com.wanwei.provider.mapper.ModelDataMapper;
import com.wanwei.provider.entity.ModelData;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;

/**
 * @ClassName : ModelDataServiceImpl
 * @Description :
 * @Author : admin
 * @Date: 2021-10-14
 */
@Service
@RequiredArgsConstructor
public class ModelDataServiceImpl extends ServiceImpl<ModelDataMapper, ModelData> implements ModelDataService {


}
package com.wanwei.common.base.controller;

//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.extension.service.IService;
//import com.github.pagehelper.Page;
//import com.github.pagehelper.PageHelper;
//import com.wanwei.DCloud2.base.annotation.BussAnnotation;
//import com.wanwei.common.constants.Constants;
//import com.wanwei.common.constants.SystemConstants;
//import com.wanwei.common.entity.BaseEntity;
//import com.wanwei.common.response.ObjectRestResponse;
//import com.wanwei.common.response.TableResultResponse;
//import com.wanwei.common.utils.SetAttrUtil;
//import com.wanwei.common.utils.StringUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName : BaseController
 * @Description : 通用增删改查
 * @Author : LuoHongyu
 * @Date: 2021-08-06 12:44
 */
@Slf4j
public class BaseController<Biz extends Object, Entity> {

//    @Autowired
    protected Biz baseService;

//
//    @PostMapping("/save")
//    @ResponseBody
//    @BussAnnotation("BASE_ADD")
//    public ObjectRestResponse<Entity> save(@RequestBody Entity entity) {
//        if(entity instanceof BaseEntity){
//            SetAttrUtil.addCreateTime((BaseEntity)entity);
//        }
//        baseService.save(entity);
//        return ObjectRestResponse.ok(entity);
//    }
//
//
//    /***
//     * @Description 保存或者更新
//     * @Author luoHongyu
//     * @Date 2021-08-06 19:36
//     * @param entity
//     * @return: com.wanwei.common.response.ObjectRestResponse<Entity>
//     */
//    @PostMapping("/saveOrUpdate")
//    @ResponseBody
//    @BussAnnotation("BASE_ADD_UPDATE")
//    public ObjectRestResponse<Entity> saveOrUpdate(@RequestBody Entity entity) throws Exception {
//        if(entity instanceof BaseEntity){
//            Field id = entity.getClass().getDeclaredField("id");
//            id.setAccessible(true);
//            if(id.get(entity)==null){
//                SetAttrUtil.addCreateTime((BaseEntity)entity);
//            }else {
//                SetAttrUtil.addUpdateTime((BaseEntity)entity);
//            }
//
//        }
//        baseService.saveOrUpdate(entity);
//        return ObjectRestResponse.ok(entity);
//    }
//
//
//    /***
//     * @Description 获取单个ID值
//     * @Author luoHongyu
//     * @Date 2021-08-06 19:13
//     * @param id
//     * @return: com.wanwei.common.response.ObjectRestResponse
//     */
//    @RequestMapping({"/get/{id}"})
//    @ResponseBody
//    public ObjectRestResponse get(@PathVariable Object id) {
//        Object o = baseService.getById(id.toString());
//        return ObjectRestResponse.ok(o);
//    }
//
//
//    /***
//     * @Description 更新某个ID的数据 只更新不为NULL的数据
//     * @Author luoHongyu
//     * @Date 2021-08-06 19:13
//     * @param entity
//     * @return: com.wanwei.common.response.ObjectRestResponse
//     */
//    @PostMapping({"/update"})
//    @ResponseBody
//    @BussAnnotation("BASE_UPDATE")
//    public ObjectRestResponse update(@RequestBody Entity entity) {
//        if(entity instanceof BaseEntity){
//            SetAttrUtil.addUpdateTime((BaseEntity)entity);
//        }
//        baseService.updateById(entity);
//        return ObjectRestResponse.ok(entity);
//    }
//
//
//    /***
//     * @Description 全量更新所有字段
//     * @Author luoHongyu
//     * @Date 2021-08-06 19:12
//     * @param entity 实体类
//     * @return: com.wanwei.common.response.ObjectRestResponse
//     */
//    @PostMapping({"/updateAll"})
//    @ResponseBody
//    @BussAnnotation("BASE_UPDATE")
//    public ObjectRestResponse updateAll(@RequestBody Entity entity) {
//        // todo 待完善
//        return ObjectRestResponse.ok(entity);
//    }
//
//
//    /***
//     * @Description 删除数据 通过ID
//     * @Author luoHongyu
//     * @Date 2021-08-06 19:11
//     * @param id
//     * @return: com.wanwei.common.response.ObjectRestResponse
//     */
//    @PostMapping("/delete")
//    @ResponseBody
//    @BussAnnotation("BASE_REMOVE")
//    public ObjectRestResponse deleteById(@RequestParam Object id) {
//        if (id == null) {
//            log.error("参数不合法,id为空");
//            return ObjectRestResponse.ok(null);
//        }
//        Object obj = this.baseService.getById(id.toString());
//        return ObjectRestResponse.ok(obj);
//    }
//
//
//    /***
//     * @Description 查询所有数据 可带参数
//     * @Author luoHongyu
//     * @Date 2021-08-06 19:11
//     * @return: com.wanwei.common.response.ObjectRestResponse
//     */
//    @PostMapping("/all")
//    @ResponseBody
//    @BussAnnotation("BASE_LIST_ALL")
//    public ObjectRestResponse selectListAll(@RequestBody Map param) {
//        QueryWrapper queryWrapper = genQueryWrapper(param);
//        return ObjectRestResponse.ok(baseService.list(queryWrapper));
//    }
//
//
//    /***
//     * @Description 分页查询数据 可带参数
//     * @Author luoHongyu
//     * @Date 2021-08-06 19:11
//     * @return: com.wanwei.common.response.ObjectRestResponse
//     */
//    @PostMapping("/page")
//    @ResponseBody
//    @BussAnnotation("BASE_PAGE")
//    public TableResultResponse page(@RequestBody Map param) {
//        Integer page = param.get(Constants.Pageable.PAGE) == null ? 0 : Integer.parseInt(param.get(Constants.Pageable.PAGE).toString());
//        Integer limit = param.get(Constants.Pageable.LIMIT) == null ? 15 : Integer.parseInt(param.get(Constants.Pageable.LIMIT).toString());
//        Page result = PageHelper.startPage(page, limit);
//        QueryWrapper queryWrapper = genQueryWrapper(param);
//        return new TableResultResponse(result.getTotal(), baseService.list(queryWrapper));
//    }
//
//
//    /**
//     * @param param 查询参数
//     * @Description 根据查询参数 包装成 QueryWrapper
//     * @Author luoHongyu
//     * @Date 2021-08-07 14:49
//     * @return: void
//     */
//    public QueryWrapper genQueryWrapper(Map param) {
//        QueryWrapper<Entity> queryWrapper = new QueryWrapper<>();
//        String paramKey;
//        Object paramValue;
//        for (Object o : param.keySet()) {
//            if (o == null || Constants.Pageable.PAGE.equals(o) || Constants.Pageable.LIMIT.equals(o)) {
//                continue;
//            }
//            paramKey = o.toString();
//            paramValue = param.get(o) == null ? null : param.get(o);
//            joinQueryParam(queryWrapper, paramKey, paramValue);
//        }
//        return queryWrapper;
//    }
//
//    /***
//     * @Description 根据前端参数 动态拼接查询条件
//     * @Author luoHongyu
//     * @Date 2021-08-07 14:49
//     * @param queryWrapper 动态拼接查询条件
//     * @param paramStr 具体查询字段名
//     * @param value 具体字段值
//     * @return: void
//     */
//    public static void joinQueryParam(QueryWrapper queryWrapper, String paramStr, Object value) {
//        if (StringUtils.isBlank(paramStr)) {
//            return;
//        }
//        String[] queryData = paramStr.split("_");
//        // 通用条件
//        commonJudge(queryData, queryWrapper, value);
//        // 模糊条件
//        likeJudge(queryData, queryWrapper, value);
//        // 范围相关条件
//        rangeJudge(queryData, queryWrapper, value);
//        // 分组排序相关条件
//        groupJudge(queryData, queryWrapper, value);
//    }
//
//
//    /**
//     * @Description 通用的( =, <>, >, >=, <, <= )查询条件生成
//     * @Author luoHongyu
//     * @Date 2021-08-07 17:57
//     * @param queryData
//     * @param queryWrapper
//     * @param value
//     * @return: void
//     */
//    public static void commonJudge(String[] queryData, QueryWrapper queryWrapper, Object value) {
//        switch (queryData[0]) {
//            case SystemConstants.Condition.EQ:
//                if (value != null) {
//                    queryWrapper.eq(StringUtil.camel2Underline(queryData[1]), value);
//                }
//                break;
//            case SystemConstants.Condition.NE:
//                if (value != null) {
//                    queryWrapper.ne(StringUtil.camel2Underline(queryData[1]), value);
//                }
//                break;
//            case SystemConstants.Condition.GT:
//                if (value != null) {
//                    queryWrapper.gt(StringUtil.camel2Underline(queryData[1]), value);
//                }
//                break;
//            case SystemConstants.Condition.GE:
//                if (value != null) {
//                    queryWrapper.ge(StringUtil.camel2Underline(queryData[1]), value);
//                }
//                break;
//            case SystemConstants.Condition.LT:
//                if (value != null) {
//                    queryWrapper.lt(StringUtil.camel2Underline(queryData[1]), value);
//                }
//                break;
//            case SystemConstants.Condition.LE:
//                if (value != null) {
//                    queryWrapper.le(StringUtil.camel2Underline(queryData[1]), value);
//                }
//                break;
//            case SystemConstants.Condition.NULL:
//                if (value != null) {
//                    if (value instanceof ArrayList) {
//                        List v = (List) value;
//                        for (Object column : v) {
//                            queryWrapper.isNull(StringUtil.camel2Underline(column.toString()));
//                        }
//                    } else if (value instanceof String && StringUtils.isNotBlank((String) value)) {
//                        String[] columnArray = ((String) value).split(",");
//                        for (String column : columnArray) {
//                            queryWrapper.isNull(StringUtil.camel2Underline(column));
//                        }
//                    }
//                }
//                break;
//            case SystemConstants.Condition.NOTNULL:
//                if (value != null) {
//                    if (value instanceof ArrayList) {
//                        List v = (List) value;
//                        for (Object column : v) {
//                            queryWrapper.isNotNull(StringUtil.camel2Underline(column.toString()));
//                        }
//                    } else if (value instanceof String && StringUtils.isNotBlank((String) value)) {
//                        String[] columnArray = ((String) value).split(",");
//                        for (String column : columnArray) {
//                            queryWrapper.isNotNull(StringUtil.camel2Underline(column));
//                        }
//                    }
//                }
//                break;
//            default:
//                return;
//        }
//    }
//
//    /***
//     * @Description 模糊匹配（like)相关条件判断
//     * @Author luoHongyu
//     * @Date 2021-08-07 17:57
//     * @param queryData
//     * @param queryWrapper
//     * @param value
//     * @return: void
//     */
//    public static void likeJudge(String[] queryData, QueryWrapper queryWrapper, Object value) {
//        switch (queryData[0]) {
//            case SystemConstants.Condition.LIKE:
//                if (value != null) {
//                    queryWrapper.like(StringUtil.camel2Underline(queryData[1]), value);
//                }
//                break;
//            case SystemConstants.Condition.LEFT_LIKE:
//                if (value != null) {
//                    queryWrapper.apply(StringUtil.camel2Underline(queryData[1]) + " like{0}", "%" + value);
//                }
//                break;
//            case SystemConstants.Condition.RIGHT_LIKE:
//                if (value != null) {
//                    queryWrapper.apply(StringUtil.camel2Underline(queryData[1]) + " like{0}", value + "%");
//                }
//                break;
//            case SystemConstants.Condition.NOT_LIKE:
//                if (value != null) {
//                    queryWrapper.notLike(StringUtil.camel2Underline(queryData[1]), value);
//                }
//                break;
//            case SystemConstants.Condition.NOT_LEFT_LIKE:
//                if (value != null) {
//                    queryWrapper.apply(StringUtil.camel2Underline(queryData[1]) + " not like{0}", "%" + value);
//                }
//                break;
//            case SystemConstants.Condition.NOT_RIGHT_LIKE:
//                if (value != null) {
//                    queryWrapper.apply(StringUtil.camel2Underline(queryData[1]) + " not like{0}", value + "%");
//                }
//                break;
//            default:
//                return;
//        }
//    }
//
//
//    /**
//     * @param queryData
//     * @param queryWrapper
//     * @param value
//     * @Description 分组排序（group、having、order）相关参数拼接
//     * @Author luoHongyu
//     * @Date 2021-08-07 17:56
//     * @return: void
//     */
//    public static void groupJudge(String[] queryData, QueryWrapper queryWrapper, Object value) {
//        switch (queryData[0]) {
//            case SystemConstants.Condition.ORDER_ASC:
//                if (value instanceof ArrayList) {
//                    List v = (List) value;
//                    for (Object column : v) {
//                        queryWrapper.orderByAsc(StringUtil.camel2Underline(column.toString()));
//                    }
//                } else if (value instanceof String && StringUtils.isNotBlank((String) value)) {
//                    String[] columnArray = ((String) value).split(",");
//                    for (String column : columnArray) {
//                        queryWrapper.orderByAsc(StringUtil.camel2Underline(column));
//                    }
//                }
//                break;
//            case SystemConstants.Condition.ORDER_DES:
//                if (value instanceof ArrayList) {
//                    List v = (List) value;
//                    for (Object column : v) {
//                        queryWrapper.orderByDesc(StringUtil.camel2Underline(column.toString()));
//                    }
//                } else if (value instanceof String && StringUtils.isNotBlank((String) value)) {
//                    String[] columnArray = ((String) value).split(",");
//                    for (String column : columnArray) {
//                        queryWrapper.orderByDesc(StringUtil.camel2Underline(column));
//                    }
//                }
//                break;
//            default:
//                return;
//        }
//    }
//
//
//    /***
//     * @Description 范围查询（between、not between、in、not in）相关条件拼接
//     * @Author luoHongyu
//     * @Date 2021-08-07 17:56
//     * @param queryData
//     * @param queryWrapper
//     * @param value
//     * @return: void
//     */
//    public static void rangeJudge(String[] queryData, QueryWrapper queryWrapper, Object value) {
//        switch (queryData[0]) {
//            case SystemConstants.Condition.IN:
//                if (value != null) {
//                    if (value instanceof ArrayList) {
//                        queryWrapper.in(StringUtil.camel2Underline(queryData[1]), (List) value);
//                    } else if (value instanceof String && StringUtils.isNotBlank((String) value)) {
//                        queryWrapper.in(StringUtil.camel2Underline(queryData[1]), Arrays.asList(value.toString().split(",")));
//                    }
//                }
//                break;
//            case SystemConstants.Condition.BETWEEN:
//                if (value != null) {
//                    if (value instanceof ArrayList) {
//                        List v = (List) value;
//                        queryWrapper.between(StringUtil.camel2Underline(queryData[1]), v.get(0), v.get(1));
//                    } else if (value instanceof String) {
//                        String[] split = value.toString().split(",");
//                        queryWrapper.between(StringUtil.camel2Underline(queryData[1]), split[0], split[1]);
//                    }
//                }
//                break;
//            case SystemConstants.Condition.NOT_BETWEEN:
//                if (value instanceof ArrayList) {
//                    List v = (List) value;
//                    queryWrapper.notBetween(StringUtil.camel2Underline(queryData[1]), v.get(0), v.get(1));
//                } else if (value instanceof String) {
//                    String[] split = value.toString().split(",");
//                    queryWrapper.notBetween(StringUtil.camel2Underline(queryData[1]), split[0], split[1]);
//                }
//                break;
//            case SystemConstants.Condition.NOT_IN:
//                if (value != null) {
//                    if (value instanceof ArrayList) {
//                        queryWrapper.notIn(StringUtil.camel2Underline(queryData[1]), (List) value);
//                    } else if (value instanceof String && StringUtils.isNotBlank((String) value)) {
//                        queryWrapper.notIn(StringUtil.camel2Underline(queryData[1]), Arrays.asList(value.toString().split(",")));
//                    }
//                }
//                break;
//            default:
//                return;
//        }
//    }


}

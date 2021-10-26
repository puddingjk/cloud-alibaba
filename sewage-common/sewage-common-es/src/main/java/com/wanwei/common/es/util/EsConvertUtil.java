package com.wanwei.common.es.util;

import com.wanwei.common.base.response.TableResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName : EsUtils
 * @Description :
 * @Author : puddingJk
 * @Date: 2021-06-08 14:01
 */
@Component
@Slf4j
public class EsConvertUtil {

    /***
     * @Param [searchHits]
     * @description 将elasticsearch对象 转为 表格对象
     * @author LuoHongyu
     * @date 2021-06-10 10:21
     */
    public static TableResultResponse toTableData(SearchHits<Map> searchHits) {
        List<Map> collect = searchHits.stream().map(x -> x.getContent()).collect(Collectors.toList());
        return new TableResultResponse(searchHits.getTotalHits(), collect);
    }

    /***
     * @Param [searchHits]
     * @description 将elasticsearch对象 转为 LIST对象
     * @author LuoHongyu
     * @date 2021-06-10 10:21
     */
    public static List toList(SearchHits<Map> searchHits) {
        List<Map> collect = searchHits.stream().map(x -> x.getContent()).collect(Collectors.toList());
        return collect;
    }
}

package com.wanwei.common.es.util;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.alias.get.GetAliasesRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.GetAliasesResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.cluster.metadata.AliasMetaData;
import org.elasticsearch.common.settings.Settings;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName : ElasticsearchRestUtil
 * @Description : Es查询工具类
 * @Author : LuoHongyu
 * @Date : 2021-10-14 17:15
 */
@Slf4j
@Component
public class ElasticsearchRestUtil {

    private RestHighLevelClient highClient;

    private ElasticsearchRestTemplate restTemplate;


    public ElasticsearchRestUtil(RestHighLevelClient highClient, ElasticsearchRestTemplate restTemplate) {
        Assert.notNull(highClient, "RestHighLevelClient must not be null!");
        Assert.notNull(restTemplate, "ElasticsearchRestTemplate must not be null!");
        this.highClient = highClient;
        this.restTemplate = restTemplate;
    }


    /***
     * @Param []
     * @description es高级查询工具类
     * @author LuoHongyu
     * @date 2021-06-28 13:53
     */
    public RestHighLevelClient highTemplate() {
        return highClient;
    }


    /***
     * @Param []
     * @description es通用查询工具类
     * @author LuoHongyu
     * @date 2021-06-28 13:53
     */
    public ElasticsearchRestTemplate template() {
        return restTemplate;
    }

    /***
     * @Param [indexName]
     * @description 判断索引是否存在
     * @author LuoHongyu
     * @date 2021-06-10 10:21
     */
    public boolean existIndex(String indexName) {
        return this.template().indexOps(IndexCoordinates.of(indexName)).exists();
    }

    /***
     * @Param []
     * @description 删除所有索引 不包含.开头的
     * @author LuoHongyu
     * @date 2021-06-28 13:57
     */
    public void deleteIndexAll() {
        GetAliasesRequest allRequest = new GetAliasesRequest();
        GetAliasesResponse alias;
        try {
            alias = highClient.indices().getAlias(allRequest, RequestOptions.DEFAULT);
            Map<String, Set<AliasMetaData>> aliases = alias.getAliases();
            String[] list = new String[aliases.keySet().size()];
            Object[] objects = aliases.keySet().toArray();
            for (int i = 0; i < aliases.keySet().size(); i++) {
                if (objects[i].toString().startsWith(".")) {
                    continue;
                }
                list[i] = objects[i].toString();
            }
            if (list.length > 0) {
                DeleteIndexRequest deleteRequest = new DeleteIndexRequest();
                deleteRequest.indices(list);
                highClient.indices().delete(deleteRequest, RequestOptions.DEFAULT);
            }
        } catch (IOException e) {
            log.error("error:{}", e);
        }
    }


    /***
     * @Param []
     * @description 创建索引 通过高级客户端创建
     * @author LuoHongyu
     * @date 2021-06-28 14:02
     */
    public void createIndex(String... indexName) {
        this.createIndex(null, indexName);
    }

    /***
     * @Param [settings, indexName]
     * @description 创建索引，并指定索引配置
     * 例：指定分配数和副本数：Settings build = Settings.builder().put("index.number_of_shards", 1).put("index.number_of_replicas", 0).build();
     * @author LuoHongyu
     * @date 2021-06-28 14:10
     */
    public void createIndexSettings(Settings settings, String... indexName) {
        this.createIndex(settings, indexName);
    }

    /***
     * @Param []
     * @description 创建索引 通过高级客户端创建
     * @author LuoHongyu
     * @date 2021-06-28 14:02
     */
    public void createIndex(Settings settings, String... indexName) {
        if (settings == null) {
            settings = Settings.builder().build();
        }
        CreateIndexRequest request;
        try {
            // 判断是否存在索引
            for (String index : indexName) {
                boolean has = highClient.indices().exists(new GetIndexRequest(index), RequestOptions.DEFAULT);
                if (!has) {
                    // 创建索引
                    request = new CreateIndexRequest(index);
                    request.settings(settings);
                    highClient.indices().create(request, RequestOptions.DEFAULT);
                }
            }
        } catch (IOException e) {
            log.error("error:{}",e);
        }
    }

}

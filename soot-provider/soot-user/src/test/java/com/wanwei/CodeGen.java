package com.wanwei;

import com.wanwei.properties.GenProperties;
import com.wanwei.util.CodeGenUtil;
import freemarker.template.TemplateException;

import javax.xml.bind.ValidationException;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @ClassName : CodeGen 根据数据库表自动生成代码
 * @Description :
 * @author : LuoHongyu
 * @date: 2021-08-03 17:30
 */
public class CodeGen {

    /**
     * 控制层层 包名
     */
    public static final String CONTROLLER = "controller";

    /**
     * 实体类层 包名
     */
    public static final String ENTITY = "entity";

    /**
     * service层 包名
     */
    public static final String SERVICE = "service";

    /**
     * mapper层 包名
     */
    public static final String MAPPER = "mapper";

    /**
     * mapper xml 包名
     */
    public static final String RESOURCES_MAPPER = "resources/mapper";




    public static void main(String[] args) throws URISyntaxException, TemplateException, ValidationException, IOException {
        GenProperties properties = new GenProperties();
        properties.setUrl("jdbc:mysql://192.168.9.14:3306/networkofthings?useUnicode=true&characterEncoding=UTF-8&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8");
        properties.setUsername("root");
        properties.setPassword("admin123!@#qwe");
        // 基础包
        String basePackagePath = "com/wanwei/provider/";
        // 模块分类文件夹
        String modulePackage = "";
        // 生成文件
        genFile(properties,basePackagePath,modulePackage,"tb_model_data");

    }

    /***
     *  生成文件的配置信息
     * @author luoHongyu
     * @date 2021-08-04 9:03
     * @param properties 配置文件路径
     * @param basePackagePath 生成代码的主路径
     * @param modulePackage 业务模块名称
     * @param tableName 数据库表名
     * @return  void
     */
    public static void genFile( GenProperties properties,String basePackagePath,String modulePackage,String tableName ) throws URISyntaxException, TemplateException, ValidationException, IOException {
        if(properties==null ){
            throw new ValidationException("配置文件数据为空");
        }
        String allPackagePath = basePackagePath + modulePackage;
        properties.setControllerPath(allPackagePath + CONTROLLER);
        properties.setEntityPath(allPackagePath + ENTITY);
        properties.setServicePath(allPackagePath + SERVICE);
        properties.setServiceImplPath(allPackagePath + SERVICE);
        properties.setMapperPath(allPackagePath + MAPPER);
        // xml生成位置
        properties.setXmlPath(RESOURCES_MAPPER);
        CodeGenUtil.genFileByTableName(tableName, properties, CodeGen.class);
    }
}

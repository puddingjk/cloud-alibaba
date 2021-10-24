package com.wanwei.common.base.rest;

import com.wanwei.common.base.response.ObjectRestResponse;
import com.wanwei.common.base.util.FileUtils;
import com.wanwei.common.base.util.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 文件上传相关
 * @author admin
 * @date 2016/10/31
 */
@Controller
@RequestMapping("file")
@Slf4j
public class RestFileController {

    @Value("${file.upload.path}")
    private String upPathFix;
    @Value("${file.upload.size}")
    private long upPathSize;


    /**
     * 返回fileName和fileNewName字符串用于数据库保存，以逗号分隔
     * 多文件上传
     * fileUrl = FileUtils.getUploadFilePath(FileUtils.FILE_YWGL_URL)+ System.getProperty("file.separator");
     * * truFile:现在数据库中保存的文件的名字，以逗号分隔
     * * newFile：现在数据库中保存的文件的新的自定义的名字，以逗号分隔
     * filePath：文件上传路径
     * 接口调用中，noRename为true或者1则不进行重命名
     *
     * @return com.wanwei.DCloud2.base.msg.ObjectRestResponse
     * @Author zhangjing
     * @Description
     * @Date 15:39 2018/9/18
     * @Param [request, filePath]
     **/
    @RequestMapping(value = "fileUp", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse uploadHandlerForUploadify(HttpServletRequest request, String filePath) {
        String characterEncoding = request.getCharacterEncoding();
        log.info("request编码:" + characterEncoding);
        ObjectRestResponse response = new ObjectRestResponse();
        if (filePath == null) {
            filePath = "";
        } else {
            filePath += "/";
        }
        String fileUrl = upPathFix + "/" + filePath;
        log.info("文件上传");
        String noRenameStr = request.getParameter("noRename");
        boolean noRename = false;
        if (StringUtils.isNotBlank(noRenameStr) && (StringUtils.equals(noRenameStr,"1") || StringUtils.equals(noRenameStr,"true"))) {
            noRename = true;
        }
        StringBuilder truFileName = new StringBuilder();
        StringBuilder newFileName = new StringBuilder();
        Map<String, String> attr = new HashMap<>();
        MultipartHttpServletRequest multipartRequest;
        if (request instanceof MultipartHttpServletRequest) {
            multipartRequest = (MultipartHttpServletRequest) request;
        } else {
            Map<String, String> result = new HashMap<>();
            result.put("fileName", truFileName.toString());
            result.put("fileNewName", newFileName.toString());
            response.setData(result);
            return response;
        }

        //支持多个input，每个input上传多个文件的形式，比较全面，采用这种方式
        MultiValueMap<String, MultipartFile> multiFileMap = multipartRequest.getMultiFileMap();
        Set<Entry<String, List<MultipartFile>>> entrySet = multiFileMap.entrySet();
        for (Entry<String, List<MultipartFile>> maps : entrySet) {
            String key = maps.getKey();
            List<MultipartFile> MultipartFileList = maps.getValue();
            for (MultipartFile multipartFile : MultipartFileList) {
//	    		if (multipartFile.isEmpty()) {
//					continue;
//				}
                String fileName = null;
                try {
                    String originalFilename = multipartFile.getOriginalFilename();
                    log.info("原始文件名：" + fileName);
                    fileName = new String(originalFilename.getBytes(characterEncoding), "UTF-8");
                    log.info("编码后的文件名：" + fileName);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    log.error("文件转码异常");
                }
                String fileFlag = UUIDUtils.generate32UUidString();
                if (fileName.lastIndexOf(".") < 0) {
                    String fileStrName = noRename ? fileName : fileFlag;
                    // 文件名
                    truFileName.append(fileName).append(",");
                    newFileName.append(fileStrName).append(",");
                    // 执行保存操作
                    attr.put(key, fileStrName);
                    FileUtils.upload(request, fileUrl, upPathSize, attr);
                } else {
                    String fileStrName = noRename ? fileName : fileFlag
                            + fileName.substring(fileName.lastIndexOf("."), fileName.length()).toLowerCase();
                    // 文件名
                    truFileName.append(fileName).append(",");
                    newFileName.append(fileStrName).append(",");
                    // 执行保存操作
                    attr.put(key, fileStrName.substring(0, fileStrName.lastIndexOf(".")));
                    FileUtils.upload(request, fileUrl, upPathSize, attr);
                }

            }
        }
        Map<String, String> result = new HashMap<>();
        if (truFileName.lastIndexOf(",") == truFileName.length() - 1) {
            truFileName.deleteCharAt(truFileName.length() - 1);
        }
        result.put("fileName", truFileName.toString());
        if (newFileName.lastIndexOf(",") == newFileName.length() - 1) {
            newFileName.deleteCharAt(newFileName.length() - 1);
        }
        result.put("fileNewName", newFileName.toString());
        response.setData(result);
        return response;
    }

    /**
     * 上传单个文件(文件名不变)
     *
     * @return com.wanwei.DCloud2.base.msg.ObjectRestResponse
     * @author qbk
     * @date 2019/12/2
     * @Param [request, file, filePath]
     * @see
     */
    @RequestMapping(value = "fileUpload", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse uploadMultipartFile(MultipartFile file, String filePath) {
        if (filePath == null) {
            filePath = "";
        } else {
            filePath += "/";
        }
        String fileUrl = upPathFix + "/" + filePath;
        if (file == null || file.isEmpty()) {
            return ObjectRestResponse.error();
        }
        FileUtils.upload(file, fileUrl, upPathSize);
        return ObjectRestResponse.ok(null);
    }

    /**
     * @return void
     * @Author zhangjing
     * @Description //文件下载
     * @Date 16:54 2018/9/4
     * @Param [response, request, fileName, newName]
     **/
    @RequestMapping("fileDown")
    public void fileDown(HttpServletResponse response, HttpServletRequest request, String fileName, String newName) {
        try {
            FileUtils.download(fileName, upPathFix + "/" + newName, request, response);
        } catch (IOException e) {
            log.error("文件下载", e);
        }
    }

    /**
     * @return com.wanwei.DCloud2.base.msg.ObjectRestResponse
     * @Author zhangjing
     * @Description //删除文件，此接口慎重使用，避免出现文件已删除，但数据未同步的问题
     * @Date 17:34 2018/9/4
     * @Param [fileName]
     **/
    @RequestMapping("fileDel")
    @ResponseBody
    public ObjectRestResponse fjDelete(String fileName) {
        ObjectRestResponse response = new ObjectRestResponse();
        try {
            boolean delIs = FileUtils.delFile(upPathFix + "/" + fileName);
            response.setRel(delIs);
            if (!delIs) {
                response.setMessage(fileName + "文件不存在！");
            }
        } catch (Exception e) {
            response.setRel(false);
            response.setMessage(fileName + "删除失败，请重试！");
        }
        return response;
    }


}

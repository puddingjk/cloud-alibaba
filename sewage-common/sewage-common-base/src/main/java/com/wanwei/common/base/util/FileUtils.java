package com.wanwei.common.base.util;

import com.wanwei.common.base.constants.ErrorCodeEnum;
import com.wanwei.common.base.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Description:文件处理工具类
 * @date 2015-4-3
 */
@Slf4j
public class FileUtils {

	/**
	 * 上传文件名称
	 */
	public static final String FILE_NAME = "fileName";
	/***
	 * 文件上传重命名后的名字
	 */
	public static final String FILE_NEW_NAME = "fileNewName";
	/**
	 * 服务器端存储路径
	 */
	public static final String FILE_URL = "fileUrl";


	/***
	 * 
	 * 文件上传
	 * 
	 * @author zhangjing
	 * @date 2015-6-18
	 * @param request
	 * @param filePath
	 * @param fileSize
	 *            文件大小限制，如无限制，则传null
	 * @param attrName
	 *            对应前台的key：前台对应的name,value:存储文件的名字，如不进行自定义，则传null
	 * @return map name对应的文件名，已经文件路径
	 *         map.get(name)--上传文件对应的name来获取文件存储的名字及存储路径，如未上传文件
	 *         ，则map.get(name)值为空 获取的map中存储文件名称，以及文件上传后名称以及文件路径
	 * @throws Exception
	 * @see
	 */
	public static Map<String, HashMap<String, String>> upload(
			HttpServletRequest request, String filePath, Long fileSize,
			Map<String, String> attrName) {

		Map<String, HashMap<String, String>> fileList = new HashMap<String, HashMap<String, String>>();
		createFileDir(filePath);
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// 获取上传文件
		// MultipartFile multipartFile =
		// multipartRequest.getFile("file_upload");
		HashMap<String, String> fileInfo = null;
		for (String name : attrName.keySet()) {
			MultipartFile multipartFile = multipartRequest.getFile(name);
			if (multipartFile == null
					|| (multipartFile.isEmpty() && StringUtils
							.isBlank(multipartFile.getOriginalFilename()))) {// 未选择文件不进行上传
				fileList.put(name, fileInfo);
//			} else if (!multipartFile.isEmpty()) {
			} else {
				String fileName = multipartFile.getOriginalFilename();// 获取名称
				/** 对文件大小进行限制 */
				if (fileSize != null && multipartFile.getSize() > fileSize) {
					throw new BaseException(ErrorCodeEnum.UPLOAD_FILE_OVERLARGE);
				}
				log.info("上传文件名称===========：" + fileName);
				// 检查文件后缀格式
				String fileEnd = null;
				if(fileName.lastIndexOf(".") >= 0){
					fileEnd = fileName.substring(
							fileName.lastIndexOf(".") + 1).toLowerCase();
				}
				String uuidFileName;
				// 创建文件唯一名称
				if (attrName.get(name) == null) {
					uuidFileName = UUIDUtils.generateShortUuid();
				}
				else {
					uuidFileName = attrName.get(name);
				}
				// 真实上传路径
				StringBuffer realPath = new StringBuffer();
				realPath.append(filePath).append(uuidFileName);
				if(fileEnd != null){
					realPath.append(".").append(fileEnd);
				}

				log.info("上传文件URL===========：" + realPath);
				// 写入文件
				File uploadfile = new File(realPath.toString());
				try {
					FileCopyUtils.copy(multipartFile.getBytes(), uploadfile);
				} catch (IOException e) {
					log.error("", e);
				}
				fileInfo = new HashMap<String, String>();
				fileInfo.put(FILE_NAME, fileName);
				fileInfo.put(FILE_URL, realPath.toString());
				if(fileEnd != null){
					fileInfo.put(FILE_NEW_NAME, uuidFileName + "." + fileEnd);
				}else{
					fileInfo.put(FILE_NEW_NAME, uuidFileName);
				}
				fileList.put(name, fileInfo);
				fileInfo = null;
			}
//			else {
//				throw new BaseException(ErrorCodeEnum.OPC10040010);
//			}
		}
		return fileList;
	}

	/**
	 *  上传单个文件(文件名不变)
	 * @author   qbk
	 * @date     2019/12/3
	 * @Param    [multipartFile, filePath, fileSize]
	 * @return   void
	 * @see
	 */
	public static void upload(MultipartFile multipartFile, String filePath, Long fileSize) {

		createFileDir(filePath);
		if (multipartFile == null || (multipartFile.isEmpty() && StringUtils.isBlank(multipartFile.getOriginalFilename()))) {
		    return;
		}
		String fileName = multipartFile.getOriginalFilename();// 获取名称
		/** 对文件大小进行限制 */
		if (fileSize != null && multipartFile.getSize() > fileSize) {
			throw new BaseException(ErrorCodeEnum.UPLOAD_FILE_OVERLARGE);
		}
		// 真实上传路径
		StringBuffer realPath = new StringBuffer();
		realPath.append(filePath).append(fileName);
		log.info("上传文件URL===========：" + realPath);
		// 写入文件
		File uploadfile = new File(realPath.toString());
		try {
			FileCopyUtils.copy(multipartFile.getBytes(), uploadfile);
		} catch (IOException e) {
			log.error("文件上传失败:", e);
		}
	}

	public static void uploadRename(MultipartFile multipartFile, String filePath, Long fileSize, String newFileName) {

		createFileDir(filePath);
		if (multipartFile == null || (multipartFile.isEmpty() && StringUtils.isBlank(multipartFile.getOriginalFilename()))) {
			return;
		}

		// 对文件大小进行限制
		if (fileSize != null && multipartFile.getSize() > fileSize) {
			throw new BaseException(ErrorCodeEnum.UPLOAD_FILE_OVERLARGE);
		}
		// 真实上传路径
		StringBuffer realPath = new StringBuffer();
		realPath.append(filePath).append(newFileName);
		log.info("上传文件URL===========：" + realPath);
		// 写入文件
		File uploadfile = new File(realPath.toString());
		try {
			FileCopyUtils.copy(multipartFile.getBytes(), uploadfile);
		} catch (IOException e) {
			log.error("文件上传失败:", e);
		}
	}

	/***
	 * 
	 * 上传单个文件
	 * @author zhangjing
	 * @date 2016-1-11
	 * @param request
	 * @param filePath 文件上传路径
	 * @param fileSize 文件大小
	 * @param attName 对应前台属性
	 * @param fileFormat 文件格式，多个分号隔开，最后已分号结尾,无格式限制传入null
	 * @param rename true 进行重命名  false 不重命名使用上传对应的文件名
	 * @return  上传文件的路径  FILE_URL（文件存储全路径） 上传文件的原名称 FILE_NAME
	 * @throws Exception
	 * @see
	 */
	public static Map<String,String>  uploadOne(
			HttpServletRequest request, String filePath, Long fileSize,String attName,String fileFormat,boolean rename) {
		createFileDir(filePath);
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String,String> map = new HashMap<String,String>();
		String fileName=null;
			MultipartFile multipartFile = multipartRequest.getFile(attName);
			if (multipartFile == null
					|| (multipartFile.isEmpty() && StringUtils
							.isBlank(multipartFile.getOriginalFilename()))) {
				// 未选择文件不进行上传
			} else if (!multipartFile.isEmpty()) {
				fileName = multipartFile.getOriginalFilename();
				// 获取名称
				map.put(FILE_NAME, fileName);
				/** 对文件大小进行限制 */
				if (fileSize != null && multipartFile.getSize() > fileSize) {
					throw new BaseException(ErrorCodeEnum.UPLOAD_FILE_OVERLARGE);
				}
				log.info("上传文件名称===========：" + fileName);
				// 检查文件后缀格式
				String fileEnd = fileName.substring(
						fileName.indexOf(".") + 1).toLowerCase();
				
				if(fileFormat!=null){
					int index = fileFormat.toLowerCase().indexOf(fileEnd+";");
					if(index==-1) {
						throw new BaseException(ErrorCodeEnum.OPC10040003);
					}
				}
				
				String uuidFileName=null;
				if (rename) {
					// 创建文件唯一名称
					uuidFileName = UUIDUtils.generateShortUuid();
				}
				else {
					uuidFileName = fileName.substring(0, fileName.indexOf("."));
				}
				// 真实上传路径
				StringBuffer realPath = new StringBuffer();
				realPath.append(filePath).append(uuidFileName).append(".")
						.append(fileEnd);
				log.info("上传文件URL===========：" + realPath);
				// 写入文件
				File uploadfile = new File(realPath.toString());
				try {
					FileCopyUtils.copy(multipartFile.getBytes(), uploadfile);
				} catch (IOException e) {
					log.error("", e);
				}
				map.put(FILE_URL, realPath.toString());
				
			} else {
				throw new BaseException(ErrorCodeEnum.UPLOAD_NOT_EXIST);
			}
		return map;
	}
	public static Map<String, HashMap<String, String>> uploadFiles(
			HttpServletRequest request, String filePath, Long fileSize,
			List<String> attrNames, boolean renamed) {

		Map<String, HashMap<String, String>> fileList = new HashMap<String, HashMap<String, String>>();
		createFileDir(filePath);
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		HashMap<String, String> fileInfo = null;
		for (String name : attrNames) {
			MultipartFile multipartFile = multipartRequest.getFile(name);
			if (multipartFile == null
					|| (multipartFile.isEmpty() && StringUtils
					.isBlank(multipartFile.getOriginalFilename()))) {// 未选择文件不进行上传
				fileList.put(name, fileInfo);
			} else if (!multipartFile.isEmpty()) {
				String oldFileName = multipartFile.getOriginalFilename();
				/** 对文件大小进行限制 */
				if (fileSize != null && multipartFile.getSize() > fileSize) {
					throw new BaseException(ErrorCodeEnum.UPLOAD_FILE_OVERLARGE);
				}
				String fileName = "";
				if (renamed){
					// 创建文件唯一名称
					fileName = UUIDUtils.generateShortUuid();
				}else{
					//使用 文件原始 名称
					fileName = oldFileName;
				}
				StringBuffer realPath = new StringBuffer();
				realPath.append(filePath).append(fileName);
				// 写入文件
				File uploadfile = new File(realPath.toString());
				try {
					FileCopyUtils.copy(multipartFile.getBytes(), uploadfile);
				} catch (IOException e) {
					log.error("", e);
				}
				fileInfo = new HashMap<String, String>();
				fileInfo.put(FILE_NAME, oldFileName);
				fileInfo.put(FILE_URL, realPath.toString());
				fileInfo.put(FILE_NEW_NAME, fileName);
				fileList.put(name, fileInfo);
				fileInfo = null;
			} else {
				throw new BaseException(ErrorCodeEnum.UPLOAD_NOT_EXIST);
			}
		}
		return fileList;
	}

	/***
	 * 
	 * 文件下载
	 * @date 2015-8-11
	 * @param fileName 文件名 
	 * @param fileUrl 文件路径fileUrl
	 * @param request
	 * @param response
	 * @throws IOException
	 * @see
	 */
	public static void download(String fileName, String fileUrl,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			request.setCharacterEncoding("UTF-8");
			File file  = new File(fileUrl);
			if(!file.exists()){
				return;
			}
			long fileLength = file.length();
			response.setContentType("application/x-msdownload;charset=UTF-8");
			response.setHeader("Content-disposition", "attachment; filename=\""+encodeChineseDownloadFileName(request, fileName)+"\"");
			response.setHeader("Content-Length", String.valueOf(fileLength));
			bis = new BufferedInputStream(new FileInputStream(fileUrl));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
			bos.flush();
		} finally {
			if (bis != null) {
				bis.close();
			}
			if (bos != null) {
				bos.close();
			}
		}
	}

	/**
	 * 方法描述：</br> 创建文件夹
	 * 
	 */
	public static void createFileDir(final String filePath) {
		final File pathDir = new File(filePath);
		if (!pathDir.exists()) {
			pathDir.mkdirs();
		}
	}


	/**
	 * 删除文件：fileUrl为全路径文件名称
	 */
	public static boolean delFile(String fileUrl) {

		if (fileUrl != null && fileUrl.trim().length() > 0) {
			File file = new File(fileUrl);
			if (file.exists()) {
				boolean result = file.delete();
				return result;
			}
		}
		return false;
	}
	
	/***
	 * 文件移动
	 * @param fileUrl 原文件位置
	 * @param newfileUrl 移动目标位置
	 * @throws IOException
	 */
	public static void moveFile(String fileUrl,String newfileUrl) throws IOException{
		File newFile = new File(newfileUrl);
		File file = new File(fileUrl);
		FileCopyUtils.copy(file, newFile);
	}
	
	/**
	 * 文件预览
	 * @author zhouxincheng
	 * @date 2017年2月6日
	 * @param fileUrl
	 * @param response
	 * @param request
	 * @see
	 */
	public static void fileToShow(String fileUrl,String fileName,HttpServletResponse response,HttpServletRequest request){
		String extName = fileName.substring(
				fileName.lastIndexOf(".")).toUpperCase();
		setHeaderByFileEndName(extName, response);
		BufferedInputStream ins;
		OutputStream ops;
		byte[] bt ;
		InputStream is;
		int bufferSize;
		try {
			
			is=new FileInputStream(new File(fileUrl));
			ins = new BufferedInputStream(is);
			bufferSize = is.available();
			bt = new byte[bufferSize];
	        ins.read(bt, 0, bufferSize);
			response.getOutputStream().write(bt);
		    ops = response.getOutputStream();
	        ops.flush();
	        ops.close();
	        ins.close();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
		
	}

	/**
	 * 根据文件名后缀，判断格式，设置响应头
	 * @author zhouxincheng
	 * @date 2017年2月6日
	 * @param extName
	 * @param response
	 * @see
	 */
	private static void setHeaderByFileEndName(String extName,HttpServletResponse response){
		if(".DOC" .equals(extName)){response.setHeader("Content-type", "application/msword");}
		 else if(".DOCX" .equals(extName)){response.setHeader("Content-type","application/vnd.openxmlformats-officedocument.wordprocessingml.document");}
		 else if(".PDF" .equals(extName)){response.setHeader("Content-type","application/pdf");}
		 else if(".TXT" .equals(extName)){response.setHeader("Content-type","text/html");}
		 else if(".XLS" .equals(extName)){response.setHeader("Content-type","application/vnd.ms-excel");}
		 else if(".XLSX" .equals(extName)){response.setHeader("Content-type","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");}
		 else if(".PPT" .equals(extName)){response.setHeader("Content-type","application/vnd.ms-powerpoint");}
		 else if(".PPTX" .equals(extName)){response.setHeader("Content-type","application/vnd.openxmlformats-officedocument.presentationml.presentation");}
		 else if(".BMP" .equals(extName)){response.setHeader("Content-type","image/bmp");}
		 else if(".GIF" .equals(extName)){response.setHeader("Content-type","image/gif");}
		 else if(".IEF" .equals(extName)){response.setHeader("Content-type","image/ief");}
		 else if(".JPEG" .equals(extName)){response.setHeader("Content-type","image/jpeg");}
		 else if(".JPG" .equals(extName)){response.setHeader("Content-type","image/jpeg");}
		 else if(".PNG" .equals(extName)){response.setHeader("Content-type","image/png");}
		 else if(".TIFF" .equals(extName)){response.setHeader("Content-type","image/tiff");}
		 else if(".TIF" .equals(extName)){response.setHeader("Content-type","image/tif");}
	}

	public static String encodeChineseDownloadFileName(
			HttpServletRequest request, String pFileName) throws UnsupportedEncodingException {

		String filename = null;
		String agent = request.getHeader("USER-AGENT");
		if (null != agent){
			if (-1 != agent.indexOf("Firefox")) {//Firefox
				filename = "=?UTF-8?B?" + (new String(org.apache.commons.codec.binary.Base64.encodeBase64(pFileName.getBytes("UTF-8"))))+ "?=";
			}else if (-1 != agent.indexOf("Chrome")) {//Chrome
				filename = new String(pFileName.getBytes(), "ISO8859-1");
			} else {//IE7+
				filename = java.net.URLEncoder.encode(pFileName, "UTF-8");
				filename = StringUtils.replace(filename, "+", "%20");//替换空格
			}
		} else {
			filename = pFileName;
		}
		return filename;
	}


	/**
	 * 删除目录（文件夹）以及目录下的文件
	 * @param   sPath 被删除目录的文件路径
	 * @return  目录删除成功返回true，否则返回false
	 */
	public static boolean deleteDirectory(String sPath) {
		//如果sPath不以文件分隔符结尾，自动添加文件分隔符
		if (!sPath.endsWith(File.separator)) {
			sPath = sPath + File.separator;
		}
		File dirFile = new File(sPath);
		//如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		Boolean flag = true;
		//删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			//删除子文件
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag) {
					break;
				}
			} //删除子目录
			else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag) {
					break;
				}
			}
		}
		if (!flag) {
			return false;
		}
		//删除当前目录
		if (dirFile.delete()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 删除单个文件
	 * @param   sPath    被删除文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(String sPath) {
		Boolean flag = false;
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}


}

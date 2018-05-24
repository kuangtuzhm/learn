package com.zealot.learn.spi.spring.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class BaseController {
	
	private final static Logger logger = LoggerFactory.getLogger(BaseController.class);	
	
	public void writeJson(HttpServletResponse response, String json) throws IOException
	{
		String contentType = "application/json";  
        response.setContentType(contentType);  
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();  
        out.print(json);  
        out.flush();  
        out.close();
	}
	
//	public SessionUser getCurrentUser(HttpServletRequest request)
//	{
//		SessionUser sessionUser = (SessionUser)request.getSession().getAttribute("user");
//		return sessionUser;
//	}
	
	/**
	 * @Title: multiFileUpload 
	 * @Description:spring多文件上传
	 * @param request
	 * @param savePath
	 * @param position
	 * @return
	 * @throws Exception 
	 * @author wushuchu
	 * @date 2017年10月9日 下午3:00:21
	 */
	public Map<String, Object> multiFileUpload(HttpServletRequest request,String savePath) throws Exception {
		Map<String, Object> filePathMap = new HashMap<String, Object>();
		try {
			savePath = savePath.replace('/', File.separatorChar).replace('\\', File.separatorChar);

			File f = new File(savePath);
			if (!f.exists()) {
				f.mkdirs();
			}
			// 创建一个通用的多部分解析器
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
					request.getSession().getServletContext());
			if (multipartResolver.isMultipart(request)) {
				// 转换成多部分request
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				MultiValueMap<String, MultipartFile> mValueMap = multiRequest.getMultiFileMap();
				// 取得request中的所有文件名
				Iterator<Map.Entry<String, List<MultipartFile>>> mFileEntries = mValueMap.entrySet().iterator();

				while (mFileEntries.hasNext()) {

					Map.Entry<String, List<MultipartFile>> entry = mFileEntries.next();
					String filePathKey = entry.getKey();
					List<MultipartFile> mFileList = entry.getValue();
					// 取得上传文件
					if (mFileList != null && mFileList.size() > 0) {

						MultipartFile file = mFileList.get(0);

						if (file != null && !StringUtils.isEmpty(file.getOriginalFilename())) {

							String filename = file.getOriginalFilename();
							String newFileName = null;

							// 重命名上传后的文件名
							// 取得当前上传文件的文件名称
							newFileName = UUID.randomUUID().toString() + ""
									+ filename.substring(filename.lastIndexOf("."));
							filePathMap.put("newFileName", newFileName);

							String fileSavePath = savePath + File.separatorChar + newFileName;
							// 将文件上传至指定路径
							File localFile = new File(fileSavePath);
							file.transferTo(localFile);
							filePathMap.put(filePathKey, fileSavePath);
							filePathMap.put("fileSavePath", fileSavePath);
							filePathMap.put("filename", filename);
						}
					}
				}
			}
		} catch (Exception e) {
			System.err.println("上传图片文件" + filePathMap + "失败" + e.getMessage());
		}

		return filePathMap;
	}
	protected static void flushResponse(HttpServletResponse response, String responseContent) {

		response.setCharacterEncoding("UTF-8");
		String contentType = "application/json; charset=UTF-8";
		response.setContentType(contentType);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(responseContent);
		} catch (IOException e) {
			logger.error("Exception : {}",e.toString(),e);
		} finally {
			writer.flush();
			writer.close();
		}
	}
	public static HttpServletResponse sessionLoseGoLogin(HttpServletResponse response,Logger logger){
		try {
			response.sendRedirect("/login");
		} catch (IOException e) {
			logger.error("用户长时间未操作,Session丢失... response IOException",e);
		}
		logger.error("用户长时间未操作,Session丢失...");
		return response;
	}
}

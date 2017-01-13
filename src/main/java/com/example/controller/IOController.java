package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * author: zf
 * Date: 2017/1/10  16:01
 * Description:
 */
@RequestMapping("/io")
@RestController
public class IOController extends BaseController{
	private Logger logger = LoggerFactory.getLogger(IOController.class);

	@RequestMapping(value = "/type")
    public Object receiveFiles(HttpServletRequest request) throws IOException {
//		ServletInputStream inputStream = request.getInputStream();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		Set<Map.Entry<String, MultipartFile>> entries = fileMap.entrySet();
		List<Map<String,String>> list = new ArrayList<>();
		for (Map.Entry<String, MultipartFile> entry : entries) {
			String key = entry.getKey();
			MultipartFile file = fileMap.get(key);
//			file.get
		}
		return list;
	}

}

package com.ddshell.framework.common.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ddshell.framework.common.service.FileService;

@Controller
public class FileUploadController {
	@Autowired
	private FileService[] services;

	@RequestMapping(value = "/fileTransfer/{bizType}/{originalFilename}", method = RequestMethod.POST)
	@ResponseBody
	public String transfer(@PathVariable("bizType") String bizType,
			@PathVariable("originalFilename") String originalFilename,
			@RequestBody byte[] bytes) {
		if (!StringUtils.isEmpty(bizType)) {
			for (FileService service : services) {
				if (service.support(bizType)) {
					return service.saveUploadedFile(bizType, originalFilename,
							bytes);
				}
			}
		}
		return "";
	}

	@RequestMapping(value = "/fileUpload/{bizType}", method = RequestMethod.POST)
	@ResponseBody
	public Serializable upload(@PathVariable("bizType") String bizType,
			@RequestParam("file") MultipartFile file) {
		if (!StringUtils.isEmpty(bizType)) {
			for (FileService service : services) {
				if (service.support(bizType)) {
					return service.saveUploadedFile(bizType, file);
				}
			}
		}
		return "";
	}

	@RequestMapping(value = "/fileDownload/{bizType}/{id}.{extension}", method = RequestMethod.GET)
	public void downloadx(@PathVariable("bizType") String bizType,
			@PathVariable("id") String id,
			@PathVariable("extension") String extension,
			HttpServletResponse response) throws IOException {
		download(bizType, id + "." + extension, response);
	}

	@RequestMapping(value = "/fileDownload/{bizType}/{name:[^\\.]+}", method = RequestMethod.GET)
	public void download(@PathVariable("bizType") String bizType,
			@PathVariable("name") String name, HttpServletResponse response)
			throws IOException {
		for (FileService service : services) {
			if (service.support(bizType)) {
				File file = service.findUploadedFile(bizType, name);
				String originalFilename = service.findUploadedOriginalFilename(
						bizType, name);

				response.setContentLength((int) file.length());
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition",
						"attachment; filename=\"" + originalFilename + "\"");
				FileCopyUtils.copy(new FileInputStream(file),
						response.getOutputStream());

				return;
			}
		}
	}
}

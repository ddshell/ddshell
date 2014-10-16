package com.ddshell.framework.common.service.impl;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.ddshell.framework.common.service.FileService;
import com.ddshell.framework.common.util.FileSystemSupport;

@Component
public class DefaultFileServiceImpl implements FileService {

	@Autowired
	private FileSystemSupport fileSystemSupport;

	@Override
	public boolean support(String bizType) {
		return bizType.startsWith("default");
	}

	@Override
	public String saveUploadedFile(String bizType, MultipartFile file) {
		UUID uuid = UUID.randomUUID();
		fileSystemSupport.save(file, uuid, bizType);

		return uuid.toString();
	}

	@Override
	public String saveUploadedFile(String bizType, String originalFilename,
			byte[] bytes) {
		UUID uuid = UUID.randomUUID();
		fileSystemSupport.save(bytes, originalFilename, uuid, bizType);

		return uuid.toString();
	}

	@Override
	public File findUploadedFile(String bizType, String name) {
		return fileSystemSupport.get(name, bizType);
	}

	@Override
	public String findUploadedOriginalFilename(String bizType, String name) {
		return name;
	}

}

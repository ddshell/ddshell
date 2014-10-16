package com.ddshell.framework.common.service;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	/**
	 * 判断是否支持该biztype
	 * 
	 * @param biztype
	 * @return
	 */
	public boolean support(String biztype);

	/**
	 * 保存上传的文件
	 * 
	 * @param biztype
	 * @param file
	 * @return 文件唯一索引标识
	 */
	public String saveUploadedFile(String biztype, MultipartFile file);

	/**
	 * @param bizType
	 * @param originalFilename
	 * @param bytes
	 *            文件字节内容
	 * @return 文件唯一索引标识
	 */
	public String saveUploadedFile(String bizType, String originalFilename,
			byte[] bytes);

	/**
	 * 查找上传的文件
	 * 
	 * @param biztype
	 * @param id
	 * @return
	 */
	public File findUploadedFile(String biztype, String name);

	/**
	 * 查找上传的原始文件名
	 * 
	 * @param biztype
	 * @param id
	 * @return
	 */
	public String findUploadedOriginalFilename(String biztype, String name);

}

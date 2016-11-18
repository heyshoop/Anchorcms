package com.anchorcms.cms.service.assist.impl;

import java.util.List;

import com.anchorcms.cms.dao.assist.FileDao;
import com.anchorcms.cms.service.assist.FileService;
import com.anchorcms.cms.model.main.CmsFile;
import com.anchorcms.cms.model.main.Content;
import com.anchorcms.common.hibernate.Updater;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service
@Transactional
public class FileServiceImpl implements FileService {
	
	public CmsFile deleteById(Integer id) {
		return FileDao.deleteById(id);
	}
	
	public CmsFile deleteByPath(String path){
		return FileDao.deleteByPath(path);
	}
	
	public void deleteByContentId(Integer contentId){
		FileDao.deleteByContentId(contentId);
	}


	public CmsFile findById(Integer id) {
		return FileDao.findById(id);
	}

	public CmsFile findByPath(String path) {
		return FileDao.findByPath(path);
	}

	public List<CmsFile> getList(Boolean valid) {
		return FileDao.getList(valid);
	}

	public CmsFile save(CmsFile bean) {
		return FileDao.save(bean);
	}

	public void saveFileByPath(String filepath, String name, Boolean valid) {
		CmsFile attFile=new CmsFile();
		attFile.setFilePath(filepath);
		attFile.setFileName(name);
		attFile.setFileIsvalid(valid);
		save(attFile);
	}

	public void updateFileByPath(String path, Boolean valid, Content c) {
		CmsFile file;
		file=findByPath(path);
		if(file!=null){
			file.setContent(c);
			file.setFileIsvalid(valid);
			update(file);
		}
	}
	public void updateFileByPaths(String[] attachmentPaths,String[]picPaths,String mediaPath,
			String titleImg, String typeImg,String contentImg,Boolean valid,Content c){
		//处理附件有效性
		if(attachmentPaths!=null){
			for(String att:attachmentPaths){
				updateFileByPath(att, valid, c);
			}
		}
		//处理图片集
		if(picPaths!=null){
			for(String pic:picPaths){
				updateFileByPath(pic, valid, c);
			}
		}
		//处理多媒体
		if(StringUtils.isNotBlank(mediaPath)){
			updateFileByPath(mediaPath, valid, c);
		}
		//标题图
		if(StringUtils.isNotBlank(titleImg)){
			updateFileByPath(titleImg, valid, c);
		}
		//类型图
		if(StringUtils.isNotBlank(typeImg)){
			updateFileByPath(typeImg, valid, c);
		}
		//内容图
		if(StringUtils.isNotBlank(contentImg)){
			updateFileByPath(contentImg, valid, c);
		}
	}

	public CmsFile update(CmsFile bean) {
		Updater<CmsFile> updater = new Updater<CmsFile>(bean);
		bean = FileDao.updateByUpdater(updater);
		return bean;
	}

	@Resource
	private FileDao FileDao;

	public FileDao getFileDao() {
		return FileDao;
	}

	public void setFileDao(FileDao fileDao) {
		FileDao = fileDao;
	}
}
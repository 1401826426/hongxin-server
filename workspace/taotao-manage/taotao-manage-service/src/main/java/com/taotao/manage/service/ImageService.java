package com.taotao.manage.service;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.util.FileUtil;
import com.taotao.manage.service.api.IImageService;

@Service
public class ImageService implements IImageService{
	 
	@Autowired
	private PropertieService propertieService ; 
	
	private static Logger logger = LoggerFactory.getLogger(ImageService.class) ; 
	
	
	@Override
	public ResponseEntity<String> uploadImage(MultipartFile uploadFile) {
		if(uploadFile.isEmpty()){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST) ; 
		}
		String fileName =  uploadFile.getOriginalFilename() ; 
		if(fileName == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST) ;
		}
		String suffix = fileName.substring(fileName.lastIndexOf(".")) ; 
		Set<String> set = propertieService.getImageAllow() ;
		if(!set.contains(suffix)){
			return new ResponseEntity<>(HttpStatus.UNSUPPORTED_MEDIA_TYPE) ;
		}
		String name = null ; 
		try{
			name = FileUtil.writeFile(propertieService.getREPOSITORY_PATH(), "", uploadFile.getInputStream()) ; 
		}catch (Exception e) {
			logger.warn("文件创建失败" +propertieService.getREPOSITORY_PATH()+" "+fileName , e);
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR) ; 
		}
		String url = propertieService.getIMAGE_BASE_URL() + "//" + name ; 
		return new ResponseEntity<String>(url,HttpStatus.OK); 
	}


	@Override
	public byte[] getImage(String img) {
		try{
			return FileUtil.getByte(propertieService.getREPOSITORY_PATH(),img) ; 
		}catch (Exception e) {
			logger.warn("img獲取問題" + img);
		}
		return null ; 
	}
}



























package com.taotao.manage.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.manage.service.api.IImageService;

@Controller
@RequestMapping("/image")
public class ImageController {
	
	private Logger logger = LoggerFactory.getLogger(ImageController.class) ; 
	
	@Autowired
	private IImageService imageService ; 
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> uploadImage(@RequestParam("uploadFile") MultipartFile uploadFile){
		return imageService.uploadImage(uploadFile) ; 
	}
	
	
	@RequestMapping(value = "/{img}" , method = RequestMethod.GET )
    public void getImage(@PathVariable("img") String img ,
                         HttpServletResponse response) {
        byte[] bytes = imageService.getImage(img) ; 
        response.setContentType("image/png");
        if(bytes == null){
        	return ; 
        }
        try {
            OutputStream os = response.getOutputStream() ;
            os.write(bytes , 0 , bytes.length);
        } catch (IOException e) {
            logger.error("获取img出错"+img , e);
        }
        
    }
	
	
	
	
}

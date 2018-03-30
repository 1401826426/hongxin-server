package com.taotao.manage.service.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface IImageService {

	ResponseEntity<String> uploadImage(MultipartFile uploadFile);

	byte[] getImage(String img);

}

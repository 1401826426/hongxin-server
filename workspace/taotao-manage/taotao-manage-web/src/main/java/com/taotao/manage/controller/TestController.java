package com.taotao.manage.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taotao.manage.bean.Test;

@Controller
public class TestController {

	@RequestMapping(value = "/test" , method = RequestMethod.GET)
	public ResponseEntity<Test> queryItemCatListByParentId() {
		return new ResponseEntity<Test>(new Test("test"),HttpStatus.OK) ; 
	}

}

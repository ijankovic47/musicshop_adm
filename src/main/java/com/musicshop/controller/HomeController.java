package com.musicshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.musicshop.type.TypeDao;

@Controller
@RequestMapping("/")
public class HomeController {

	private TypeDao typeDao;
	
	@Autowired
	public HomeController(TypeDao typeDao) {
		this.typeDao=typeDao;
	}
	
	@RequestMapping()
	public String goHome() {
		typeDao.readAll();
		return "index";
	}
}

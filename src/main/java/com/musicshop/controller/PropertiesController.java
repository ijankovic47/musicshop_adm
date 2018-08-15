package com.musicshop.controller;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/properties")
public class PropertiesController {

	private Environment environent;
	
	public PropertiesController(Environment environent) {
		this.environent=environent;
	}
	@RequestMapping()
	public String getAPIurl() {
		return environent.getProperty("musicshop.api.url");
	}
}

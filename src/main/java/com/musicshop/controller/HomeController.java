package com.musicshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.musicshop.brand.BrandDao;
import com.musicshop.family.Family;
import com.musicshop.family.FamilyDao;

@Controller
@RequestMapping("/")
public class HomeController {

	private FamilyDao familyDao;
	private BrandDao brandDao;
	
	@Autowired
	public HomeController(FamilyDao familyDao, BrandDao brandDao) {
		this.familyDao=familyDao;
		this.brandDao=brandDao;
	}
	
	@RequestMapping()
	public String goHome(Model model) {
		
		List<Family> families=familyDao.readAll();
		model.addAttribute("families",families);
		model.addAttribute("brands", brandDao.readAll());
		return "home";
	}
}

package com.musicshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.musicshop.brand.BrandDao;
import com.musicshop.brand.BrandSort;
import com.musicshop.family.FamilyDao;

@Controller
@RequestMapping("/")
public class HomeController {

	private FamilyDao familyDao;
	private BrandDao brandDao;

	@Autowired
	public HomeController(FamilyDao familyDao, BrandDao brandDao) {
		this.familyDao = familyDao;
		this.brandDao = brandDao;
	}

	@RequestMapping()
	public String goHome(Model model) {

		model.addAttribute("families", familyDao.read(null, null, null, false));
		model.addAttribute("brands", brandDao.read(null, null, null, null, null, false, BrandSort.instrumentCountDESC));

		return "home";
	}
}

package com.musicshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.musicshop.brand.BrandDao;
import com.musicshop.type.TypeDao;

@Controller
@RequestMapping("/types")
public class TypesController {

	private TypeDao typeDao;
	private BrandDao brandDao;
	
	public TypesController(TypeDao typeDao, BrandDao brandDao) {
		this.typeDao=typeDao;
		this.brandDao=brandDao;
	}

	@RequestMapping
	public String showTypes(@RequestParam(name="familyId", required=true) Integer familyId, Model model) {

		model.addAttribute("familyId", familyId);
		model.addAttribute("types", typeDao.read(familyId));
		model.addAttribute("brands", brandDao.read(familyId, null));
		return "types";
	}
}

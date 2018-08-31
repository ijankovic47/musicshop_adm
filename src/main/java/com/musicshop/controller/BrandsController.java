package com.musicshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.musicshop.brand.BrandDao;
import com.musicshop.brand.BrandSort;

@Controller
@RequestMapping("/brands")
public class BrandsController {

	private BrandDao brandDao;
	
	@Autowired
	public BrandsController(BrandDao brandDao) {
		this.brandDao=brandDao;
	}
	
	@RequestMapping()
	public String getBrands(Model model, @RequestParam(name="sort", defaultValue = "nameASC") BrandSort sort) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		boolean isAdmin=auth.getAuthorities().stream().anyMatch(r->r.getAuthority().equals("ROLE_ADMIN"));
		model.addAttribute("sort", sort.toString());
		model.addAttribute("brands", brandDao.read(null, null, null, null, null, !isAdmin, sort));
		return "brands";
	}
}

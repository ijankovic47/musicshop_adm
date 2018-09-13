package com.musicshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		boolean isAdmin=auth.getAuthorities().stream().anyMatch(r->r.getAuthority().equals("ROLE_ADMIN"));
		model.addAttribute("families", familyDao.read(null, null, null, !isAdmin));
		model.addAttribute("brands", brandDao.read(null, null, null, null, null, !isAdmin, BrandSort.instrumentCountDESC));

		return "home";
	}
}

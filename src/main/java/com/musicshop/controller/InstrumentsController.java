package com.musicshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.musicshop.family.FamilyDao;

@Controller
@RequestMapping("/instruments")
public class InstrumentsController {

	private FamilyDao familyDao;
	
	@Autowired
	public InstrumentsController(FamilyDao familyDao) {
		this.familyDao=familyDao;
	}
	
	@RequestMapping
	public String showInstruments(@RequestParam(name="brandId", required=false) Integer brandId, Model model) {
		
		model.addAttribute("families", familyDao.read(brandId));
		
		return "instruments";
	}
}

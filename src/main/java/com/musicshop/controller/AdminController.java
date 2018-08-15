package com.musicshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.musicshop.brand.Brand;
import com.musicshop.brand.BrandDao;
import com.musicshop.family.Family;
import com.musicshop.family.FamilyDao;
import com.musicshop.instrument.Instrument;
import com.musicshop.property.Property;
import com.musicshop.property.PropertyDao;
import com.musicshop.type.Type;
import com.musicshop.type.TypeDao;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private FamilyDao familyDao;
	private BrandDao brandDao;
	private TypeDao typeDao;
	private PropertyDao propertyDao;
	
	@Autowired
	public AdminController(FamilyDao familyDao, BrandDao brandDao, TypeDao typeDao, PropertyDao propertyDao) {
		this.familyDao=familyDao;
		this.brandDao=brandDao;
		this.typeDao=typeDao;
		this.propertyDao=propertyDao;
	}
	@RequestMapping()
	public String showAdminPage(Model model) {
		model.addAttribute("instrument", new Instrument());
		model.addAttribute("family", new Family());
		model.addAttribute("type", new Type());
		model.addAttribute("property", new Property());
		model.addAttribute("brand", new Brand());
		model.addAttribute("brands", brandDao.readAll());
		model.addAttribute("families", familyDao.readAll());
	
		return "admin";
	}
	@RequestMapping(value="/brand", method=RequestMethod.POST, consumes="application/json", headers= "content-type=application/x-www-form-urlencoded")
	public String createBrand(@ModelAttribute Brand brand) {
		brandDao.saveEntity(brand);
		
		return "redirect:/admin";
	}
	@RequestMapping(value="/family", method=RequestMethod.POST, consumes="application/json", headers= "content-type=application/x-www-form-urlencoded")
	public String createFamily(@ModelAttribute Family family) {
		familyDao.saveEntity(family);
		
		return "redirect:/admin";
	}
	@RequestMapping(value="/type", method=RequestMethod.POST, consumes="application/json", headers= "content-type=application/x-www-form-urlencoded")
	public String createType(@ModelAttribute Type type) {
		typeDao.saveEntity(type);
		
		return "redirect:/admin";
	}
	@RequestMapping(value="/property", method=RequestMethod.POST, consumes="application/json", headers= "content-type=application/x-www-form-urlencoded")
	public String createProperty(@ModelAttribute Property property) {
		propertyDao.saveEntity(property);
		
		return "redirect:/admin";
	}
}

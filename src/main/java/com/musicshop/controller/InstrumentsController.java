package com.musicshop.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.musicshop.brand.BrandDao;
import com.musicshop.family.FamilyDao;
import com.musicshop.property.PropertyDao;
import com.musicshop.type.TypeDao;

@Controller
@RequestMapping("/instruments")
public class InstrumentsController {

	private FamilyDao familyDao;
	private BrandDao brandDao;
	private TypeDao typeDao;
	private PropertyDao propertyDao;

	@Autowired
	public InstrumentsController(FamilyDao familyDao, BrandDao brandDao, TypeDao typeDao, PropertyDao propertyDao) {
		this.familyDao = familyDao;
		this.brandDao = brandDao;
		this.typeDao = typeDao;
		this.propertyDao = propertyDao;
	}

	@RequestMapping
	public String showInstruments(@RequestParam Map<String, String> parameters, Model model) {

		String familyId = parameters.get("familyId");
		String brandId = parameters.get("brandId");
		String typeId = parameters.get("typeId");
		String propertyId = parameters.get("propertyId");
		
		if(brandId!=null) {
			model.addAttribute("filter", "&brandId="+brandId);
		}
		if (propertyId != null && brandId != null) {
			return "instruments";
		}
		if (propertyId != null) {
			model.addAttribute("brands", brandDao.read(null,null));
			model.addAttribute("brandFilter", "&propertyId="+propertyId);
			return "instruments";
		}
		if (typeId != null && brandId != null) {
			model.addAttribute("properties", propertyDao.read(Integer.valueOf(typeId),Integer.valueOf(brandId)));
			return "instruments";
		}
		if(typeId!=null) {
			model.addAttribute("properties", propertyDao.read(Integer.valueOf(typeId), null));
			model.addAttribute("brands", brandDao.read(null, Integer.valueOf(typeId)));
			model.addAttribute("brandFilter", "&typeId="+typeId);
			return "instruments";
		}
		if(familyId!=null&&brandId!=null) {
			model.addAttribute("types", typeDao.read(Integer.valueOf(familyId)));
			return "instruments";
		}
		if(familyId!=null) {
			model.addAttribute("types", typeDao.read(Integer.valueOf(familyId)));
			model.addAttribute("brands", brandDao.read(null, null));
			model.addAttribute("brandFilter", "&familyId="+familyId);
			return "instruments";
		}
		if(brandId!=null) {
			model.addAttribute("families", familyDao.read(Integer.valueOf(brandId)));
			return "instruments";
		}
		return "instruments";
	}
}

package com.musicshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.musicshop.brand.BrandDao;
import com.musicshop.family.FamilyDao;
import com.musicshop.instrument.InstrumentDao;
import com.musicshop.property.PropertyDao;
import com.musicshop.type.TypeDao;

@Controller
@RequestMapping("/instruments")
public class InstrumentsController {

	private FamilyDao familyDao;
	private BrandDao brandDao;
	private TypeDao typeDao;
	private PropertyDao propertyDao;
	private InstrumentDao instrumentDao;

	@Autowired
	public InstrumentsController(FamilyDao familyDao, BrandDao brandDao, TypeDao typeDao, PropertyDao propertyDao, InstrumentDao instrumentDao) {
		this.familyDao = familyDao;
		this.brandDao = brandDao;
		this.typeDao = typeDao;
		this.propertyDao = propertyDao;
		this.instrumentDao=instrumentDao;
	}

	@RequestMapping
	public String showInstruments(@RequestParam(name="familyId", required=false)Integer familyId,
			@RequestParam(name="typeId", required=false)Integer typeId,
			@RequestParam(name="propertyId", required=false)Integer propertyId,
			@RequestParam(name="brandId", required=false)Integer brandId, Model model) {

		model.addAttribute("instruments", instrumentDao.read(familyId, typeId, propertyId, brandId));
		
		if(brandId!=null) {
			model.addAttribute("filter", "&brandId="+brandId);
		}
		if (propertyId != null && brandId != null) {
			return "instruments";
		}
		if (propertyId != null) {
			model.addAttribute("brands", brandDao.read(null,null, propertyId));
			model.addAttribute("brandFilter", "&propertyId="+propertyId);
			return "instruments";
		}
		if (typeId != null && brandId != null) {
			model.addAttribute("properties", propertyDao.read(typeId,brandId));
			return "instruments";
		}
		if(typeId!=null) {
			model.addAttribute("properties", propertyDao.read(typeId, null));
			model.addAttribute("brands", brandDao.read(null, typeId, null));
			model.addAttribute("brandFilter", "&typeId="+typeId);
			return "instruments";
		}
		if(familyId!=null&&brandId!=null) {
			model.addAttribute("types", typeDao.read(familyId, brandId));
			return "instruments";
		}
		if(familyId!=null) {
			model.addAttribute("types", typeDao.read(familyId, brandId));
			model.addAttribute("brands", brandDao.read(familyId, null, null));
			model.addAttribute("brandFilter", "&familyId="+familyId);
			return "instruments";
		}
		if(brandId!=null) {
			model.addAttribute("families", familyDao.read(brandId));
			return "instruments";
		}
		return "instruments";
	}
}

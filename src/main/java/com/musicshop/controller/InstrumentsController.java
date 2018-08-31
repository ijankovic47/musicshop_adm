package com.musicshop.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.musicshop.brand.BrandDao;
import com.musicshop.brand.BrandSort;
import com.musicshop.currency.CurrencyService;
import com.musicshop.family.Family;
import com.musicshop.family.FamilyDao;
import com.musicshop.instrument.Instrument;
import com.musicshop.instrument.InstrumentDao;
import com.musicshop.instrument.InstrumentSort;
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
	private CurrencyService currencyService;

	@Autowired
	public InstrumentsController(FamilyDao familyDao, BrandDao brandDao, TypeDao typeDao, PropertyDao propertyDao,
			InstrumentDao instrumentDao, CurrencyService currencyService) {
		this.familyDao = familyDao;
		this.brandDao = brandDao;
		this.typeDao = typeDao;
		this.propertyDao = propertyDao;
		this.instrumentDao = instrumentDao;
		this.currencyService = currencyService;
	}

	@RequestMapping
	public String showInstruments(@RequestParam(name = "familyId", required = false) Integer familyId,
			@RequestParam(name = "typeId", required = false) Integer typeId,
			@RequestParam(name = "propertyId", required = false) Integer propertyId,
			@RequestParam(name = "brandId", required = false) Integer brandId,
			@RequestParam(name = "pageSize", defaultValue = "3") Integer pageSize,
			@RequestParam(name = "pageNumber", defaultValue = "1") Integer pageNumber,
			@RequestParam(name = "priceMin", required = false) Double priceMin,
			@RequestParam(name = "priceMax", required = false) Double priceMax,
			@RequestParam(name = "sort", defaultValue="priceASC") InstrumentSort sort, Model model) {

		String treeFilter = (familyId != null ? "&familyId=" + familyId : "")
				+ (typeId != null ? "&typeId=" + typeId : "") + (propertyId != null ? "&propertyId=" + propertyId : "");
		String pricesFilter = (priceMin != null ? "&priceMin=" + priceMin : "")
				+ (priceMax != null ? "&priceMax=" + priceMax : "");
		String brandFilter = (brandId != null ? "&brandId=" + brandId : "");
		String pageSizeFilter = "&pageSize=" + pageSize;
		String sortFilter = (sort!=null?"&sort=" + sort:"");
		String pageNumberFilter = "&pageNumber=" + pageNumber;

		model.addAttribute("paginationFilter", pageSizeFilter + treeFilter + brandFilter + pricesFilter + sortFilter);
		model.addAttribute("pageSizeFilter", treeFilter + brandFilter + pricesFilter + sortFilter);
		model.addAttribute("treefilter", brandFilter + pricesFilter + pageSizeFilter + sortFilter);
		model.addAttribute("brandFilter", treeFilter + pricesFilter + pageSizeFilter + sortFilter);
		model.addAttribute("sortFilter", treeFilter + brandFilter + pricesFilter + pageSizeFilter + pageNumberFilter);

		model.addAttribute("pageSize", pageSize);
		model.addAttribute("page", pageNumber);
		model.addAttribute("sort", sort);

		priceMin = currencyService.EURtoRSD(priceMin);
		priceMax = currencyService.EURtoRSD(priceMax);

		List<Instrument> instruments = instrumentDao.read(familyId, typeId, propertyId, brandId, pageSize, pageNumber,
				priceMin, priceMax, sort);
		model.addAttribute("instruments", instruments);

		List<Double> instrumentPrices = instrumentDao.prices(familyId, typeId, propertyId, brandId, priceMin, priceMax);

		model.addAttribute("family", new Family());
		
		if (priceMin == null && priceMax == null) {
			Map<Integer, Integer> priceGroups = createPriceGroups(instrumentPrices);
			model.addAttribute("priceGroups", priceGroups);
		}

		int instrumentCount = instrumentPrices.size();
		model.addAttribute("instrumentCount", instrumentCount);
		model.addAttribute("pages", calculateTotalPages(instrumentCount, pageSize));

		if (propertyId != null && brandId != null) {
			return "instruments";
		}
		if (propertyId != null) {
			model.addAttribute("brands", brandDao.read(null, null, propertyId, priceMin, priceMax, !isAdmin(), BrandSort.instrumentCountDESC));
			return "instruments";
		}
		if (typeId != null && brandId != null) {
			model.addAttribute("properties", propertyDao.read(typeId, brandId, priceMin, priceMax, !isAdmin()));
			return "instruments";
		}
		if (typeId != null) {
			model.addAttribute("properties", propertyDao.read(typeId, null, priceMin, priceMax, !isAdmin()));
			model.addAttribute("brands", brandDao.read(null, typeId, null, priceMin, priceMax, !isAdmin(), BrandSort.instrumentCountDESC));
			return "instruments";
		}
		if (familyId != null && brandId != null) {
			model.addAttribute("types", typeDao.read(familyId, brandId, priceMin, priceMax, !isAdmin()));
			return "instruments";
		}
		if (familyId != null) {
			model.addAttribute("types", typeDao.read(familyId, brandId, priceMin, priceMax, !isAdmin()));
			model.addAttribute("brands", brandDao.read(familyId, null, null, priceMin, priceMax, !isAdmin(), BrandSort.instrumentCountDESC));
			return "instruments";
		}
		if (brandId != null) {
			model.addAttribute("families", familyDao.read(brandId, priceMin, priceMax, !isAdmin()));
			return "instruments";
		}
		    model.addAttribute("families", familyDao.read(brandId, priceMin, priceMax, !isAdmin()));
		    model.addAttribute("brands", brandDao.read(familyId, null, null, priceMin, priceMax, !isAdmin(), BrandSort.instrumentCountDESC));
		    
		return "instruments";
	}

	private Map<Integer, Integer> createPriceGroups(List<Double> instrumentPrices) {

		Map<Integer, Integer> priceCount = new LinkedHashMap<Integer, Integer>();

		for (Double price : instrumentPrices) {
			sortPriceRanges(priceCount, currencyService.RSDtoEUR(price), 100);
		}
		return priceCount;
	}

	private void sortPriceRanges(Map<Integer, Integer> priceCount, Double instrumentPrice, Integer breakingAmount) {

		if (!priceCount.containsKey(breakingAmount)) {
			priceCount.put(breakingAmount, 0);
		}
		if (instrumentPrice < breakingAmount || breakingAmount > 2000) {
			priceCount.put(breakingAmount, 1 + priceCount.get(breakingAmount));
			return;
		} else {
			if (breakingAmount < 500) {
				breakingAmount += 100;
			} else if (breakingAmount >= 500 && breakingAmount < 1000) {
				breakingAmount += 250;
			} else if (breakingAmount >= 1000 && breakingAmount <= 2000) {
				breakingAmount += 500;
			}
			sortPriceRanges(priceCount, instrumentPrice, breakingAmount);
		}
	}

	private int calculateTotalPages(int instrumentCount, int pageSize) {
		int pages = (int) Math.ceil((double) instrumentCount / (double) pageSize);
		return pages < 1 ? 1 : pages;
	}
	
	private boolean isAdmin() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		boolean isAdmin=auth.getAuthorities().stream().anyMatch(r->r.getAuthority().equals("ROLE_ADMIN"));
		return isAdmin;
	}
}

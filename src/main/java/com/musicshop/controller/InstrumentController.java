package com.musicshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.musicshop.instrument.Instrument;
import com.musicshop.instrument.InstrumentDao;

@Controller
@RequestMapping("/instrument")
public class InstrumentController {

	private InstrumentDao instrumentDao;
	
	@Autowired
	public InstrumentController(InstrumentDao instrumentDao) {
		this.instrumentDao=instrumentDao;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public String getInstrument(@PathVariable("id") Integer id, Model model) {
		
		model.addAttribute("instrument", instrumentDao.readById(id));
		return "instrument";
	}
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public List<Instrument> readByIds(@RequestParam("ids") List<Integer> ids){
		return instrumentDao.readByIds(ids);
	}
}

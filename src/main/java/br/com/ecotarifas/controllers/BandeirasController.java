package br.com.ecotarifas.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.ecotarifas.model.entities.Bandeiras;
import br.com.ecotarifas.repositories.BandeirasRepository;

@Controller
@RequestMapping("/bandeiras")
public class BandeirasController {
	
	@Autowired
	private BandeirasRepository bandeirasRepository;
	@InitBinder
	public void allowEmptyDataBinding(WebDataBinder binder) {
	    binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@GetMapping("/update/{id}/{bandVerde}/{bandAmarela}/{bandVermelha1}/{bandVermelha2}/{taxaFixa}/{verde}/{amarela}/{vermelha1}/{vermelha2}")
	public ModelAndView excluir(@PathVariable String id, @PathVariable String bandVerde, @PathVariable String bandAmarela, @PathVariable String bandVermelha1, @PathVariable String bandVermelha2, @PathVariable String taxaFixa, @PathVariable String verde, @PathVariable String amarela, @PathVariable String vermelha1, @PathVariable String vermelha2) {
		//http://localhost:8080/consumo/incluir/A/23/25/33/2019-06-15/15/200
		Bandeiras bandeiras = new Bandeiras();
		bandeiras.setId(new Long(id));
		bandeiras.setBandVerde(new BigDecimal(bandVerde));
		bandeiras.setBandAmarela(new BigDecimal(bandAmarela));
		bandeiras.setBandVermelha1(new BigDecimal(bandVermelha1));
		bandeiras.setBandVermelha2(new BigDecimal(bandVermelha2));
		bandeiras.setTaxaFixa(new BigDecimal(taxaFixa));
		bandeiras.setVerde(new Integer(verde.equals("true") || verde.equals("1") ? 1 : 0));
		bandeiras.setAmarela(new Integer(amarela.equals("true") || amarela.equals("1") ? 1 : 0));
		bandeiras.setVermelha1(new Integer(vermelha1.equals("true") || vermelha1.equals("1") ? 1 : 0));
		bandeiras.setVermelha2(new Integer(vermelha2.equals("true") || vermelha2.equals("1") ? 1 : 0));
		bandeirasRepository.save(bandeiras);
		return new ModelAndView("redirect:/view");
	}
	
	@GetMapping(path="/listar", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Bandeiras> listarConsumos() {
		return bandeirasRepository.findAll();
	}	
}
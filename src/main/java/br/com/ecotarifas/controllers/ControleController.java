package br.com.ecotarifas.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

import br.com.ecotarifas.model.entities.Controle;
import br.com.ecotarifas.repositories.ControleRepository;

@Controller
@RequestMapping("/controle")
public class ControleController {
	
	@Autowired
	private ControleRepository controleRepository;
	
	@InitBinder
	public void allowEmptyDataBinding(WebDataBinder binder) {
	    binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@GetMapping("/incluir/{nome}/{status}/{statdtini}/{control}/{ctrldtini}/{estado}")
	public ModelAndView excluir(@PathVariable String nome, @PathVariable String status, @PathVariable String statdtini, @PathVariable String control, @PathVariable String ctrldtini, @PathVariable String estado) {
		//http://localhost:8080/controle/incluir/A/1/2019-01-01/1/2019-01-02/1
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate statdtiniformat = LocalDate.parse(statdtini,formatter);
		LocalDate ctrldtiniformat = LocalDate.parse(ctrldtini,formatter);
		
		Controle controle = new Controle();
		controle.setNome(nome);
		controle.setStatus(new Integer(status));
		controle.setStatdtini(statdtiniformat);
		controle.setControle(new Integer(control));
		controle.setCtrldtini(ctrldtiniformat);
		controle.setEstado(new Integer(estado));
		controleRepository.save(controle);
		return new ModelAndView("redirect:/view");
	}
	
	@GetMapping(path="/listar", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Controle> listarControles() {
		return controleRepository.findAll();
	}
	
	@GetMapping(path="/deletar/{controleid}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ModelAndView deletarControle(@PathVariable String controleid) {
		controleRepository.deleteById(new Long(controleid));
		return new ModelAndView("redirect:/view");
	}
}
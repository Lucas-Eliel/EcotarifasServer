package br.com.ecotarifas.controllers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

import br.com.ecotarifas.model.entities.Consumo;
import br.com.ecotarifas.repositories.ConsumoRepository;

@Controller
@RequestMapping("/consumo")
public class ConsumoController {
	
	@Autowired
	private ConsumoRepository consumoRepository;
	@InitBinder
	public void allowEmptyDataBinding(WebDataBinder binder) {
	    binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@GetMapping("/incluir/{nome}/{tensao}/{corrente}/{potencia}/{data}/{uso}/{total}")
	public ModelAndView excluir(@PathVariable String nome, @PathVariable String tensao, @PathVariable String corrente, @PathVariable String potencia, @PathVariable String data, @PathVariable String uso, @PathVariable String total) {
		//http://localhost:8080/consumo/incluir/Microondas/127/0.21/47.42/2019-06-15/576/200
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String dataAtual = LocalDate.now().toString();
		LocalDate date = LocalDate.parse(dataAtual, formatter);
		
		Consumo consumo = new Consumo();
		consumo.setNome(nome);
		
		BigDecimal v = new BigDecimal(tensao);
		consumo.setTensao(v);
		
		BigDecimal i = new BigDecimal(corrente);
		consumo.setCorrente(i);
		
		BigDecimal p = new BigDecimal(potencia).divide(new BigDecimal(1000)).setScale(8, RoundingMode.HALF_EVEN);
		consumo.setPotencia(p);
		
		consumo.setData(date);
		
		BigDecimal us = new BigDecimal(uso).multiply(new BigDecimal(0.0000002778)).setScale(8, RoundingMode.HALF_EVEN);
		consumo.setUso(us);
		
		BigDecimal tot = p.multiply(us);
		consumo.setTotal(tot);
		
		consumoRepository.save(consumo);
		return new ModelAndView("redirect:/view");
	}
	
	@GetMapping(path="/listar", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Consumo> listarConsumos() {
		return consumoRepository.findAll();
	}
	
	@GetMapping(path="/listar/{dataInicial}/{dataFinal}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Consumo> listarConsumosPeriodo(@PathVariable String dataInicial,@PathVariable String dataFinal) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dtIni = LocalDate.parse(dataInicial, formatter);
		LocalDate dtFim = LocalDate.parse(dataFinal, formatter);
		List<Consumo> medidas = consumoRepository.findConsumoPeriodo(dtIni, dtFim);
		return medidas;
	}
	
	@GetMapping(path="/deletar/{consumoid}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ModelAndView deletarConsumosPeriodo(@PathVariable String consumoid) {
		consumoRepository.deleteById(new Long(consumoid));
		return new ModelAndView("redirect:/view");
	}
	
	@GetMapping(path="/buscarConsumoTotal/{mes}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String buscarConsumoTotal(@PathVariable String mes) {
		BigDecimal m = new BigDecimal(mes);
		BigDecimal total = consumoRepository.findConsumoTotal(m);
		return "[{"+"\"total\":"+total+"}]";
	}
	
	@GetMapping(path="/buscarDadosGrafico/{mes}/{ano}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Object> buscarConsumoSemanal(@PathVariable String mes,@PathVariable String ano) {
		BigDecimal m = new BigDecimal(mes);
		BigDecimal a = new BigDecimal(ano);
		
		List<Object> nomeCargas = consumoRepository.findNomeCargas(m, a);
		List<Object> dadosGraficoFinal = new ArrayList<>();

		for(int i=0; i < nomeCargas.size(); i++) {
			Object dadosGrafico = consumoRepository.findDadosGrafico(m, a, nomeCargas.get(i).toString());
			dadosGraficoFinal.add(dadosGrafico);
		}
		return dadosGraficoFinal;
	}
}
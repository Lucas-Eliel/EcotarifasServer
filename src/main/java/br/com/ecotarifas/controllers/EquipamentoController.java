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
import br.com.ecotarifas.model.entities.Equipamento;
import br.com.ecotarifas.repositories.EquipamentoRepository;

@Controller
@RequestMapping("/equipamento")
public class EquipamentoController {
	
	@Autowired
	private EquipamentoRepository equipamentoRepository;
	
	@InitBinder
	public void allowEmptyDataBinding(WebDataBinder binder) {
	    binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@GetMapping("/incluir/{nome}/{meta}/{ctrldiario}/{arduino}")
	public ModelAndView excluir(@PathVariable String nome, @PathVariable String meta, @PathVariable String ctrldiario, @PathVariable String arduino) {
		//http://localhost:8080/equipamento/incluir/A/100/500/10
		Equipamento equipamento = new Equipamento();
		equipamento.setNome(nome);
		equipamento.setMeta(new BigDecimal(meta));
		equipamento.setCtrldiario(new BigDecimal(ctrldiario));
		equipamento.setArduino(new Integer(arduino));
		equipamentoRepository.save(equipamento);
		return new ModelAndView("redirect:/view");
	}
	
	@GetMapping(path="/listar", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Equipamento> listarEquipamentos() {
		return equipamentoRepository.findAll();
	}
	
	@GetMapping(path="/buscarMetaTotal", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String buscarConsumoTotal() {
		BigDecimal meta = equipamentoRepository.findMetaTotal();
		return "[{"+"\"meta\":"+meta+"}]";
	}
	
	@GetMapping(path="/deletar/{equipamentoid}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ModelAndView deletarEquipamento(@PathVariable String equipamentoid) {
		equipamentoRepository.deleteById(new Long(equipamentoid));
		return new ModelAndView("redirect:/view");
	}

}
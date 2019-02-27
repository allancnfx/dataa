package br.project.eleicao.controller;

import br.project.eleicao.service.VotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/eleicao/{eleicaoId}/resultado")
public class ResultadoController {

    @Autowired
    private VotacaoService votacaoService;
   
    
        @GetMapping("/listar")
    public ModelAndView modelAndView(@PathVariable("eleicaoId") long eleicaoId, ModelMap model) {
        model.addAttribute("resultados", votacaoService.recuperar(eleicaoId));
        model.addAttribute("eleicaoId", eleicaoId);
        return new ModelAndView("/resultado/list", model);
    }
    
}

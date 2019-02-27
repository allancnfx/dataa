/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.project.eleicao.controller;

import br.project.eleicao.domain.Votacao;
import br.project.eleicao.service.CandidatoService;
import br.project.eleicao.service.CargoService;
import br.project.eleicao.service.EleicaoService;
import br.project.eleicao.service.EleitorService;
import br.project.eleicao.service.VotacaoService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("eleicao/{eleicaoId}/eleitor/{eleitorId}/votacao")
public class VotacaoController {

    @Autowired
    private VotacaoService votacaoService;

    @Autowired
    private EleitorService eleitorService;

    @Autowired
    private CargoService cargoService;

    @Autowired
    private CandidatoService candidatoService;

    @Autowired
    private EleicaoService eleicaoService;

    @GetMapping("/listar")
    public ModelAndView modelAndView(@PathVariable("eleicaoId") long eleicaoId, ModelMap model) {
        model.addAttribute("eleicoes", eleicaoService.recuperar());
        model.addAttribute("cargos", cargoService.recuperarPorEleicaoId(eleicaoId));
        model.addAttribute("candidatos", candidatoService.recuperarPorEleicaoId(eleicaoId));
        model.addAttribute("eleitors", eleitorService.recuperarPorEleicaoId(eleicaoId));
        model.addAttribute("votacao", votacaoService.recuperarPorEleicaoId(eleicaoId));
        model.addAttribute("eleicaoId", eleicaoId);
        return new ModelAndView("/votacao/list", model);
    }
    
    @PostMapping("/salvar")
    public String salvar(@PathVariable("eleicaoId") long eleicaoId, @PathVariable("eleitorId") long eleitorId, @Valid @ModelAttribute("votacao") Votacao votacao, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "/eleitor/list";
        }

        votacaoService.salvar(votacao, eleicaoId, eleitorId);
        attr.addFlashAttribute("mensagem", "Cargo salva com sucesso.");
        return "redirect:/eleicao/" + eleicaoId + "/eleitors/listar";
    }

}

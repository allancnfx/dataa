/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.project.eleicao.controller;

import br.project.eleicao.domain.Eleitor;
import br.project.eleicao.service.EleitorService;
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
@RequestMapping("eleicao/{eleicaoId}/eleitors")
public class EleitorController {

    @Autowired
    private EleitorService eleitorService;

    @GetMapping("/listar")
    public ModelAndView modelAndView(@PathVariable("eleicaoId") long eleicaoId, ModelMap model) {
        model.addAttribute("eleitors", eleitorService.recuperarPorEleicaoId(eleicaoId));
        model.addAttribute("eleicaoId", eleicaoId);
        return new ModelAndView("/eleitor/list", model);
    }
    
        @GetMapping("/cadastro")
    public String preSalvar(@ModelAttribute("eleitor") Eleitor eleitor, @PathVariable("eleicaoId") long eleicaoId) {
        return "/eleitor/add";
    }

    @PostMapping("/salvar")
    public String salvar(@PathVariable("eleicaoId") long eleicaoId, @Valid @ModelAttribute("eleitor") Eleitor eleitor, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "/eleitor/add";
        }
        eleitorService.salvar(eleitor, eleicaoId);
        attr.addFlashAttribute("mensagem", "Cargo salva com sucesso.");
        return "redirect:/eleicao/" + eleicaoId + "/eleitors/listar";
    }
}

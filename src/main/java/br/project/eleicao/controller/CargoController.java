/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.project.eleicao.controller;

import br.project.eleicao.domain.Cargo;
import br.project.eleicao.service.CargoService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("eleicao/{eleicaoId}/cargos")
public class CargoController {

    @Autowired
    private CargoService cargoService;

    @GetMapping("/listar")
    public ModelAndView modelAndView(@PathVariable("eleicaoId") long eleicaoId, ModelMap model) {
        model.addAttribute("cargos", cargoService.recuperarPorEleicaoId(eleicaoId));
        model.addAttribute("eleicaoId", eleicaoId);
        return new ModelAndView("/cargo/list", model);
    }
    

        @GetMapping("/cadastro")
    public String preSalvar(@ModelAttribute("cargo") Cargo cargo, @PathVariable("eleicaoId") long eleicaoId) {
        return "/cargo/add";
    }

    @PostMapping("/salvar")
    public String salvar(@PathVariable("eleicaoId") long eleicaoId, @Valid @ModelAttribute("cargo") Cargo cargo, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "/cargo/add";
        }

        cargoService.salvar(cargo, eleicaoId);
        attr.addFlashAttribute("mensagem", "Cargo salva com sucesso.");
        return "redirect:/eleicao/" + eleicaoId + "/cargos/listar";
    }

    @GetMapping("/{cargoId}/atualizar")
    public ModelAndView preAtualizar(@PathVariable("eleicaoId") long eleicaoId, @PathVariable("cargoId") long cargoId, ModelMap model) {
        Cargo cargo = cargoService.recuperarPorEleicaoIdECargoId(eleicaoId, cargoId);
        model.addAttribute("cargo", cargo);
        model.addAttribute("eleicaoId", eleicaoId);
        return new ModelAndView("/cargo/add", model);
    }
    

    @PutMapping("/salvar")
    public String atualizar(@PathVariable("eleicaoId") long eleicaoId, @Valid @ModelAttribute("cargo") Cargo cargo, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "/cargo/add";
        }

        cargoService.atualizar(cargo, eleicaoId);
        attr.addFlashAttribute("mensagem", "Cargo atualizada com sucesso.");
        return "redirect:/eleicao/" + eleicaoId + "/cargos/listar";
    }

    @GetMapping("/{cargoId}/remover")
    public String remover(@PathVariable("eleicaoId") long eleicaoId, @PathVariable("cargoId") long cargoId, RedirectAttributes attr) {
        cargoService.excluir(eleicaoId, cargoId);
        attr.addFlashAttribute("mensagem", "Cargo excluída com sucesso.");
        return "redirect:/eleicao/" + eleicaoId + "/cargos/listar";
    }

}

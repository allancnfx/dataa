package br.project.eleicao.controller;

import br.project.eleicao.domain.Eleicao;
import br.project.eleicao.service.EleicaoService;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("eleicao")
public class EleicaoController {

    @Autowired
    private EleicaoService eleicaoService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }

    @GetMapping("/listar")
    public ModelAndView modelAndView(ModelMap model) {
        model.addAttribute("eleicoes", eleicaoService.recuperar());
        return new ModelAndView("/eleicao/list", model);
    }
    
    @GetMapping("/cadastro")
    public String preSalvar(@ModelAttribute("eleicao") Eleicao eleicao) {
        return "/eleicao/add";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("eleicao") Eleicao eleicao, BindingResult result, RedirectAttributes attr, ModelMap model) {
        if (result.hasErrors()) {
            return "/eleicao/add";
        }
        eleicaoService.salvar(eleicao);
        attr.addFlashAttribute("mensagem", "eleição criada com sucesso.");
        return "redirect:/eleicao/listar";
    }

    @GetMapping("/{id}/atualizar")
    public ModelAndView preAtualizar(@PathVariable("id") long id, ModelMap model) {
        Eleicao eleicao = eleicaoService.recuperarPorId(id);
        model.addAttribute("eleicao", eleicao);
        return new ModelAndView("/eleicao/add", model);
    }

    @PutMapping("/salvar")
    public String atualizar(@Valid @ModelAttribute("playlist") Eleicao eleicao, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "/eleicoes/add";
        }

        eleicaoService.atualizar(eleicao);
        attr.addFlashAttribute("mensagem", "Eleicao atualizada com sucesso.");
        return "redirect:/eleicao/listar";
    }

    @GetMapping("/{id}/remover")
    public String remover(@PathVariable("id") long id, RedirectAttributes attr) {
        eleicaoService.excluir(id);
        attr.addFlashAttribute("mensagem", "Eleição excluída com sucesso.");
        return "redirect:/eleicao/listar";
    }

}

package br.project.eleicao.controller;

import br.project.eleicao.dao.CargoDAO;
import br.project.eleicao.generico.CargoConverter;
import br.project.eleicao.domain.Candidato;
import br.project.eleicao.domain.Cargo;
import br.project.eleicao.domain.Image;
import br.project.eleicao.service.CandidatoService;
import br.project.eleicao.service.CargoService;
import br.project.eleicao.service.ImageService;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Classe Candidato Controller.
 *
 *
 * Classe responsalvel por conter os metodos de controller de requsição da viwe
 * e model
 *
 *
 * @author Allan Christiant
 */
@Controller
@RequestMapping("eleicao/{eleicaoId}/candidatos")
public class CandidatoController {

    @Autowired
    private CandidatoService candidatoService;

    @Autowired
    private CargoService cargoService;

    @Autowired
    private CargoDAO cargoDAO;

    private final ImageService imageService;

    @InitBinder
    public void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
        binder.registerCustomEditor(Cargo.class, new CargoConverter(cargoDAO));
    }

    @Autowired
    public CandidatoController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/listar")
    public ModelAndView modelAndView(@PathVariable("eleicaoId") long eleicaoId, ModelMap model, Pageable pageable) throws IOException {
        final Page<Image> page = imageService.findPage(pageable);
        model.addAttribute("page", page);
        model.addAttribute("candidatos", candidatoService.recuperarPorEleicaoId(eleicaoId));
        model.addAttribute("cargos", cargoService.recuperarPorEleicaoId(eleicaoId));
        model.addAttribute("eleicaoId", eleicaoId);
        return new ModelAndView("/candidato/list", model);
    }

    @GetMapping("/cadastro")
    public ModelAndView preSalvar(@ModelAttribute("candidato") Candidato candidato, @ModelAttribute("cargo") Cargo cargo, @PathVariable("eleicaoId") long eleicaoId, ModelMap model) {
        model.addAttribute("cargos", cargoService.recuperarPorEleicaoId(eleicaoId));
        return new ModelAndView("/candidato/add", model);
    }

    @PostMapping("/salvar")
    public ModelAndView salvar(@PathVariable("eleicaoId") long eleicaoId, @Valid @ModelAttribute("candidato") Candidato candidato, BindingResult result, RedirectAttributes attr, HttpServletRequest request, ModelMap model) {

        //long cargoId = Long.parseLong(request.getParameter("cargoId"));
        if (result.hasErrors()) {
            model.addAttribute("cargos", cargoService.recuperarPorEleicaoId(eleicaoId));
            return new ModelAndView("/candidato/add", model);
        }
        candidatoService.salvar(candidato, eleicaoId);
        attr.addFlashAttribute("mensagem", "Candidato salva com sucesso.");
        return new ModelAndView("redirect:/eleicao/" + eleicaoId + "/candidatos/listar");

    }

    @GetMapping("/{candidatoId}/atualizar")
    public ModelAndView preAtualizar(@PathVariable("eleicaoId") long eleicaoId, @PathVariable("candidatoId") long candidatoId, ModelMap model) {
        Candidato candidato = candidatoService.recuperarPorEleicaoIdECandidatoId(eleicaoId, candidatoId);
        model.addAttribute("cargos", cargoService.recuperarPorEleicaoId(eleicaoId));
        model.addAttribute("candidato", candidato);
        model.addAttribute("eleicaoId", eleicaoId);
        return new ModelAndView("/candidato/add", model);
    }

    @PutMapping("/salvar")
    public ModelAndView atualizar(@PathVariable("eleicaoId") long eleicaoId, @PathVariable("eleicaoId") long cargoId, @Valid @ModelAttribute("candidato") Candidato candidato, BindingResult result, RedirectAttributes attr, ModelMap model) {
        if (result.hasErrors()) {
            model.addAttribute("cargos", cargoService.recuperarPorEleicaoId(eleicaoId));
            return new ModelAndView("/candidato/add", model);
        }

        candidatoService.atualizar(candidato, eleicaoId);
        attr.addFlashAttribute("mensagem", "Candidato atualizado com sucesso.");
        return new ModelAndView("redirect:/eleicao/" + eleicaoId + "/candidatos/listar");
    }

    @GetMapping("/{candidatoId}/remover")
    public String remover(@PathVariable("eleicaoId") long eleicaoId, @PathVariable("candidatoId") long candidatoId, RedirectAttributes attr) {
        candidatoService.excluir(eleicaoId, candidatoId);
        attr.addFlashAttribute("mensagem", "Candidato excluído com sucesso.");
        return "redirect:/eleicao/" + eleicaoId + "/candidatos/listar";
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.project.eleicao.controller;

import br.project.eleicao.dao.CandidatoDAO;
import br.project.eleicao.domain.Candidato;
import br.project.eleicao.domain.Votacao;
import br.project.eleicao.generico.CandidatoConverter;
import br.project.eleicao.service.CandidatoService;
import br.project.eleicao.service.CargoService;
import br.project.eleicao.service.EleicaoService;
import br.project.eleicao.service.EleitorService;
import br.project.eleicao.service.VotacaoService;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Allan Christiant
 *
 * @Controller indica que a classe se trata realmente de um controller.
 *
 * @RequestMapping("") caso uma requisição seja enviada para localhost:8080/..
 * essa requisição será tratada por um dos métodos implementados nesta classe.
 *
 * @ModelAttribute essa anotação faz o binding, faz a ligação entre o objeto
 * manipulado no formulário de cadastro e o objeto esperado pelo controller.
 *
 * @Valid indica que as regras de negocio na camada domain (dominio) devem ser
 * validadas aqui. O parâmetro BindingResult caso a validação identifique algum
 * erro, esse erro estará presente no parâmetro result.
 *
 * @PostMapping recebe requisições do tipo POST enviadas para ../salvar que
 * serão processadas por esse método.
 *
 * @PutMapping recebe requisições do tipo PUTT enviadas para ../salvar que serão
 * processadas por esse método.
 *
 * @GetMapping. indica qee requisições HTTP do tipo GET enviadas para
 * localhost:8080/../listar, cadastro, atualizar ou remover devem ser atendidas
 * por esse método.
 *
 * Na assinatura dos métodos listar e atualizar é declado como tipo de retorno
 * ModelAndView e recebemos por parâmetro um objeto do tipo ModelMap. Com um
 * parâmetro do tipo ModelMap o Spring nos fornecerá um objeto desse tipo já
 * instanciado. Ele representa a implementação de um map feita pelo Spring para
 * que possamos enviar dados para a view.
 *
 * Para enviar dados para a view é chamado o método addAttribute() e adicionado
 * no map o atributo que sera enviado.
 *
 * Para enviar os dados e indicar a página que a aplicação deve exibir, é criado
 * um objeto do tipo ModelAndView e passamos como parâmetros o model no qual foi
 * adicionado um atributo, e uma string, que indica a página a ser exibida.
 *
 *
 */
@Controller
@RequestMapping("eleicao/{eleicaoId}/eleitor/{eleitorId}/")
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

    @Autowired
    private CandidatoDAO candidatoDAO;

    @InitBinder
    public void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
        binder.registerCustomEditor(Candidato.class, new CandidatoConverter(candidatoDAO));
    }

    /**
     * em listar passamos seis atributos pelo model, candidato, cargo, eleitor e
     * votacao, eleicao e o id da eleicao
     */
    private void list(long eleicaoId, long eleitorId, ModelMap model) {
        model.addAttribute("eleicoes", eleicaoService.recuperar());
        model.addAttribute("candidatos", candidatoService.recuperarPorEleicaoId(eleicaoId));
        model.addAttribute("cargos", cargoService.recuperarPorEleicaoId(eleicaoId));
        model.addAttribute("eleitors", eleitorService.recuperarPorEleicaoId(eleicaoId));
        model.addAttribute("votacaos", votacaoService.recuperarPorEleicaoId(eleicaoId));
        model.addAttribute("eleicaoId", eleicaoId);
        model.addAttribute("eleitorId", eleitorId);

    }

    @GetMapping("/listar")
    public ModelAndView modelAndView(@PathVariable("eleicaoId") long eleicaoId, @PathVariable("eleitorId") long eleitorId, ModelMap model) {
        list(eleicaoId, eleitorId, model);
        return new ModelAndView("/votacao/list", model);
    }

    @GetMapping("cargo/{cargoId}/votar")
    public ModelAndView modelAndViewVotar(@PathVariable("eleicaoId") long eleicaoId, @PathVariable("eleitorId") long eleitorId, @PathVariable("cargoId") long cargoId, ModelMap model) {
        list(eleicaoId, eleitorId, model);
        model.addAttribute("cargos", cargoService.recuperarPorEleicaoIdECargoId(eleicaoId, cargoId));
        model.addAttribute("cargoId", cargoId);
        return new ModelAndView("/votacao/add", model);
    }

    @PostMapping("cargo/{cargoId}/salvar")
    public ModelAndView salvar(@PathVariable("eleicaoId") long eleicaoId, @PathVariable("eleitorId") long eleitorId, @PathVariable("cargoId") long cargoId, @Valid @ModelAttribute("votacao") Votacao votacao, BindingResult result, RedirectAttributes attr, HttpServletRequest request, ModelMap model) {

        votacaoService.salvar(votacao, eleicaoId, eleitorId, protocolo());
        attr.addFlashAttribute("mensagem", "Cargo salva com sucesso.");
        return new ModelAndView("redirect:/eleicao/" + eleicaoId + "/eleitor/" + eleitorId + "/listar");
    }

    private String protocolo() {
        long v1 = numeroProtocolo();
        long v2 = numeroProtocolo();
        long v3 = numeroProtocolo();
        long v4 = numeroProtocolo();

        String protoculo = v1 + "-" + v2 + "-" + v3 + "-" + v4;
        return protoculo;
    }

    private long numeroProtocolo() {
        long valor = (long) (1000 + Math.random() * 9999);
        return valor;
    }
}

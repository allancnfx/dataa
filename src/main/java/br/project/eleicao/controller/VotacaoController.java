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

    /**
     * em listar passamos seis atributos pelo model, candidato, cargo, eleitor e votacao, eleicao e o id da
     * eleicao
     */
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

}

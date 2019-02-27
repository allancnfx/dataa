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
@RequestMapping("eleicao/{eleicaoId}/cargos")
public class CargoController {

    @Autowired
    private CargoService cargoService;

    /**
     * em listar passamos dois atributos pelo model cargo e o id da eleicao
     */
    @GetMapping("/listar")
    public ModelAndView modelAndView(@PathVariable("eleicaoId") long eleicaoId, ModelMap model) {
        model.addAttribute("cargos", cargoService.recuperarPorEleicaoId(eleicaoId));
        model.addAttribute("eleicaoId", eleicaoId);
        return new ModelAndView("/cargo/list", model);
    }

    /**
     * em cadastro passamos apenas passamos o ModelAttribute do objeto e
     * enviamos para view de cadastro.
     */
    @GetMapping("/cadastro")
    public String preSalvar(@ModelAttribute("cargo") Cargo cargo, @PathVariable("eleicaoId") long eleicaoId) {
        return "/cargo/add";
    }

    /**
     * if (result.hasErrors()) verifica se o usuario tentou cadastrar e deixou
     * algum camnpo em branco e retorna o erro.
     *
     * fazemos a chamada do metodo salvar da classe de serviço CargoService e
     * passamos como parametro os dados como objeto atraves do formulario e o id
     * da eleição pela url.
     */
    @PostMapping("/salvar")
    public String salvar(@PathVariable("eleicaoId") long eleicaoId, @Valid @ModelAttribute("cargo") Cargo cargo, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "/cargo/add";
        }

        cargoService.salvar(cargo, eleicaoId);
        attr.addFlashAttribute("mensagem", "Cargo salva com sucesso.");
        return "redirect:/eleicao/" + eleicaoId + "/cargos/listar";
    }

    /**
     * em atualizar de forma igual ao cadastro passamos o atributo cargo e id da
     * eleição pelo model com o obejetivo de recuperar os dados na view de
     * cadastro.
     */
    @GetMapping("/{cargoId}/atualizar")
    public ModelAndView preAtualizar(@PathVariable("eleicaoId") long eleicaoId, @PathVariable("cargoId") long cargoId, ModelMap model) {
        Cargo cargo = cargoService.recuperarPorEleicaoIdECargoId(eleicaoId, cargoId);
        model.addAttribute("cargo", cargo);
        model.addAttribute("eleicaoId", eleicaoId);
        return new ModelAndView("/cargo/add", model);
    }

    /**
     * de forma semelhante ao Post salvar, aqui é chamado o metodo atualizar com
     * o Parametro @PutMapping para informar que será realizar um update.
     */
    @PutMapping("/salvar")
    public String atualizar(@PathVariable("eleicaoId") long eleicaoId, @Valid @ModelAttribute("cargo") Cargo cargo, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "/cargo/add";
        }

        cargoService.atualizar(cargo, eleicaoId);
        attr.addFlashAttribute("mensagem", "Cargo atualizada com sucesso.");
        return "redirect:/eleicao/" + eleicaoId + "/cargos/listar";
    }

    /**
     * semelhante ao outros metodos, aqui é informado o id da eleicao e do
     * cargo e chamamos o metodo excluir passando esse parametros, fazendo assum
     * a exclusão dos dados.
     */
    @GetMapping("/{cargoId}/remover")
    public String remover(@PathVariable("eleicaoId") long eleicaoId, @PathVariable("cargoId") long cargoId, RedirectAttributes attr) {
        cargoService.excluir(eleicaoId, cargoId);
        attr.addFlashAttribute("mensagem", "Cargo excluída com sucesso.");
        return "redirect:/eleicao/" + eleicaoId + "/cargos/listar";
    }

}

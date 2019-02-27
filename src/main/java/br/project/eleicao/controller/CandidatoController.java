package br.project.eleicao.controller;

import br.project.eleicao.dao.CargoDAO;
import br.project.eleicao.generico.CargoConverter;
import br.project.eleicao.domain.Candidato;
import br.project.eleicao.domain.Cargo;
import br.project.eleicao.service.CandidatoService;
import br.project.eleicao.service.CargoService;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("eleicao/{eleicaoId}/candidatos")
public class CandidatoController {

    @Autowired
    private CandidatoService candidatoService;

    @Autowired
    private CargoService cargoService;

    @Autowired
    private CargoDAO cargoDAO;

    /**
     * metodo que recebe o valor String do select e converte para o tipo objeto
     * Cargo através da classe CargoConverter localizada em
     * br.project.eleicao.generico
     */
    @InitBinder
    public void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
        binder.registerCustomEditor(Cargo.class, new CargoConverter(cargoDAO));
    }

    /**
     * em listar passamos três atributos pelo model, candidato, cargo e o id da
     * eleicao
     */
    @GetMapping("/listar")
    public ModelAndView modelAndView(@PathVariable("eleicaoId") long eleicaoId, ModelMap model) throws IOException {
        model.addAttribute("candidatos", candidatoService.recuperarPorEleicaoId(eleicaoId));
        model.addAttribute("cargos", cargoService.recuperarPorEleicaoId(eleicaoId));
        model.addAttribute("eleicaoId", eleicaoId);
        return new ModelAndView("/candidato/list", model);
    }

    /**
     * em cadastro passamos apenas o atributo cargo pelo model com o obejetivo
     * de recuperar os dados na view de cadastro e popula o select em um
     * comobox.
     */
    @GetMapping("/cadastro")
    public ModelAndView preSalvar(@ModelAttribute("candidato") Candidato candidato, @PathVariable("eleicaoId") long eleicaoId, ModelMap model) {
        model.addAttribute("cargos", cargoService.recuperarPorEleicaoId(eleicaoId));
        return new ModelAndView("/candidato/add", model);
    }

    /**
     * if (result.hasErrors()) verifica se o usuario tentou cadastrar e deixou
     * algum camnpo em branco e retorna o erro.
     *
     * fazemos a chamada do metodo salvar da classe de serviço CandidatoService
     * e passamos como parametro os dados como objeto atraves do formulario e o
     * id da eleição pela url.
     */
    @PostMapping("/salvar")
    public ModelAndView salvar(@PathVariable("eleicaoId") long eleicaoId, @Valid @ModelAttribute("candidato") Candidato candidato, BindingResult result, RedirectAttributes attr, ModelMap model) {

        if (result.hasErrors()) {
            model.addAttribute("cargos", cargoService.recuperarPorEleicaoId(eleicaoId));
            return new ModelAndView("/candidato/add", model);
        }

        candidatoService.salvar(candidato, eleicaoId);
        attr.addFlashAttribute("mensagem", "Candidato salva com sucesso.");
        return new ModelAndView("redirect:/eleicao/" + eleicaoId + "/candidatos/listar");

    }

    /**
     * em atualizar de forma igual ao cadastro passamos além do atributo cargo
     * também o atributo candidato e id da eleição pelo model com o obejetivo de
     * recuperar os dados na view de cadastro.
     */
    @GetMapping("/{candidatoId}/atualizar")
    public ModelAndView preAtualizar(@PathVariable("eleicaoId") long eleicaoId, @PathVariable("candidatoId") long candidatoId, ModelMap model) {
        Candidato candidato = candidatoService.recuperarPorEleicaoIdECandidatoId(eleicaoId, candidatoId);
        model.addAttribute("cargos", cargoService.recuperarPorEleicaoId(eleicaoId));
        model.addAttribute("candidato", candidato);
        model.addAttribute("eleicaoId", eleicaoId);
        return new ModelAndView("/candidato/add", model);
    }

    /**
     * de forma semelhante ao Post salvar, aqui é chamado o metodo atualizar com
     * o Parametro @PutMapping para informar que será realizar um update.
     */
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

    /**
     * semelhante ao outros metodos, aqui é informado o id da eleicao e do
     * candidato e chamamos o metodo excluir passando esse parametros, fazendo assum
     * a exclusão dos dados.
     */
    @GetMapping("/{candidatoId}/remover")
    public String remover(@PathVariable("eleicaoId") long eleicaoId, @PathVariable("candidatoId") long candidatoId, RedirectAttributes attr) {
        candidatoService.excluir(eleicaoId, candidatoId);
        attr.addFlashAttribute("mensagem", "Candidato excluído com sucesso.");
        return "redirect:/eleicao/" + eleicaoId + "/candidatos/listar";
    }
}

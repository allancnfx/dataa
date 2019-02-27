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
@RequestMapping("eleicao")
public class EleicaoController {

    @Autowired
    private EleicaoService eleicaoService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }

    /**
     * em listar apenas recuperamos as eleições cadastradas e adicionamos no
     * model para enviar para a view
     */
    @GetMapping("/listar")
    public ModelAndView modelAndView(ModelMap model) {
        model.addAttribute("eleicoes", eleicaoService.recuperar());
        return new ModelAndView("/eleicao/list", model);
    }

    /**
     * em cadastro passamos apenas passamos o ModelAttribute do objeto e
     * enviamos para view de cadastro.
     */
    @GetMapping("/cadastro")
    public String preSalvar(@ModelAttribute("eleicao") Eleicao eleicao) {
        return "/eleicao/add";
    }

    /**
     * if (result.hasErrors()) verifica se o usuario tentou cadastrar e deixou
     * algum camnpo em branco e retorna o erro.
     *
     * fazemos a chamada do metodo salvar da classe de serviço EleicaoService e
     * passamos como parametro os dados como objeto atraves do formulario e o id
     * da eleição pela url.
     */
    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("eleicao") Eleicao eleicao, BindingResult result, RedirectAttributes attr, ModelMap model) {
        if (result.hasErrors()) {
            return "/eleicao/add";
        }
        eleicaoService.salvar(eleicao);
        attr.addFlashAttribute("mensagem", "eleição criada com sucesso.");
        return "redirect:/eleicao/listar";
    }

    /**
     * em atualizar de forma igual ao cadastro passamos atributo eleição pelo
     * model com o obejetivo de recuperar os dados na view de cadastro.
     */
    @GetMapping("/{id}/atualizar")
    public ModelAndView preAtualizar(@PathVariable("id") long id, ModelMap model) {
        Eleicao eleicao = eleicaoService.recuperarPorId(id);
        model.addAttribute("eleicao", eleicao);
        return new ModelAndView("/eleicao/add", model);
    }

    /**
     * de forma semelhante ao Post salvar, aqui é chamado o metodo atualizar com
     * o Parametro @PutMapping para informar que será realizar um update.
     */
    @PutMapping("/salvar")
    public String atualizar(@Valid @ModelAttribute("playlist") Eleicao eleicao, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "/eleicoes/add";
        }

        eleicaoService.atualizar(eleicao);
        attr.addFlashAttribute("mensagem", "Eleicao atualizada com sucesso.");
        return "redirect:/eleicao/listar";
    }

    /**
     * semelhante ao outros metodos, aqui é informado o id da eleicao e chamamos
     * o metodo excluir passando esse parametros, fazendo assum a exclusão dos dados.
     */
    @GetMapping("/{id}/remover")
    public String remover(@PathVariable("id") long id, RedirectAttributes attr) {
        eleicaoService.excluir(id);
        attr.addFlashAttribute("mensagem", "Eleição excluída com sucesso.");
        return "redirect:/eleicao/listar";
    }

}

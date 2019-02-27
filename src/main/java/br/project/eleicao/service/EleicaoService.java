package br.project.eleicao.service;

import br.project.eleicao.domain.Eleicao;
import java.util.List;

/**
 *
 * Classe da camada de serviço que realizara as regras de negocios implementas
 * nas classes dentro do pacote br.project.eleicao.service.impl
 */
public interface EleicaoService {

    void salvar(Eleicao eleicao);

    List<Eleicao> recuperar();

    Eleicao recuperarPorId(long id);

    void atualizar(Eleicao eleicao);

    void excluir(long id);
}

package br.project.eleicao.service;

import br.project.eleicao.domain.Eleitor;
import java.util.List;

/**
 *
 * Classe da camada de serviço que realizara as regras de negocios implementas
 * nas classes dentro do pacote br.project.eleicao.service.impl
 */
public interface EleitorService {

    void salvar(Eleitor eleitor, long eleicaoId);

    List<Eleitor> recuperarPorEleicaoId(long eleicaoId);

    List<Eleitor> recuperarPorEleitorId(long eleitorId);

}

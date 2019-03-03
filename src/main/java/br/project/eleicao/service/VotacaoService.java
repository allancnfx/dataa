package br.project.eleicao.service;

import br.project.eleicao.domain.Votacao;
import java.util.List;

/**
 *
 * Classe da camada de serviço que realizara as regras de negocios implementas
 * nas classes dentro do pacote br.project.eleicao.service.impl
 */
public interface VotacaoService {
    
    void salvar(Votacao Votacao, long eleicaoId, long eleitorId, String protocolo);
    
    List<Votacao> recuperarPorEleicaoId(long eleicaoId);

    int recuperarPorId(long id);
}

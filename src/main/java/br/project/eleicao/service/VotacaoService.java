package br.project.eleicao.service;

import br.project.eleicao.domain.Votacao;
import java.util.List;

public interface VotacaoService {
    void salvar(Votacao votacao, long eleicaoId, long eleitorId);
    List<Votacao> recuperarPorEleicaoId(long eleicaoId);
    int recuperar(long eleicaoId);
}

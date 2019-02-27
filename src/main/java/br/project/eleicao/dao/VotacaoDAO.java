package br.project.eleicao.dao;

import br.project.eleicao.domain.Votacao;
import java.util.List;

public interface VotacaoDAO {

    void salvar(Votacao votacao);
    List<Votacao> recuperarPorEleicaoId(long eleicaoId);
    int recuperar(long eleicaoId);

}

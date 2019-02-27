package br.project.eleicao.dao;

import br.project.eleicao.domain.Eleitor;
import java.util.List;

public interface EleitorDAO {

    void salvar(Eleitor eleitor);
    List<Eleitor> recuperarPorEleicaoId(long eleicaoId);
    List<Eleitor> recuperarPorEleitorId(long eleitorId);

}

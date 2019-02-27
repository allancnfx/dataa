package br.project.eleicao.service;

import br.project.eleicao.domain.Eleitor;
import java.util.List;

public interface EleitorService {

    void salvar(Eleitor eleitor, long eleicaoId);

    List<Eleitor> recuperarPorEleicaoId(long eleicaoId);
    List<Eleitor> recuperarPorEleitorId(long eleitorId);

}

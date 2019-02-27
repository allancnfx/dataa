package br.project.eleicao.service;
import br.project.eleicao.domain.Eleicao;
import java.util.List;

public interface EleicaoService {
    void salvar(Eleicao eleicao);
    List<Eleicao> recuperar();
    Eleicao recuperarPorId(long id);
    void atualizar(Eleicao eleicao);
    void excluir(long id);
}

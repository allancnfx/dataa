package br.project.eleicao.service.impl;

import br.project.eleicao.dao.EleitorDAO;
import br.project.eleicao.domain.Eleitor;
import br.project.eleicao.service.EleicaoService;
import br.project.eleicao.service.EleitorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EleitorServiceImpl implements EleitorService {

    @Autowired
    private EleitorDAO eleitorDAO;

    @Autowired
    private EleicaoService eleicaoService;

    @Override
    public void salvar(Eleitor eleitor, long eleicaoId) {
        eleitor.setEleicao(eleicaoService.recuperarPorId(eleicaoId));
        eleitorDAO.salvar(eleitor);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Eleitor> recuperarPorEleicaoId(long eleicaoId) {
        return eleitorDAO.recuperarPorEleicaoId(eleicaoId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Eleitor> recuperarPorEleitorId(long eleitorId) {
        return eleitorDAO.recuperarPorEleitorId(eleitorId);

    }

}

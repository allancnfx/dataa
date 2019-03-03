package br.project.eleicao.generico;

import br.project.eleicao.dao.CandidatoDAO;
import br.project.eleicao.domain.Candidato;
import java.beans.PropertyEditorSupport;

/**
 *
 * @author Allan
 *
 * Classe que converte o valor String do select combobox da view cadastro
 * candidato em Objeto do tipo Cargo.
 * 
 */
public class CandidatoConverter extends PropertyEditorSupport {

    private final CandidatoDAO candidatoDAO;

    public CandidatoConverter(CandidatoDAO candidatoDAO) {
        this.candidatoDAO = candidatoDAO;
    }

    @Override
    public void setAsText(String id) throws IllegalArgumentException {
        Long candidatoId = new Long(id);
        Candidato candidato = this.candidatoDAO.recuperarPorId(candidatoId);
        this.setValue(candidato);
    }
}

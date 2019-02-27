package br.project.eleicao.generico;

import br.project.eleicao.dao.CargoDAO;
import br.project.eleicao.domain.Cargo;
import java.beans.PropertyEditorSupport;

/**
 *
 * @author Allan
 *
 * Classe que converte o valor String do select combobox da view cadastro
 * candidato em Objeto do tipo Cargo.
 * 
 */
public class CargoConverter extends PropertyEditorSupport {

    private final CargoDAO cargoDAO;

    public CargoConverter(CargoDAO cargoDAO) {
        this.cargoDAO = cargoDAO;
    }

    @Override
    public void setAsText(String id) throws IllegalArgumentException {
        Long cargoId = new Long(id);
        Cargo cargo = this.cargoDAO.recuperarPorID(cargoId);
        this.setValue(cargo);
    }
}


package br.project.eleicao.generico;

import br.project.eleicao.domain.Image;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface ImageRepository extends PagingAndSortingRepository<Image, Long> {
	public Image findByName(String name);
}

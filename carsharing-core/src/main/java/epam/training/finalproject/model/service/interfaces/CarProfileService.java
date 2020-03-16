package epam.training.finalproject.model.service.interfaces;

import epam.training.finalproject.model.dao.specification.SearchCriteria;
import epam.training.finalproject.model.domain.dto.CarProfileDto;

import java.util.List;

public interface CarProfileService extends AbstractEntityService<Long, CarProfileDto> {

    List<CarProfileDto> findCarProfilesByCriteria(List<SearchCriteria> criteria);
}

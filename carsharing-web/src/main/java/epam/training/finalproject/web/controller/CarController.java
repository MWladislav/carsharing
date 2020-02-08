package epam.training.finalproject.web.controller;

import epam.training.finalproject.model.domain.entity.CarProfile;
import epam.training.finalproject.model.service.interfaces.CarProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarProfileService carProfileService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CarProfile getCar(@PathVariable(value = "id") Long id){
        return carProfileService.getById(id);
    }

}

package epam.training.finalproject.controller;


import epam.training.finalproject.model.domain.entity.CarProfile;
import epam.training.finalproject.model.service.interfaces.CarProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

    @Autowired
    private CarProfileService carProfileService;


    @RequestMapping(value = "/homePage", method = RequestMethod.GET)
    public List<CarProfile> getHomePage() {
        return carProfileService.getAll();
    }

}

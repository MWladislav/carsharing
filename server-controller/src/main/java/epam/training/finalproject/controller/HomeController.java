package epam.training.finalproject.controller;


import epam.training.finalproject.model.domain.entity.Car;
import epam.training.finalproject.model.service.interfaces.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

    @Autowired
    private CarService carService;


    @RequestMapping(value = "/homePage", method = RequestMethod.GET)
    public List<Car> getHomePage() {
        return carService.getAll();
    }

}

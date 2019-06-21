package epam.training.finalproject.controller;

import epam.training.finalproject.model.domain.entity.Car;
import epam.training.finalproject.model.service.interfaces.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CarController {


    @Autowired
    private CarService carService;

    @RequestMapping(value = "/car/{id}", method = RequestMethod.GET)
    public String getCar(@PathVariable(value = "id") Long id, Model model){
        Car car=carService.getById(id);
        model.addAttribute("car",car);
        return "carPage";
    }

}

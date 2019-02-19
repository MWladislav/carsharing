package epam.finalProject.carSharing.controller;


import epam.finalProject.carSharing.model.domain.entity.Car;
import epam.finalProject.carSharing.model.service.interfaces.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private CarService carService;


    @RequestMapping(value = "/homePage", method = RequestMethod.GET)
    public String getHomePage(Model model) {

        List<Car> cars=carService.getAll();
        model.addAttribute("cars",cars);
        return "homePage";
    }

}

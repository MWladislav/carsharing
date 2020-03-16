package epam.training.finalproject.web.controller;

import epam.training.finalproject.model.dao.interfaces.CarProfileRepository;
import epam.training.finalproject.model.dao.specification.SearchCriteria;
import epam.training.finalproject.model.domain.dto.CarProfileDto;
import epam.training.finalproject.model.service.interfaces.CarProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarProfileController {

    @Autowired
    private CarProfileService carProfileService;
    @Autowired
    private CarProfileRepository repository;

    @GetMapping(value = "/searchCars")
    public List<CarProfileDto> searchCars(@RequestBody List<SearchCriteria> params) {
        return carProfileService.findCarProfilesByCriteria(params);
    }

//    @GetMapping(value = "/profile")
//    public CarProfile getCar(@RequestParam(value = "id") Long id) {
//        return carProfileService.getById(id);
//    }
//
//    @GetMapping("/all")
//    public ResponseEntity<List<CarProfile>> getAllOrders() {
//        return ResponseEntity.ok(carProfileService.getAll());
//    }

//    @GetMapping(value = "/searchCars")
//    public List<CarProfile> findByManufacturer(@RequestParam(value = "manufacturer") String manufacturer){
//        return carProfileService.findByManufacturer(manufacturer);
//    }
//
//    @GetMapping(value = "/searchCars")
//    public List<CarProfile> findByModel(@RequestParam(value = "model") String model){
//        return carProfileService.findByModel(model);
//    }
//
//    @GetMapping(value = "/searchCars")
//    public List<CarProfile> findByBodyType(@RequestParam(value = "carBodyType") CarBodyType carBodyType){
//        return carProfileService.findByBodyType(carBodyType);
//    }
//
//    @GetMapping(value = "/searchCars")
//    public List<CarProfile> findByEngineType(@RequestParam(value = "engineType") CarEngineType engineType){
//        return carProfileService.findByEngineType(engineType);
//    }
//
//    @GetMapping(value = "/searchCars")
//    public List<CarProfile> findByYearOfIssue(@RequestParam(value = "yearOfIssue") int yearOfIssue){
//        return carProfileService.findByYearOfIssue(yearOfIssue);
//    }

//    @PostMapping("/update")
//    public ResponseEntity<Long> updateCarProfile(@RequestBody CarProfile profile) {
//        return ResponseEntity.ok(carProfileService.update(profile));
//    }
//
//    @DeleteMapping("/remove")
//    public ResponseEntity<Long> removeCarProfile(@RequestBody CarProfile profile) {
//        return ResponseEntity.ok(carProfileService.delete(profile));
//    }
//
//    @PutMapping("/save")
//    public ResponseEntity<Long> saveCarProfile(@RequestBody CarProfile profile) {
//        return ResponseEntity.ok(carProfileService.save(profile));
//    }
}

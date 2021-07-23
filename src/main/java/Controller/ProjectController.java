package Controller;


import Entity.LocationEntity;
import Service.ProjectService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RestController
@AllArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping("/alllocations")
    public List<LocationEntity> findAll() {
        return projectService.findAll();
    }

    @GetMapping("/findlocation")
    public Optional<LocationEntity> findById(@RequestParam("locationId") int id) throws NotFoundException {
        return projectService.findById(id);
    }

    @GetMapping("/locationsindistt")
    public List<LocationEntity> findAllLocationsWithinDistrict(@RequestParam("districtId") int id){
        return projectService.findAllLocationsWithinDistrict(id);
    }

    @GetMapping("/nearbylocations")
    public List<LocationEntity> findAllNearbyLocations(@RequestParam("userlocation") List<Double> userLocation){
        return projectService.findAllNearbyLocations(userLocation.get(0)/*longitude*/, userLocation.get(1)/*latitude*/);
    }

    @GetMapping("/deletelocation")
    public void deleteById(@RequestParam("locationId") int locationId){
        projectService.deleteById(locationId);
    }

    @GetMapping("/savelocation")
    public void saveLocation(
            @RequestParam("name") String Name,
            @RequestParam("geom") Point Geom
            ) {
        projectService.saveLocation(Name, Geom);
    }
}

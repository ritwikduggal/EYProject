package Service;

import Repository.ProjectRepository;
import Entity.LocationEntity;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<LocationEntity> findAll(){
        return projectRepository.findAll();
    }

    public Optional<LocationEntity> findById(int id) {
        return projectRepository.findById(id);
    }

    public List<LocationEntity> findAllLocationsWithinDistrict(int districtId){
        return projectRepository.findAllLocationsWithinDistrict(districtId);
    }

    public List<LocationEntity> findAllNearbyLocations(Double userLongitude, Double userLatitude){
        return projectRepository.findAllNearbyLocations(userLongitude, userLatitude);
    }

    public void deleteById(int id){
        projectRepository.deleteById(id);
    }

    public void saveLocation(String Name, Point Geom){
        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setName(Name);
        locationEntity.setGeom(Geom);
        projectRepository.save(locationEntity);
    }
}

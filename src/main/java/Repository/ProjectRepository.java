package Repository;

import Entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<LocationEntity, Integer> {
    @Query(value = "SELECT nhf.id, nhf.name, nhf.geom "
            + "FROM nairobi_Health_facilities nhf, nairobi_sub_counties nsc "
            + "WHERE ST_Within(nhf.geom, nsc.geom) AND nsc.id= :subCountyId"
            , nativeQuery = true)
    List<LocationEntity> findAllLocationsWithinDistrict(@Param("districtId")  int subCountyId);

    @Query(value = "SELECT nhf.id, nhf.name, nhf.geom, ST_Distance(nhf.geom,ST_SetSRID(ST_Point(:userLongitude,:userLatitude),4326)) AS distance "
            + "FROM nairobi_Health_facilities nhf "
            + "ORDER BY nhf.geom  <-> ST_SetSRID(ST_Point(:userLongitude,:userLatitude),4326) "
            + "LIMIT 20"
            , nativeQuery = true)
    List<LocationEntity> findAllNearbyLocations(@Param("userLongitude") Double userLongitude,@Param("userLatitude")  Double userLatitude);
}

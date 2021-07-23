package Entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.geo.Point;
//import org.locationtech.jts.geom.Point;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "locations")
public class LocationEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "name")
    private String name;

    /*@Column(name = "district")
    private String district;

    @Column(name = "location_type")
    private String locationType;*/

    @Column(name = "geom")
    private Point geom;
}

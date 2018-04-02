package spring.mvc.housing.association.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.mvc.housing.association.model.Occupant;

public interface OccupantRepository extends JpaRepository<Occupant, Integer> {
}

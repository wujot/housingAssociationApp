package spring.mvc.housing.association.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.mvc.housing.association.model.Flat;

public interface FlatRepository extends JpaRepository<Flat, Integer> {
}

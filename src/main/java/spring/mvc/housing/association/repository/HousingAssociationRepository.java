package spring.mvc.housing.association.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.mvc.housing.association.model.HousingAssociation;

public interface HousingAssociationRepository extends JpaRepository<HousingAssociation, Integer> {
}

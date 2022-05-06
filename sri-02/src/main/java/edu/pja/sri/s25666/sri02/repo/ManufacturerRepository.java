package edu.pja.sri.s25666.sri02.repo;

import edu.pja.sri.s25666.sri02.model.Manufacturer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ManufacturerRepository extends CrudRepository<Manufacturer, Long> {
    List<Manufacturer> findAll();

    @Override
    Optional<Manufacturer> findById(Long Id);

/*    @Query("from Manufacturer as m left join fetch m.medicines where m.id=:manufacturerId")
    Optional<Manufacturer> getManufacturerDetailsById(@Param("manufacturerId") long manufacturerId);*/

}

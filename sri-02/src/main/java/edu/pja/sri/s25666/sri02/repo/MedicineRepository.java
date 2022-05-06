package edu.pja.sri.s25666.sri02.repo;

import edu.pja.sri.s25666.sri02.model.Medicine;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface MedicineRepository extends CrudRepository<Medicine, Long> {
    List<Medicine> findAll();

    @Query("SELECT ma.medicines FROM Manufacturer AS ma WHERE ma.id =: manufacturerId")
    List<Medicine> findProductsByManufacturerId(@PathVariable Long manufacturerId);
}

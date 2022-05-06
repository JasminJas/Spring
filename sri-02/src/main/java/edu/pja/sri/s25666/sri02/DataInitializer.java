package edu.pja.sri.s25666.sri02;

import edu.pja.sri.s25666.sri02.model.Manufacturer;
import edu.pja.sri.s25666.sri02.model.Medicine;
import edu.pja.sri.s25666.sri02.repo.ManufacturerRepository;
import edu.pja.sri.s25666.sri02.repo.MedicineRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

@Component
public class DataInitializer implements ApplicationRunner {

    private MedicineRepository  medicineRepository;
    private ManufacturerRepository manufacturerRepository;

    public DataInitializer(MedicineRepository medicineRepository, ManufacturerRepository manufacturerRepository){
        this.medicineRepository = medicineRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Medicine m1 = Medicine.builder()
                .name("Apap")
                .api("Paracetamol")
                .dose(200.0)
                .isWithoutPrescription(true)
                .expirationDate(LocalDate.parse("2025-05-01"))
                .build();

        Medicine m2 = Medicine.builder()
                .name("Ibuprom")
                .api("Ibuprofen")
                .dose(200.0)
                .isWithoutPrescription(true)
                .expirationDate(LocalDate.parse("2025-07-01"))
                .build();

        Medicine m3 = Medicine.builder()
                .name("Diclofenac GSK")
                .api("Diclofenac")
                .dose(20.0)
                .isWithoutPrescription(false)
                .expirationDate(LocalDate.parse("2025-08-11"))
                .build();

        Manufacturer ma1 = Manufacturer.builder()
                .name("USP Zdrowie")
                .medicines(new HashSet<>())
                .build();

        Manufacturer ma2 = Manufacturer.builder()
                .name("Novartis")
                .medicines(new HashSet<>())
                .build();

        m1.setManufacturer(ma1);
        ma1.getMedicines().add(m1);

        m2.setManufacturer(ma1);
        ma1.getMedicines().add(m2);

        m3.setManufacturer(ma2);
        ma2.getMedicines().add(m3);

        manufacturerRepository.saveAll(Arrays.asList(ma1, ma2));
        medicineRepository.saveAll(Arrays.asList(m1,m2,m3));

    }
}

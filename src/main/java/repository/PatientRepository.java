package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long>{

}

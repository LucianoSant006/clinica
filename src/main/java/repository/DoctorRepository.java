package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}

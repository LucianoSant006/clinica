package repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import entity.Consult;
import entity.Doctor;

public interface ConsultRepository extends JpaRepository<Consult, Long> {
	
	List<Consult> findByDateAndDoctor(LocalDate date, Doctor doctor);
	
}

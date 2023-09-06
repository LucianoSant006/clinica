package service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.Consult;
import entity.Doctor;
import repository.ConsultRepository;
import resources.InvalidConsultException;

@Service
public class ConsultService {
	
	@Autowired
	private ConsultRepository consultRepository;
	
	private void validateConsult(Consult consult) {
		
		LocalTime consultTime = consult.getTime();
		if(consultTime.isBefore(LocalTime.of(9, 0)) || consultTime.isAfter(LocalTime.of(17,0))) {
			throw new InvalidConsultException("A consulta deve estar entre as 09:00 às 17:00");
		}
		
		if(!consult.getTime().plusMinutes(30).isBefore(LocalTime.of(17, 0))) {
			throw new InvalidConsultException("O intervalo entre consultas dever ser de 30 minutos");
		}
	}
	
	private void verifyAvailability(Consult consult) {
		LocalDate consultDate = consult.getDate();
		LocalTime consultTime = consult.getTime();
		Doctor doctor = consult.getDoctor();
		
		List<Consult> consultsInSameDay = consultRepository.findByDateAndDoctor(consultDate, doctor);
		for(Consult existingConsult : consultsInSameDay) {
			LocalTime existingTime = existingConsult.getTime();
			if(Math.abs(ChronoUnit.MINUTES.between(existingTime, consultTime)) < 30) {
				throw new InvalidConsultException("O médico ja possui consulta marcada neste horário");
			}
		}
	}
	
	public Consult scheduleAppointment(Consult consult) {
		
		validateConsult(consult);
		
		verifyAvailability(consult);
		
		return consultRepository.save(consult);
		
	}
	
	public List<Consult> consultList(){
		return consultRepository.findAll();
	}
	
	
	
}

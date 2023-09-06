package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.Patient;
import jakarta.persistence.EntityNotFoundException;
import repository.PatientRepository;

@Service
public class ServicePatient {
	
	@Autowired
	private PatientRepository patientRepository;
	
	public Patient createPatient(Patient patient) {
	
		return patientRepository.save(patient);
	}
	
	public void deletePatient(Long idPatient) {
		
		patientRepository.deleteById(idPatient);
	}
	
	public List<Patient> listPatient(){
		
		return patientRepository.findAll();	
	}
	
	public Patient findPatientById(Long idPatient) {
		
		return patientRepository.findById(idPatient)
				.orElseThrow(()-> new EntityNotFoundException("Paciente não encontrado"));
	}
	
	public Patient updatePatient(Long idPatient, Patient updatedPatient) {
		
		Patient patient = findPatientById(idPatient);
		patient.setName(updatedPatient.getName());
		patient.setBirthday(updatedPatient.getBirthday());
		patient.setInsurance(updatedPatient.getInsurance());
		patient.setCpf(updatedPatient.getCpf());
		return patientRepository.save(patient);
		
	}
	
	
}

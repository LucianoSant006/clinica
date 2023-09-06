package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import entity.Consult;
import resources.InvalidConsultException;
import service.ConsultService;
import service.ServicePatient;

@RestController
@RequestMapping("/consultas")
public class ConsultController {
	
	
	@Autowired
	private ConsultService consultService;
	
	@PostMapping("/schedule")
	public ResponseEntity<?> 
	scheduleAppointment(@RequestBody Consult consult){
		try {
			Consult scheduleAppointment = 
					consultService.scheduleAppointment(consult);
			return ResponseEntity.ok(scheduleAppointment);
		} catch (InvalidConsultException e) {
			return ResponseEntity.badRequest().body(e.getMessage());		
		}
	}
	
	@GetMapping("/list")
	public List<Consult> consultList(){
		return consultService.consultList();
	}

}

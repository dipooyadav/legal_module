package com.nscs.SBMaster;

import com.nscs.SBMaster.model.Employee;
import com.nscs.SBMaster.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SbMasterApplication  {

	public static void main(String[] args) {
		SpringApplication.run(SbMasterApplication.class, args);
	}

	/*
	@Autowired
	private EmployeeRepository emprep ;
	@Override
	public void run(String... args) throws Exception {

		Employee emp = new Employee();
		emp.setFirstName("Vinay") ;
		emp.setLastName("Dwivedi") ;
		emp.setEmailID("vinay@nscspl.in") ;
		emprep.save(emp) ;

		Employee emp1 = new Employee();
		emp1.setFirstName("Reetik") ;
		emp1.setLastName("Sharma") ;
		emp1.setEmailID ("reetik@nscspl.in") ;
		emprep.save(emp1) ;

	}
	*/

}

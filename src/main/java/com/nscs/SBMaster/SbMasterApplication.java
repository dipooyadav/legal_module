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


}

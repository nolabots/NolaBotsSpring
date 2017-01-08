package org.usfirst.frc.team5953.services;

import org.usfirst.frc.team5953.domain.Data;
import org.usfirst.frc.team5953.ras.IDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Contains logic to manipulate controller requests into calls to the database and data from the database into a desirable form
 * @author matthewwolff
 *
 */
@Service
public class DataService implements IDataService {

	@Autowired
	private IDataRepository dataRepo;
	
	public DataService(){
		
	}
	
	public List <Data> getAllData(){
		return dataRepo.findAll();
	}
	

	
}



package org.usfirst.frc.team5953.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import org.usfirst.frc.team5953.domain.Data;
import org.usfirst.frc.team5953.services.DataService;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Handles the REST endpoints for data delivery
 * @author scottrobertson
 *
 */
@RestController
@RequestMapping(value = "/data")
class DataController {
	

	@Autowired
	private DataService dataService;
	
	@RequestMapping(value = "/getData", method = RequestMethod.GET)
	public @ResponseBody List<Data> getData() {
       return dataService.getAllData();
   }

}

package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Exceptions.CarWashException;
import com.example.demo.entity.CustCarDetails;
import com.example.demo.entity.CustomerDetails;
import com.example.demo.service.CustomerService;

@RestController
@RequestMapping(value = "/customer")
@CrossOrigin(origins = "http://localhost:4200")
public class Customer {

	@Autowired
	private CustomerService custService;

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public CustomerDetails checkUser(@RequestBody CustomerDetails customer) throws CarWashException {

		CustomerDetails result;
		try {
			System.out.println(customer.getEmail());
			 result=custService.login(customer);
			 
			 
		} catch (Exception e) {
			System.out.println("errrrrrr");
			throw new CarWashException("Please enter correct username and password");
		}
		System.out.println("above res");
		return result;
	}

	@RequestMapping(value = "/details", method = RequestMethod.POST, consumes = "application/json", produces = "text/plain")
	public String addDetails(@RequestBody CustCarDetails details) throws CarWashException {

		try {

			custService.addDetails(details);
		} catch (Exception e) {
			throw new CarWashException("please try again");
		}
		return "Added Successfully";

	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json", produces = "text/plain")
	public String addUser(@RequestBody CustomerDetails customer) throws CarWashException {

		try {
			System.out.println("registered");
			if (custService.register(customer) != null);
		} catch (Exception e) {
			throw new CarWashException("User Already Exists");
		}
		return " Registered Successfully";
	}
	
	@RequestMapping(value = "/view", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public List<CustCarDetails> viewDetails(@RequestBody CustCarDetails details)throws CarWashException{
		
		List<CustCarDetails> result=custService.viewDetails(details);
		System.out.println("qwerty");
			if(result==null) {
				throw new CarWashException("No Records found");
			}
		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, consumes = "application/json", produces = "text/plain")
	public String deleteDetails(@RequestBody CustCarDetails details)throws CarWashException{
		try {
			custService.deleteService(details);
		}catch(Exception e) {
		 	throw new CarWashException("Somethig Went Wrong please try Again");
			}
		return "Deleted Successfully";
	}
}

package com.example.demo.service;

import java.util.List;

import com.example.demo.Exceptions.CarWashException;
import com.example.demo.entity.CustCarDetails;
import com.example.demo.entity.CustomerDetails;

public interface CustomerService {

	CustomerDetails login(CustomerDetails customer) throws CarWashException;

	boolean addDetails(CustCarDetails details) throws CarWashException;

	CustomerDetails register(CustomerDetails customer) throws CarWashException;
	
	List<CustCarDetails> viewDetails(CustCarDetails details) throws CarWashException;

	public boolean deleteService(CustCarDetails details) throws CarWashException;
}

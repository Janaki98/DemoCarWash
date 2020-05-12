package com.example.demo.dao;

import java.util.List;

import com.example.demo.Exceptions.CarWashException;
import com.example.demo.entity.CustCarDetails;
import com.example.demo.entity.CustomerDetails;

public interface CustomerDao {
	
	public CustomerDetails findUser(CustomerDetails customer) throws CarWashException;

	public boolean addDetails(CustCarDetails details) throws CarWashException;

	public CustomerDetails addUser(CustomerDetails customer) throws CarWashException;
	
	public List<CustCarDetails> viewDetails(CustCarDetails details) throws CarWashException;
	
	public boolean deleteDetail(CustCarDetails details) throws CarWashException;
}

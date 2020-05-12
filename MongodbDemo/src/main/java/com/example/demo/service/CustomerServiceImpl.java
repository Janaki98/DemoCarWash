package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exceptions.CarWashException;
import com.example.demo.dao.CustomerDao;
import com.example.demo.entity.CustCarDetails;
import com.example.demo.entity.CustomerDetails;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerDao custDao;

	public void setLoginDao(CustomerDao custDao) {
		this.custDao = custDao;
	}

	@Override
	public CustomerDetails login(CustomerDetails custDetails) throws CarWashException {

		System.out.println(custDetails.getPassword());
		CustomerDetails result = custDao.findUser(custDetails);
		if (result==null) {
			throw new CarWashException("error");
		}
		return result;

	}

	@Override
	public boolean addDetails(CustCarDetails details) throws CarWashException {
		boolean result;
			result =  custDao.addDetails(details);
			if(result==false) {
				throw new CarWashException("Fill All Details");
			}
		
		return result;
	}

	@Override
	public CustomerDetails register(CustomerDetails customer) throws CarWashException {
		System.out.println(customer.getPassword());
		
		CustomerDetails addUser = custDao.addUser(customer);
	
		return addUser;
	}

	@Override
	public List<CustCarDetails> viewDetails(CustCarDetails details) throws CarWashException {
		
		List<CustCarDetails> viewDetails= custDao.viewDetails(details);
		
		return viewDetails;
	}

	@Override
	public boolean deleteService(CustCarDetails details) throws CarWashException {
		
		boolean result=custDao.deleteDetail(details);
		if(result==false) {
			throw new CarWashException("error");
		}
		return result;
	}

}

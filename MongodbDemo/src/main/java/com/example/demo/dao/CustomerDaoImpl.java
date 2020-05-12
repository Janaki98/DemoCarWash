package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Exceptions.CarWashException;
import com.example.demo.entity.CustCarDetails;
import com.example.demo.entity.CustomerDetails;
import com.mongodb.client.result.DeleteResult;

@Repository
public class CustomerDaoImpl implements CustomerDao {
	@Autowired
	private MongoTemplate mongoTemplate;


	@Override
	public CustomerDetails findUser(CustomerDetails user) throws CarWashException {

		Query query = new Query();
		Query query1= new Query();
		String email = user.getEmail();
		String pwd = user.getPassword();
		try {

			query = query
					.addCriteria(Criteria.where("email").is(email)
							.andOperator(Criteria.where("password").is(pwd)));
			query1=query1.addCriteria(Criteria.where("email").is(email));
			boolean dataExists = mongoTemplate.exists(query, "customerDetails");
			System.out.println(dataExists);
			if (dataExists) {
				return mongoTemplate.findOne(query1, CustomerDetails.class);
			}
		} catch (Exception e) {
			throw new CarWashException("reposit error");
		}
		return null;

	}


	@Override
	public CustomerDetails addUser(CustomerDetails customer) throws CarWashException {

		Query query = new Query();
		String email = customer.getEmail();
//		String pwd = customer.getPassword();S
		CustomerDetails addedUser = null;
		try {

			query = query.addCriteria(Criteria.where("email").is(email));
			boolean dataExists = mongoTemplate.exists(query, "customerDetails");
			System.out.println(dataExists);

			if (dataExists!=true) {
				System.out.println("rtrtrt");
				System.out.println(customer.getName());
				addedUser = mongoTemplate.insert(customer, "customerDetails");
			} else {
				throw new CarWashException("User Already Exists");
			}
		} catch (Exception e) {
			throw new CarWashException("reposit error");
		}
		return addedUser;
	}

@Override
public boolean addDetails(CustCarDetails details) throws CarWashException {
	
	Query query= new Query();
	String email= details.getEmail();
	System.out.println(email);
	
	query.addCriteria(Criteria.where("userName").is(email));
	try {
		if(details.getCarName()!=null&&details.getPackAge()!=null&&
				details.getDate()!=null&&details.getPaymentDetails()!=null&&details.getTime()!=null) 
			{
			List<CustCarDetails> list=mongoTemplate.find(query, CustCarDetails.class);
			int count=list.size();
			System.out.println(count);
			if(count==0) {
				System.out.println("count entered");
			CustCarDetails dataExists = mongoTemplate.insert(details, "custCarDetails");
					if (dataExists != null) {
					return true;
				}
			}
			}}
		
	 catch (Exception e) {
		 System.out.println("error entered");
		throw new CarWashException("error occurred");
	}
	return false;
}


@Override
public List<CustCarDetails> viewDetails(CustCarDetails details) throws CarWashException {
	
	Query query = new Query();
	String email=details.getEmail();
	System.out.println(email+" email");
	query = query.addCriteria(Criteria.where("email").is(email));
	boolean dataExists = mongoTemplate.exists(query, "custCarDetails");
	System.out.println(dataExists);
	try{
		if (dataExists) {
			List<CustCarDetails> list=mongoTemplate.find(query, CustCarDetails.class);
			return list;
	}
} catch (Exception e) {
	throw new CarWashException("reposit error");
}
	
	return null;
}


@Override
public boolean deleteDetail(CustCarDetails details) throws CarWashException {
	
	Query query=new Query();
	String id=details.get_id();
	query.addCriteria(Criteria.where("_id").is(id));
	boolean result=mongoTemplate.exists(query,CustCarDetails.class);
	if(result) {
		 DeleteResult delResult=mongoTemplate.remove(query,CustCarDetails.class);
		 if(delResult==null) {
			 throw new CarWashException("Something Went Wrong");
		 }
	}
	return true;
}
}

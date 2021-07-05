package com.yzj.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.yzj.domain.BaseDict;
import com.yzj.domain.Customer;

public interface CustomerService {

	List<Customer> findAll(DetachedCriteria criteria);

	void save(Customer customer);


}

package com.yzj.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.yzj.domain.BaseDict;
import com.yzj.domain.Customer;
import com.yzj.web.commons.Page;

public interface CustomerService {
	/**
	 * 查询所有的客户信息
	 * @param criteria
	 * @return
	 */
	/**
	 * 分页查询客户信息
	 * @param criteria
	 * @param num
	 * @return page封装好的类
	 */
	Page findAll(DetachedCriteria criteria,Integer num);

	/**
	 * 新增客户
	 * 
	 * @param customer
	 */
	void save(Customer customer);

	/**
	 * 查询所有的客户来源
	 * 
	 * @return
	 */
	List<BaseDict> findAllCustomerSource();
	/**
	 * 查询所有的客户级别
	 * 
	 * @return
	 */
	List<BaseDict> findAllCustomerLevel();
	/**
	 * 根据客户id删除客户
	 * 
	 * @param custId
	 */
	void deleteCustomer(Long custId);
	/**
	 * 根据客户id查询客户
	 * 
	 * @param custId
	 * @return
	 */
	Customer findById(Long custId);
	/**
	 * 更新客户信息
	 * 
	 * @param customer
	 */
	void updateCustomer(Customer customer);
/**
 * 投影查询
 * @return
 */
	List<Customer> findAll();

}

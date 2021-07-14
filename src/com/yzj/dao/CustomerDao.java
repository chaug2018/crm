package com.yzj.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.yzj.domain.Customer;

public interface CustomerDao {
	/**
	 * 查询总记录数
	 * @param criteria
	 * @return
	 */
	int FindtotalRecords(DetachedCriteria criteria);

	/**
	 * 客户列表分页查询
	 * 
	 * @param criteria   查询条件
	 * @param startIndex 开始索引
	 * @param pageSize   每页总数
	 * @return
	 */
	List<Customer> findAll(DetachedCriteria criteria, int startIndex, int pageSize);

	void save(Customer customer);

	void deletCustomerById(Long custId);

	Customer findById(Long custId);

	void update(Customer customer);

	List<Customer> findAll();

}

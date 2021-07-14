package com.yzj.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.yzj.dao.CustomerDao;
import com.yzj.domain.Customer;

@Repository("customerDao")
public class CustomerDaoImpl implements CustomerDao {

	@Resource(name = "hibernateTemplate")
	HibernateTemplate hibernateTemplate;

	/**
	 * 总记录数
	 */
	@Override
	public int FindtotalRecords(DetachedCriteria criteria) {
//		hibernateTemplate.findByCriteria(criteria).size();
		criteria.setProjection(Projections.count("custId"));// select count(custId)
		List<Long> list = (List<Long>) hibernateTemplate.findByCriteria(criteria);
//		System.out.println(list.isEmpty()?0:list.get(0).intValue());
		return list.isEmpty() ? 0 : list.get(0).intValue();
	}

	/**
	 * 列表分页查询
	 */
	@Override
	public List<Customer> findAll(DetachedCriteria criteria, int startIndex, int pageSize) {
		// 把之前的设置给清除掉
		criteria.setProjection(null);
		System.out.println(startIndex + "--" + pageSize);
		System.out.println((List<Customer>) hibernateTemplate.findByCriteria(criteria, startIndex, pageSize));
		return (List<Customer>) hibernateTemplate.findByCriteria(criteria, startIndex, pageSize);
	}

	@Override
	public void save(Customer customer) {
		// TODO Auto-generated method stub

		hibernateTemplate.save(customer);
	}

	@Override
	public Customer findById(Long custId) {

		return hibernateTemplate.load(Customer.class, custId);
	}

	@Override
	public void deletCustomerById(Long custId) {
//	Customer customer=findById(custId);
		hibernateTemplate.delete(findById(custId));
	}

	/**
	 * 更新客户信息
	 */
	@Override
	public void update(Customer customer) {
		hibernateTemplate.update(customer);

	}

	/**
	 * 投影查询
	 */

	@Override
	public List<Customer> findAll() {
		// TODO Auto-generated method stub
		return (List<Customer>) hibernateTemplate.find("select new Customer(custId,custName) from Customer");
	}

}

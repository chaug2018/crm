package com.yzj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yzj.dao.BaseDictDao;
import com.yzj.dao.CustomerDao;
import com.yzj.domain.BaseDict;
import com.yzj.domain.Customer;
import com.yzj.service.CustomerService;
import com.yzj.web.commons.Page;

@Service("customerService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class CustomerServiceImpl implements CustomerService {

	@Resource(name = "customerDao")
	CustomerDao customerDao;

	@Resource(name = "baseDictDao")
	BaseDictDao baseDictDao;
	/**
	 * 分页查询客户信息
	 */
	@Override
	public Page findAll(DetachedCriteria criteria, Integer num) {
		
//		1.创建page对象
		
		
//		初始当前页信息
		int currentPageNum=1;
		if (num!=null) {
			currentPageNum=num;
		}
//		2.获取记录总数
		int	totalRecords=customerDao.FindtotalRecords(criteria);
		
		Page page =new Page(currentPageNum,totalRecords);
		
//		3.查询分页的数据
		List<Customer> customers=customerDao.findAll(criteria, currentPageNum,page.getPageSize());
		
		page.setResult(customers);
		
//		返回page对象
		return page;
	
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void save(Customer customer) {
		// TODO Auto-generated method stub
		customerDao.save(customer);
	}

	@Override
	public List<BaseDict> findAllCustomerSource() {
		// TODO Auto-generated method stub
		return baseDictDao.findBaseDictByTypeCode("002");
	}

	@Override
	public List<BaseDict> findAllCustomerLevel() {
		// TODO Auto-generated method stub
		return baseDictDao.findBaseDictByTypeCode("006");
	}
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteCustomer(Long custId) {
		customerDao.deletCustomerById(custId);

	}
	/**
	 * 查询出一个客户
	 */
	@Override
	public Customer findById(Long custId) {

		return customerDao.findById(custId);
	}

	/**
	 * 更新客户信息
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateCustomer(Customer customer) {
		customerDao.update(customer);
	}
/**
 * 投影
 */
	@Override
	public List<Customer> findAll() {
		// TODO Auto-generated method stub
		return customerDao.findAll();
	}



}

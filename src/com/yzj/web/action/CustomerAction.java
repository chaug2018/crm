package com.yzj.web.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.yzj.domain.BaseDict;
import com.yzj.domain.Customer;
import com.yzj.service.CustomerService;

/**
 * 客户的动作类
 * 
 * @author 86186
 *
 */
@Controller("customerAction")
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/customer")
@Results({ @Result(name = "addUI", type = "dispatcher", location = "/jsp/customer/add.jsp"),
		@Result(name = "findAll", type = "dispatcher", location = "/jsp/customer/list.jsp"),
		@Result(name = "listCustomer", type = "redirectAction", location = "findAllCustomer") })
public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {

	private Customer customer = new Customer();
	private List<Customer> customers;

	private List<BaseDict> customerSource;
	private List<BaseDict> customerLevel;

	@Resource(name = "customerService")
	private CustomerService custService;

	@Override
	public Customer getModel() {
		// TODO Auto-generated method stub
		return customer;
	}

	/**
	 * 获取添加客户页面
	 * 
	 * @return
	 */
	@Action("addUICustomer")
	public String addUICustomer() {
//		1.查询所有的客户来源
		customerSource = custService.findAllCustomerSource();
		customerLevel = custService.findAllCustomerLevel();
//		2.查询所有的客户级别

		return "addUI";
	}

	// 添加客户
	@Action("addCustomer")
	public String addCustomer() {
		System.out.println(customer);
		custService.save(customer);
		return "listCustomer";

	}

//	获取客户列表的页面
	@Action("findAllCustomer")
	public String findAllCustomer() {
		DetachedCriteria dCriteria = DetachedCriteria.forClass(Customer.class);
		customers = custService.findAll(dCriteria);
		for (Customer customer : customers) {
			System.out.println(customer);
		}
		return "findAll";
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public List<BaseDict> getCustomerSource() {
		return customerSource;
	}

	public void setCustomerSource(List<BaseDict> customerSource) {
		this.customerSource = customerSource;
	}

	public List<BaseDict> getCustomerLevel() {
		return customerLevel;
	}

	public void setCustomerLevel(List<BaseDict> customerLevel) {
		this.customerLevel = customerLevel;
	}

	public CustomerService getCustService() {
		return custService;
	}

	public void setCustService(CustomerService custService) {
		this.custService = custService;
	}
}

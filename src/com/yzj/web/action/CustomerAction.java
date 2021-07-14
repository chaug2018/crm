package com.yzj.web.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AcceptAction;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.yzj.domain.BaseDict;
import com.yzj.domain.Customer;
import com.yzj.service.CustomerService;
import com.yzj.web.commons.Page;

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
		@Result(name = "listCustomer", type = "redirectAction", location = "findAllCustomer"),
		@Result(name = "editUI", type = "dispatcher", location = "/jsp/customer/edit.jsp"), })
public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {

	private Customer customer = new Customer();
	private List<Customer> customers;

	private List<BaseDict> custSources;
	private List<BaseDict> custLevels;

	@Resource(name = "customerService")
	private CustomerService custService;
	
	private Page page;
	
	private Integer num;//当前页
	

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
		custSources = custService.findAllCustomerSource();
//		2.查询所有的客户级别
		custLevels = custService.findAllCustomerLevel();
		return "addUI";
	}

	// 添加客户
	@Action("addCustomer")
	public String addCustomer() {
		custService.save(customer);
		return "listCustomer";

	}

	/**
	 * deleteCustomer 删除客户
	 * 
	 * @return
	 */
	@Action("deleteCustomer")
	public String deleteCustomer() {
		custService.deleteCustomer(customer.getCustId());
		return "listCustomer";
	}

	/**
	 * 编辑客户
	 */
	@Action("editCustomer")
	public String editCustomer() {
		custService.updateCustomer(customer);
		return "listCustomer";
	}

	/**
	 * 获取编辑页面
	 * 
	 * @return
	 */
	@Action("editUICustomer")
	public String editUICustomer() {
//		1.获取客户来源
		custSources = custService.findAllCustomerSource();
//		2.获取客户级别
		custLevels = custService.findAllCustomerLevel();
//		3.获取客户信息
//		使用模型接受
//		customer = custService.findById(customer.getCustId());
		customer = custService.findById(customer.getCustId());
//		手动压栈
		ActionContext.getContext().getValueStack().push(customer);
		return "editUI";
	}

//	获取客户列表的页面
	@Action("findAllCustomer")
	public String findAllCustomer() {
		DetachedCriteria dCriteria = DetachedCriteria.forClass(Customer.class);

//		3.判断客户名称是否输入
		if(StringUtils.isNoneBlank(customer.getCustName())) {
//			执行模糊查询
			dCriteria.add(Restrictions.ilike("custName","%"+customer.getCustName()+"%"));
		}
//		4.判断所属行业是否输入
		if(StringUtils.isNoneBlank(customer.getCustIndustry())) {
//			对所属行业进行 模糊查询
			dCriteria.add(Restrictions.ilike("custIndustry","%"+customer.getCustIndustry()+"%"));
		}
//		5判断客户来源是否为空
		if(customer.getCustSource()!=null&&customer.getCustSource().getDictId()!=null) {
//			精确客户来源的id
			dCriteria.add(Restrictions.eq("custSource.dictId",customer.getCustSource().getDictId()));
		}
//		6判断客户级别
		if (customer.getCustLevel()!=null&&customer.getCustLevel().getDictId()!=null) {
//			精确客户级别的id
			dCriteria.add(Restrictions.eq("custLevel.dictId",customer.getCustLevel().getDictId()));
		}
		System.out.println("-------");
//		7.执行查询语句
		System.out.println(num);
		page = custService.findAll(dCriteria, num);
//		1.获取客户来源
		custSources = custService.findAllCustomerSource();
//		2.获取客户级别
		custLevels = custService.findAllCustomerLevel();
		return "findAll";
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public List<BaseDict> getCustSources() {
		return custSources;
	}

	public void setCustSources(List<BaseDict> custSources) {
		this.custSources = custSources;
	}

	public List<BaseDict> getCustLevels() {
		return custLevels;
	}

	public void setCustLevels(List<BaseDict> custLevels) {
		this.custLevels = custLevels;
	}

	public CustomerService getCustService() {
		return custService;
	}

	public void setCustService(CustomerService custService) {
		this.custService = custService;
	}
	
	

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	@Override
	public String toString() {
		return "CustomerAction [customer=" + customer + ", customers=" + customers + ", custSources=" + custSources
				+ ", custLevels=" + custLevels + ", custService=" + custService + "]";
	}

}

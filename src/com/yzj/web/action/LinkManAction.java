package com.yzj.web.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.yzj.domain.Customer;
import com.yzj.domain.LinkMan;
import com.yzj.service.CustomerService;
import com.yzj.service.LinkManService;

/**
 * @Scope("prototype")
 * 表示每次接收一个请求创建一个对象
 * @author 86186
 */

/**
 * ActionSupport 在实现了Action接口的基础上还定义了一个validate()方法, 默认的处理用户请求的方法等
 * 
 * @author 86186
 *
 */
//实现模型
@Controller("linkManAction")
@ParentPackage("struts-default")
@Scope("prototype")
@Namespace("/linkman")
@Results({ @Result(name = "addUI", type = "dispatcher", location = "/jsp/linkman/add.jsp"),
		@Result(name = "listUI", type = "dispatcher", location = "/jsp/linkman/list.jsp"), 
		@Result(name = "listLinkMan", type = "redirectAction", location = "listUILinkMan"), 

})
public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {

	private LinkMan linkman = new LinkMan();
	private List<LinkMan> linkmans;
	private List<Customer> customers;

	@Resource(name = "customerService")
	CustomerService customerService;

	@Override
	public LinkMan getModel() {
		// TODO Auto-generated method stub
		return linkman;
	}

	@Resource(name = "linkManService")
	LinkManService linkManService;

	/**
	 * 获取新增联系人页面
	 * 
	 * @return
	 */
	@Action("addUILinkMan")
	public String addUILinkMan() {

		DetachedCriteria dCriteria = DetachedCriteria.forClass(Customer.class);
		System.out.println("-----");
		// 1.查询所有的客户
		customers = customerService.findAll(dCriteria);
		return "addUI";
	}

	/**
	 * 新增联系人
	 */
	@Action("addLinkMan")
	public String addLinkMan() {
		
		linkManService.addLinkMan(linkman);
		return "listLinkMan";
	}
	/**e
	 * 查询所有的联系人
	 * 
	 * @return
	 */
	@Action("listUILinkMan")
	public String listUILinkMan() {
		DetachedCriteria dCriteria=DetachedCriteria.forClass(LinkMan.class);
		linkmans=linkManService.findAllLinkMan(dCriteria);
		System.out.println(linkmans);
		return "listUI";

	}

	public LinkMan getLinkman() {
		return linkman;
	}

	public void setLinkman(LinkMan linkman) {
		this.linkman = linkman;
	}

	public List<LinkMan> getLinkmans() {
		return linkmans;
	}

	public void setLinkmans(List<LinkMan> linkmans) {
		this.linkmans = linkmans;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

}

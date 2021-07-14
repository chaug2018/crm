package com.yzj.web.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
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
		@Result(name = "editUI", type = "dispatcher", location = "/jsp/linkman/edit.jsp"),

})
public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {

	private LinkMan linkman = new LinkMan();
	private List<LinkMan> linkmans;
	private List<Customer> customers;

	@Resource(name = "customerService")
	CustomerService customerService;

	@Override
	public LinkMan getModel() {

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
//		System.out.println("-----");
		// 1.查询所有的客户的id和姓名
		customers = customerService.findAll();
		return "addUI";
	}

	/**
	 * 新增联系人
	 */
	@Action("addLinkMan")
	public String addLinkMan() {

		linkManService.addLinkMan(linkman);
		System.out.println(linkman);
		return "listLinkMan";
	}

	/**
	 * e 查询的联系人及筛选
	 * 
	 * @return
	 */
	@Action("listUILinkMan")
	public String listUILinkMan() {
		DetachedCriteria dCriteria = DetachedCriteria.forClass(LinkMan.class);
//		1.查询所有的客户
		customers = customerService.findAll();
//		判断是否输入了联系人名称
		if (StringUtils.isNoneBlank(linkman.getLkmName())) {
			dCriteria.add(Restrictions.ilike("lkmName", "%"+linkman.getLkmName()+"%"));
		}
//		判断是否输入性别
		if (StringUtils.isNoneBlank(linkman.getLkmGender())) {
			dCriteria.add(Restrictions.eq("lkmGender", linkman.getLkmGender()));
		}
//		判断是否输入了所属客户
		if (linkman.getCustomer()!=null&&linkman.getCustomer().getCustId()!=null) {
			dCriteria.add(Restrictions.eq("customer.custId", linkman.getCustomer().getCustId()));
		}
//		判断是否输入了职位
		if (StringUtils.isNoneBlank(linkman.getLkmPosition())) {
			dCriteria.add(Restrictions.eq("lkmPosition", linkman.getLkmPosition()));
		}
		
		linkmans = linkManService.findAllLinkMan(dCriteria);
		System.out.println(linkmans);
		return "listUI";

	}

	/**
	 * 删除联系人
	 * 
	 * @return
	 */
	@Action("deleLinkMan")
	public String deleLinkMan() {
		linkManService.deleteLinkMan(linkman.getLkmId());
		
		return "listLinkMan";
	}
	
	/**
	 * 获取编辑联系人的页面
	 * @return
	 */
	@Action("editUILinkMan")
	public String editUILinkMan() {
		//1.获取客户
		customers = customerService.findAll();
		//2.获得联系人
		linkman=linkManService.findLinkMan(linkman.getLkmId());
		//3.压栈
		ActionContext.getContext().getValueStack().push(linkman);
		
		System.out.println(linkman);
		return "editUI";
	}
	/**
	 * 编辑联系人
	 * @return
	 */
	@Action("editLinkMan")
	public String editLinkMan() {
//		1.执
		linkManService.saveLinkMan(linkman);
		
		return "listLinkMan";
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

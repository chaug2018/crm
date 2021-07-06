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
import com.yzj.domain.LinkMan;
import com.yzj.service.LinkManService;
/**
 * @Scope("prototype")
 * 表示每次接收一个请求创建一个对象
 * @author 86186
 */
@Controller("linkManAction")
@ParentPackage("struts-default")
@Scope("prototype")
@Namespace("/linkman")
@Results({
	@Result(name = "addUI",type = "dispatcher",location = "/jsp/linkman/add.jsp"),
	@Result(name = "listLinkMan",type = "dispatcher",location = "/jsp/linkman/list.jsp")
})

/**
 * ActionSupport
 * 在实现了Action接口的基础上还定义了一个validate()方法,
 * 默认的处理用户请求的方法等
 * @author 86186
 *
 */
//实现模型
public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {
	
	private LinkMan linkman=new LinkMan();
	private List<LinkMan> linkmans;
	@Override
	public LinkMan getModel() {
		// TODO Auto-generated method stub
		return linkman;
	}
	
	
	@Resource(name="linkManService")
	LinkManService linkManService;
	/**
	 * 获取新增联系人页面
	 * @return
	 */
	@Action("addUILinkMan")
	public String addUILinkMan() {
		return "addUI";	
	}
	/**
	 * 新增联系人
	 */
	@Action("addLinkMan")
	public String addLinkMan(){
		System.out.println("----------");
		System.out.println(linkman);
		linkManService.addLinkMan(linkman);
		
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

	
	

}

package com.yzj.text;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yzj.domain.BaseDict;
import com.yzj.domain.Customer;
import com.yzj.domain.LinkMan;
import com.yzj.service.CustomerService;
import com.yzj.service.LinkManService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/applicationContext.xml" })
public class CustomerTest {

	@Autowired
	CustomerService customerService;
	
	@Autowired
	LinkManService linkManService;

	@Test
	public void findAll() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
//		List<Customer> all = customerService.findAll(criteria);
//		for (Object o : all) {
//			System.out.println(o);
//		}
	}

	@Test
	public void save() {
		for (int i = 0; i < 60; i++) {
			Customer customer = new Customer();
			BaseDict bd = new BaseDict();
			bd.setDictId(7L);
			customer.setCustName("传智播客集团"+i);
			customer.setCustSource(bd);
			customer.setCustIndustry("IT培训");
			bd.setDictId(23l);
			customer.setCustLevel(bd);
			customer.setCustAddress("北京市昌平区北七家镇修正大厦");
			customer.setCustPhone("010-66668888");
			customerService.save(customer);
		}
	}
	@Test
	public void delete() {
		for (Long i = 5L; i <=84; i++) {
			customerService.deleteCustomer(i);
		}
	}
	@Test 
	public void saveLinkMan() {
		
		LinkMan linkMan=new LinkMan();
		linkMan.setLkmName("张老师");
	
			linkMan.setLkmGender("女");
	
		
		
		linkMan.setLkmPhone("010-82935100");
		linkMan.setLkmMobile("010-82935100");
		
		linkMan.setLkmEmail("zzz@itcast.cn");
		
		linkMan.setLkmPosition("宣传部负责人");
		linkMan.setLkmMemo("很负责");
		Customer customer=new Customer();
		customer.setCustId(9L);
		linkMan.setCustomer(customer);
		System.out.println(linkMan);
		linkManService.saveLinkMan(linkMan);
	}

}

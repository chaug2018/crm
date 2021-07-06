package com.yzj.service.impl;

import static org.junit.Assert.fail;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yzj.dao.LinkManDao;
import com.yzj.domain.LinkMan;
import com.yzj.service.LinkManService;

@Service("linkManService")
@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
public class LinkManServiceImpl implements LinkManService {
	
	@Resource(name="linkManDao")
	LinkManDao linkManDao;
	
	/**
	 * 查询所有的联系人
	 */
	@Override
	public List<LinkMan> findAllLinkMan(DetachedCriteria dCriteria) {
		// TODO Auto-generated method stub
		return linkManDao.findAllLinkMan(dCriteria);
	}
/**
 * 新增一个联系人
 */
	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	public void addLinkMan(LinkMan linkman) {
		linkManDao.addLinkMan(linkman);
		
	}

}

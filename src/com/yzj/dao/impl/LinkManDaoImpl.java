package com.yzj.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.yzj.dao.LinkManDao;
import com.yzj.domain.LinkMan;

@Repository("linkManDao")
public class LinkManDaoImpl implements LinkManDao {

	@Resource(name = "hibernateTemplate")
	HibernateTemplate hibernateTemplate;

	/**
	 * 查询所有的联系人
	 */
	@Override
	public List<LinkMan> findAllLinkMan(DetachedCriteria dCriteria) {
		// TODO Auto-generated method stub
		return (List<LinkMan>) hibernateTemplate.findByCriteria(dCriteria);
	}

	/**
	 * 新增联系人
	 */

	@Override
	public void addLinkMan(LinkMan linkman) {
		hibernateTemplate.save(linkman);

	}

	@Override
	public void deleteLinkManById(Long lkmId) {
//		查询出这个联系人
		LinkMan linkMan = findLinkMan(lkmId);

		hibernateTemplate.delete(linkMan);
	}

	/**
	 * 根据id查询出联系人
	 */
	@Override
	public LinkMan findLinkMan(Long lkmId) {
		return hibernateTemplate.load(LinkMan.class, lkmId);
	}

	@Override
	public void saveLinkMan(LinkMan linkman) {
		hibernateTemplate.update(linkman);
	}

}

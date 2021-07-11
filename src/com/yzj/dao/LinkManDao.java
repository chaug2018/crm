package com.yzj.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.yzj.domain.LinkMan;

public interface LinkManDao {

	List<LinkMan> findAllLinkMan(DetachedCriteria dCriteria);

	void addLinkMan(LinkMan linkman);

	void deleteLinkManById(Long lkmId);
	
	LinkMan findLinkMan(Long lkmId);

	void saveLinkMan(LinkMan linkman);

}

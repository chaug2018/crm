package com.yzj.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.yzj.domain.LinkMan;

public interface LinkManDao {

	List<LinkMan> findAllLinkMan(DetachedCriteria dCriteria);

	void addLinkMan(LinkMan linkman);

}

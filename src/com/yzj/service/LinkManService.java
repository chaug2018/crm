package com.yzj.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.yzj.domain.LinkMan;

public interface LinkManService {

	List<LinkMan> findAllLinkMan(DetachedCriteria dCriteria);

	void addLinkMan(LinkMan linkman);
}

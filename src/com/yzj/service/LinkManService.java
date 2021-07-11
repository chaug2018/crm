package com.yzj.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.yzj.domain.LinkMan;

public interface LinkManService {

	List<LinkMan> findAllLinkMan(DetachedCriteria dCriteria);

	void addLinkMan(LinkMan linkman);

	void deleteLinkMan(Long lkmId);

	LinkMan findLinkMan(Long lkmId);

	void saveLinkMan(LinkMan linkman);
}

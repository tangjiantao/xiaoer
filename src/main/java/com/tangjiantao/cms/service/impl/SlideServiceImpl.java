package com.tangjiantao.cms.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tangjiantao.cms.dao.SlideDao;
import com.tangjiantao.cms.domain.Slide;
import com.tangjiantao.cms.service.SlideService;
@Service
public class SlideServiceImpl implements SlideService {

	@Autowired
	private SlideDao slideDao;

	@Override
	public List<Slide> selects() {
		 
		return slideDao.selects();
	}
}

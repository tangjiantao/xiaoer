package com.tangjiantao.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tangjiantao.cms.dao.CategoryDao;
import com.tangjiantao.cms.domain.Category;
import com.tangjiantao.cms.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	@Override
	public List<Category> selects(Integer channelId) {
		
		return categoryDao.selects(channelId);
	}
}

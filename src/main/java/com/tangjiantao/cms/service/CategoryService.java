package com.tangjiantao.cms.service;

import java.util.List;

import com.tangjiantao.cms.domain.Category;

public interface CategoryService {

	List<Category> selects(Integer channelId);

}

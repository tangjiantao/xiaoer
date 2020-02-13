package com.tangjiantao.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tangjiantao.cms.domain.Category;

public interface CategoryDao {

	List<Category> selects(@Param("channelId")Integer channelId);

}

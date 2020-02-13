package com.tangjiantao.cms.dao;

import java.util.List;

import com.tangjiantao.cms.domain.Article;
import com.tangjiantao.cms.domain.Category;
import com.tangjiantao.cms.domain.Channel;

public interface ArticleDao {

	public List<Article> selectsByAdmin(Article article);

	public void update(Article article);

	public Article select(int id);
	//查询所有的栏目
	public List<Channel> selectsCannel();
	//根据栏目id查询此栏目下的所有分类
	public List<Category> selectsCategory(int id);
	//添加
	public void add(Article article);

}

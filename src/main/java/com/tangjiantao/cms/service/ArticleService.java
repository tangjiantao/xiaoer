package com.tangjiantao.cms.service;



import java.util.List;

import com.github.pagehelper.PageInfo;
import com.tangjiantao.cms.domain.Article;
import com.tangjiantao.cms.domain.Category;
import com.tangjiantao.cms.domain.Channel;

public interface ArticleService {

	
	public boolean upate(Article article);

	public PageInfo<Article> selectsByAdmin(Article article, Integer pageNum, Integer pageSize);

	public Article select(int id);
	//查询所有的栏目
	public List<Channel> selectsChannel();
	//根据栏目id查询此栏目下的所有分类
	public List<Category> selectsCategory(int id);
	//添加
	public void add(Article article);
	//查询24小时内文章
	public List<Article> select24Article(String createTime);
	//查询热门文章
	public List getHotList();
	
}

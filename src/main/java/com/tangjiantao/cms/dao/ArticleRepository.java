package com.tangjiantao.cms.dao;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.tangjiantao.cms.domain.Article;

//就是仓库接口,让他继承一个接口,这个接口就具备了CRUD的方法                                                           指定实体类,       实体类主键的类型
//此时这个接口就已经准备完毕
public interface ArticleRepository extends ElasticsearchRepository<Article, Integer>{
	List<Article> findByTitle(String key);
}

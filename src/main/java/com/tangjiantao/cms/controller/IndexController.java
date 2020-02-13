package com.tangjiantao.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tangjiantao.cms.domain.Article;
import com.tangjiantao.cms.service.ArticleService;

@RequestMapping("indexs")
@Controller
public class IndexController {
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping("select")
	public String select(Model m,Article article) {
		//通过文章id查询文章详情
		Article article1 = articleService.select(article.getId());
		m.addAttribute("article", article1);
		
		return "index/article";
		
	}
	
}

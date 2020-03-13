package com.tangjiantao.cms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.tangjiantao.cms.domain.Article;
import com.tangjiantao.cms.service.ArticleService;
import com.tangjiantao.cms.service.CommentService;

@RequestMapping("indexs")
@Controller
public class IndexController {
	@Autowired
	private ArticleService articleService;

	@Autowired
	private CommentService commentService;
	
	@Autowired
	RedisTemplate redisTemplate;

	@Autowired
	ThreadPoolTaskExecutor executor;
	// 文章详情方法
	/*
	 * 当用户浏览文章时，将“Hits_ ${文章ID}_ _${用户IP地址}为key，
	 * 查询Redis里有没有该key,如果有key,则不做任何操作。如果没有，则 使用Spring线程池异步执行数据库加1操作，
	 * 并往Redis保存key为Hits_ _${文章ID}_ _${用户IP地址}，value为空值的记录，而且有效时长为5分钟。
	 */
	@RequestMapping("select")
	public String select(HttpServletRequest req,Model m, Article article, @RequestParam(defaultValue = "1") int pageNum) {
		// 通过文章id查询文章详情
		Article article1 = articleService.select(article.getId());
		// 1.当用户浏览文章时 将hits_${文章id}_{用户ip地址}为key
		//获取用户的ip地址
		String ip = req.getRemoteAddr();
		String key = "Hits_"+article.getId()+"_"+ip;
		System.err.println("打印"+key);
		//2.查询redis中有没有key 如果有key 则不作任何操作
		boolean haskey=redisTemplate.hasKey(key);
		if (!haskey) {
			//haskey为false说明没有key
			//如果没有，则 使用Spring线程池异步执行数据库加1操作，
			//
			
			executor.execute(new Runnable() {
				
				@Override
				public void run() {
					// 书写自己的逻辑
					//数据库+1
					//获取原来点击量
					Integer hits = article1.getHits();
					article1.setHits(hits+1);
					//重新保存到数据库
					articleService.upate(article1);
					System.err.println("点击量已经+1了！！！");
				}
			});
		}
		// 通过文章id查询文章详情
		m.addAttribute("article", article1);

		// 通过文章的id 查询所有的评论
		PageInfo info = commentService.selects(article.getId(), pageNum, 10);
		m.addAttribute("info", info);

		return "index/article";

	}

}

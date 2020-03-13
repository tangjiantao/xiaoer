package com.tangjiantao.cms.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.listener.MessageListener;

import com.alibaba.fastjson.JSON;
import com.tangjiantao.cms.dao.ArticleDao;
import com.tangjiantao.cms.dao.ArticleRepository;
import com.tangjiantao.cms.domain.Article;


//监听爬虫项目发来的文章的json串
public class ArticleListener implements MessageListener<String, String>{

	@Autowired
	ArticleDao articleDao;
	
	@Autowired
	ArticleRepository articleRepository;
	//就是监听消息的方法
	@Override
	public void onMessage(ConsumerRecord<String, String> data) {
		//========================================
		//这些代码是接受读取的本地文章 保存到mysql数据库
		System.err.println("收到了消息");
		String jsonString = data.value();
		if (jsonString.startsWith("shenhe")) {
			//执行审核的逻辑
			String[] split = jsonString.split("=");
			Integer id = Integer.parseInt(split[1]);
			//拿着这个id去数据库找对应文章
			Article select = articleDao.select(id);
			//得到文章之后就开始往es索引
			articleRepository.save(select);
			System.err.println("同步es索引库成功！！");
		}else {
			//由于jsonString是一个json类型的字符串,所以我们要把这个json字符串转成文章对象,然后保存到mysql
			Article article = JSON.parseObject(jsonString, Article.class);
			//注入articleDao然后就可以直接调用保存方法了
			articleDao.add(article);
			System.err.println("保存了文章对象到mysql数据库...........");
			//========================================
		}
		
		
		
	}

}

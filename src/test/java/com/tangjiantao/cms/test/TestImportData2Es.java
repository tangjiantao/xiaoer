package com.tangjiantao.cms.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tangjiantao.cms.dao.ArticleDao;
import com.tangjiantao.cms.dao.ArticleRepository;
import com.tangjiantao.cms.domain.Article;

import scala.annotation.varargs;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class TestImportData2Es {

	//注入mapper
	//不能加载es.xml?因为articleDao这个接口,是由spring.xml来扫描的.如果是我们只加载了一个es.xml,此时这个接口就会扫描不到
	//报空指针
	
	@Autowired
	ArticleDao articleDao;
	
	@Autowired
	ArticleRepository articleRepository;
	
	//解决冲突的步骤:
		//1.把spring的版本改成:<spring.version>5.1.5.RELEASE</spring.version>
		//2.把校验相关版本改成:<validator.version>5.1.0.Final</validator.version>
		//3.复制解决冲突的依赖:
		/**
		 * 	<!-- 解决冲突的依赖 -->
			<dependency>
				<groupId>javax.validation</groupId>
				<artifactId>validation-api</artifactId>
				<version>1.1.0.Final</version>
			</dependency>
			<dependency>
				<groupId>com.fasterxml.jackson.core</groupId>
				<artifactId>jackson-core</artifactId>
				<version>2.8.3</version>
			</dependency>
			<dependency>
				<groupId>com.fasterxml.jackson.core</groupId>
				<artifactId>jackson-databind</artifactId>
				<version>2.8.3</version>
			</dependency>
		 */
		//4.修改jetty的版本:<version>9.4.9.v20180320</version>
		//5.修改log相关的依赖:在pom文件里加:
		/**
		 * <dependency>
				<groupId>commons-logging</groupId>
				<artifactId>commons-logging</artifactId>
				<version>1.2</version>
			</dependency>
		 */
	
	//需求：保证数据实时更新   保证mysql中的数据和es索引库的数据同步 一致性
	//分析需求
	//1.当对文章进行发布--增加 删除 修改此时mysql中数据修改了
	//2.发布文章 修改文章 删除文章的同时要添加es索引库 修改 删除es索引库
	//3.进行编码
	//1.编码的思路：
	//只需要找到文章的发布 修改 删除的方法 在这些方法内加上一个操作 修改文章 删除文章的同时要添加es索引库 修改 删除es索引库
	//问题1.发布文章的时候就要添加es索引库 2.还是审核通过了再添加es索引库？
	//不审核通过的文章不能在页面显示 意味着es搜索不到
	//必须保证 审核通过的文章才能添加es索引库 去审核文章的方法
	
	@Test
	public void testImport() {
		//必须从mysql中查询出来所有数据
		List<Article> list = articleDao.selectsByAdmin(null);
		
		//2.把从mysql中查询出来的数据,存入es索引库
		articleRepository.saveAll(list);
		System.out.println("导入成功!!!!");
	}
}

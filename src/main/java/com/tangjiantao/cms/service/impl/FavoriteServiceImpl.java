package com.tangjiantao.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bw.utils.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangjiantao.cms.dao.FavoriteDao;
import com.tangjiantao.cms.domain.Favorite;
import com.tangjiantao.cms.domain.User;
import com.tangjiantao.cms.exception.CMSRuntimeException;
import com.tangjiantao.cms.service.FavoriteService;

@Service
public class FavoriteServiceImpl implements FavoriteService{

	@Autowired
	private FavoriteDao dao;
	
	//执行收藏夹
	@Override
	public int addFavo(Favorite favo) throws CMSRuntimeException {
		int i;
		/**
		 * 需要使用上面的isHttpUrl方法判断传入的收藏夹地址是否合法，
		 * 如果合法则保存，否则向上抛出自定义异常(CMSRuntimeException)，页面上显示错误消息。
		 */
		if(!StringUtil.isHttpUrl(favo.getUrl())) {
			//向上抛出自定义异常(CMSRuntimeException)，页面上显示错误消息
			throw new CMSRuntimeException("url不合法");
		}else {
			 i=dao.addFavo(favo);
		}
		
		return i;
	}

	//展示我的收藏夹
	@Override
	public PageInfo selects(int pageNum, int pageSize, User user) {
		PageHelper.startPage(pageNum, pageSize);
		List<Favorite> list=dao.getFavoList(user);
		PageInfo<Favorite> info=new PageInfo<Favorite>(list);
		return info;
	}

}

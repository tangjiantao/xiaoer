package com.tangjiantao.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangjiantao.cms.dao.UserDao;
import com.tangjiantao.cms.domain.User;
import com.tangjiantao.cms.service.UserService;
import com.tangjiantao.cms.vo.UserVO;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public PageInfo<User> getUserList(String username, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<User> list=userDao.getUserList(username);
		return new PageInfo<User>(list);
	}

	@Override
	public int updateLocked(User user) {
		// TODO Auto-generated method stub
		return userDao.updateLocked(user);
	}

	@Override
	public User getOne(int id) {
		// TODO Auto-generated method stub
		return userDao.getOne(id);
	}

	//登录
	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		return userDao.login(user);
	}

	//验证用户唯一 查血此用户名数据库中是否存在
	@Override
	public int getCountByUserName(String username) {
		// TODO Auto-generated method stub
		return userDao.getCountByUsername(username);
	}

	//注册 添加
	@Override
	public void addUser(UserVO user) {
		userDao.addUser(user);
	}
}

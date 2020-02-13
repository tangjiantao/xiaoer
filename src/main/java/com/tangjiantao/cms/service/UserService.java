package com.tangjiantao.cms.service;

import com.github.pagehelper.PageInfo;
import com.tangjiantao.cms.domain.User;
import com.tangjiantao.cms.vo.UserVO;

public interface UserService {
	public PageInfo<User> getUserList(String username,Integer pageNum,Integer pageSize);
	
	public int updateLocked(User user);
	
	public User getOne(int id);

	public User login(User user);

	public int getCountByUserName(String username);

	public void addUser(UserVO user);
}

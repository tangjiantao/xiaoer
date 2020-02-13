package com.tangjiantao.cms.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tangjiantao.cms.dao.ChannelDao;
import com.tangjiantao.cms.domain.Channel;
import com.tangjiantao.cms.service.ChannelService;
@Service
public class ChannelServiceImpl implements ChannelService {

	@Autowired
	private ChannelDao channelDao;

	@Override
	public List<Channel> selects() {
	
		return channelDao.selects();
	}
}

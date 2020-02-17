package com.tangjiantao.cms.dao;

import java.util.List;

import com.tangjiantao.cms.domain.Favorite;
import com.tangjiantao.cms.domain.User;

public interface FavoriteDao {

	//执行收藏
		int addFavo(Favorite favo);
		//展示我的收藏夹
		List<Favorite> getFavoList(User user);
}

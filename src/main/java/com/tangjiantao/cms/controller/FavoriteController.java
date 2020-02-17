package com.tangjiantao.cms.controller;
/**
 * 
    * @ClassName: FavoriteController
    * @Description: 收藏夹
    * @author 唐建涛
    * @date 2020年2月16日
    *
 */

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.tangjiantao.cms.domain.Favorite;
import com.tangjiantao.cms.domain.User;
import com.tangjiantao.cms.exception.CMSRuntimeException;
import com.tangjiantao.cms.service.FavoriteService;

@Controller
@RequestMapping("favorite")
public class FavoriteController {

	@Autowired
	private FavoriteService service;
	
	//展示我的收藏夹
		@RequestMapping("selects")
		public String selects(Model model,@RequestParam(defaultValue = "1")int pageNum,HttpSession session) {
			User user = (User) session.getAttribute("user");
			if(user!=null) {
				PageInfo info=service.selects(pageNum,8,user);
				model.addAttribute("info", info);
				return "my/favo";
			}else {
				//如果没有登录  去登录
				return "index/login";
			}
			
		}
		
		
		//收藏
		@ResponseBody
		@RequestMapping("addFavo")
		public int addFavo(Favorite favo,HttpSession session) {
			//获得登录的对象
			User user = (User) session.getAttribute("user");
			if(user!=null) {
				favo.setUser(user);
			}
			
			//执行收藏
			int i=0;
			try {
				i = service.addFavo(favo);
			} catch (CMSRuntimeException e) {
				i=-1;
				e.getMessage();//打印异常的信息
				e.printStackTrace();
			}
			
			return i;
		}
}

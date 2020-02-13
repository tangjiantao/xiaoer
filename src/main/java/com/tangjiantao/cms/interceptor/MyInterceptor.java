package com.tangjiantao.cms.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.tangjiantao.cms.domain.User;

public class MyInterceptor implements HandlerInterceptor{

	//在controller执行之前执行的方法
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {


		//获取session中的user对象
		User user = (User) request.getSession().getAttribute("user");
		//如果有值证明登录成功
		if (user!=null) {
			//放行
			return true;
		}else {
			//如果没有值  证明没有登录或者登录失败
			//跳转到登录页面
			//获得请求的方法
			request.setAttribute("error", "请您先登录");
			
			String requestURI = request.getRequestURI();
			System.out.println("requestURI======"+requestURI);
			if (requestURI.equals("/admin")) {
				//如果请求后台管理模块没有登录  跳转到后台管理员登录页面
				request.getRequestDispatcher("WEB-INF/view/admin/login.jsp").forward(request, response);
				
			}else if (requestURI.equals("/my")) {
				//如果请求的是个人中心模块没有登录  跳转到个人中心的登陆页面
				request.getRequestDispatcher("WEB-INF/view/index/login.jsp").forward(request, response);
			}
		}
		
		return false;
	}

	//在controller执行之后 在view渲染之前
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	//在controller执行之后执行的方法 在view渲染之后
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}

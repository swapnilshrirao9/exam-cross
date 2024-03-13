package com.exam.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.exam.web.controller.ExamController;

/**
 * Interceptor class to check if the user is authenticated.
 * 
 * @author shubhankar_roy
 *
 */
public class ExamInterceptor implements HandlerInterceptor {

	/**
	 * logger instance
	 */
	private final Logger logger = LoggerFactory.getLogger(ExamController.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.debug("preHandle() called.");
		String applicationName = (String) request.getServletContext().getAttribute("applicationName");
		String uri = request.getRequestURI();
		String username = (String) request.getSession().getAttribute("username");
		if(!uri.endsWith("login") && !uri.endsWith("/") && !uri.endsWith(applicationName) && username == null) {
			response.sendRedirect("/" + applicationName + "/");
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.debug("postHandle() called.");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.debug("afterCompletion() called.");
	}

}

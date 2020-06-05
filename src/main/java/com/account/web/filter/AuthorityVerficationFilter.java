package com.account.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.account.core.tool.StringTool;

public class AuthorityVerficationFilter implements Filter {
	private String[] filters = null;
	private String[] excludeUrls = null;
	private String redirectUrl = "system/main.jsp";

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		boolean flag = false;
		// fetch objects
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String url = req.getServletPath();
		HttpSession session = req.getSession();

		// logic judge
		if (filters != null) {
			if (filter(url)) {
				if(!filterUrl(url)){
					if (session != null) {
						Object objUserName = session.getAttribute("app_username");
						if (objUserName != null) {
							flag = true;
						} else {
							if (url.indexOf(redirectUrl) > 0) {
								flag = true;
							}
						}
					}
				}else{
					flag = true;
				}
			} else {
				flag = true;
			}
		} else {
			flag = true;
		}

		// return result
		if (flag) {
			chain.doFilter(request, response);
		} else {
			res.sendRedirect(redirectUrl);
		}
	}

	public void init(FilterConfig config) throws ServletException {
		String filter = config.getInitParameter("filter");
		String excludeUrl = config.getInitParameter("excludeUrls");
		if (!StringTool.isNullOrEmpty(filter)) {
			filters = filter.split(",");
		}
		if (!StringTool.isNullOrEmpty(excludeUrl)) {
			excludeUrls = excludeUrl.split(",");
		}
	}

	private boolean filter(String url) {
		boolean result = false;
		if (!StringTool.isNullOrEmpty(url)) {
			for (String filter : filters) {
				if (url.indexOf(filter) > 0) {
					result = true;
					break;
				}
			}
		}
		return result;
	}

	private boolean filterUrl(String url) {
		boolean result = false;
		if (!StringTool.isNullOrEmpty(url)) {
			for (String excludeUrl : excludeUrls) {
				if (url.indexOf(excludeUrl) > 0) {
					result = true;
					break;
				}
			}
		}
		return result;
	}
}

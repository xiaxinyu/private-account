package com.account.web.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.account.core.tool.StringTool;

public class AuthorityVerficationFilter implements Filter {
	private String[] filters = null;
	private String[] excludeUrls = null;
	private String redirectUrl = "/login.html";

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

package com.orad.Project3.login;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.orad.Project3.session.Session;
import com.orad.Project3.session.SessionContext;

@Component
public class LoginFilter implements Filter {

	private SessionContext sessionContext;

	public LoginFilter(SessionContext sessionContext) {
		this.sessionContext = sessionContext;
	}

	@Override
	public void destroy() {
		// unimplemented

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String path = req.getRequestURL().toString();
		if (path.contains("/login")) {
			chain.doFilter(request, response); // just continue chain.
			return;
		}

		String token = req.getHeader("token");
		if (token != null) {
			Session session = sessionContext.getSession(token);
			if (session != null) {
				chain.doFilter(request, response);
				return;
			}
		}

		// pre-flight(comes w/o token)
		if (req.getMethod().equalsIgnoreCase("options")) {
			chain.doFilter(request, response);

		} else {// no session
			res.setHeader("Access-Control-Allow-Origin", "*");
			res.setHeader("Access-Control-Allow-Headers", "*");
			res.sendError(HttpStatus.UNAUTHORIZED.value(), "You are not logged in");
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// unimplemented

	}

}

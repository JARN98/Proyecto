package com.salesianostriana.pruebaproyecto.security;

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

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.salesianostriana.pruebaproyecto.controller.LoginController;
import com.salesianostriana.pruebaproyecto.model.Usuario;

@Configuration
public class SecurityConfiguration {
	@Bean
	public FilterRegistrationBean<SecurityFilter> filterSecurityBean() {
		FilterRegistrationBean<SecurityFilter> registro = new FilterRegistrationBean<>();
		registro.setFilter(securityFilter());
		registro.addUrlPatterns("/FilterUser/*");
		registro.setName("securityFilter");
		return registro;
	}

	@Bean("securityFilter")
	public SecurityFilter securityFilter() {
		return new SecurityFilter();
	}

	@Bean
	public FilterRegistrationBean<SecurityFilter> filterSecurityBean2() {
		FilterRegistrationBean<SecurityFilter> registro = new FilterRegistrationBean<>();
		registro.setFilter(securityFilter2());
		registro.addUrlPatterns("/usuarios");
		registro.setName("securityFilter2");
		return registro;
	}

	@Bean("securityFilter2")
	public SecurityFilter securityFilter2() {
		return new SecurityFilter();
	}

	class SecurityFilter implements Filter {

		@Override
		public void init(FilterConfig filterConfig) throws ServletException {
			// TODO Auto-generated method stub

		}

		public void doFilter2(ServletRequest req, ServletResponse resp, FilterChain chain)
				throws IOException, ServletException {
			HttpServletRequest request = (HttpServletRequest) req;
			HttpServletResponse response = (HttpServletResponse) resp;
			HttpSession session = request.getSession();
			Usuario user = (Usuario) session.getAttribute("usuarioActual");

			if (LoginController.usuario.isAdmin() == false || LoginController.usuario == null) {
				response.sendRedirect("/login");
				return;
			} else {
				chain.doFilter(req, resp);
			}
		}

		@Override
		public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
				throws IOException, ServletException {
			HttpServletRequest request = (HttpServletRequest) req;
			HttpServletResponse response = (HttpServletResponse) resp;
			HttpSession session = request.getSession();

			if (session.getAttribute("usuarioActual") == null) {
				response.sendRedirect("/login");
				return;
			} else
				chain.doFilter(req, resp);

		}

		@Override
		public void destroy() {
			// TODO Auto-generated method stub

		}

	}

}

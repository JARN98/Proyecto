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

@Configuration
public class SecurityConfiguration {

	public static boolean error6 = false;

	/*
	 * Tengos dos filtros, el primero sirve para que un usuario que no esté
	 * registrado no pueda reservar una habitación y el segundo sirve para que solo
	 * pueda entrar a la pantalla de administrador un usuario administrador.
	 */

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
	public FilterRegistrationBean<SecurityFilter2> filterSecurityBean2() {
		FilterRegistrationBean<SecurityFilter2> registro = new FilterRegistrationBean<>();
		registro.setFilter(securityFilter2());
		registro.addUrlPatterns("/usuarios");
		registro.setName("securityFilter2");
		return registro;
	}

	@Bean("securityFilter2")
	public SecurityFilter2 securityFilter2() {
		return new SecurityFilter2();
	}

	class SecurityFilter2 implements Filter {

		@Override
		public void init(FilterConfig filterConfig) throws ServletException {
			// TODO Auto-generated method stub

		}

		@Override
		public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
				throws IOException, ServletException {
			HttpServletRequest request = (HttpServletRequest) req;
			HttpServletResponse response = (HttpServletResponse) resp;
			HttpSession session = request.getSession();

			if (session.getAttribute("usuarioActual") == null || LoginController.usuario.isAdmin() == false) {
				response.sendRedirect("/");
				return;
			} else {
				chain.doFilter(req, resp);
			}

		}

		@Override
		public void destroy() {
			// TODO Auto-generated method stub

		}

	}

	class SecurityFilter implements Filter {
		@Override
		public void init(FilterConfig filterConfig) throws ServletException {
			// TODO Auto-generated method stub

		}

		@Override
		public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
				throws IOException, ServletException {
			HttpServletRequest request = (HttpServletRequest) req;
			HttpServletResponse response = (HttpServletResponse) resp;
			HttpSession session = request.getSession();

			if (session.getAttribute("usuarioActual") == null) {
				response.sendRedirect("/login");
				error6 = true;
				return;
			} else {
				error6 = false;
				chain.doFilter(req, resp);

			}

		}

		@Override
		public void destroy() {
			// TODO Auto-generated method stub

		}
	}

}

package com.addo.basicserver.config;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterComponent extends OncePerRequestFilter {
	
	private final SecureRandom securerand = new SecureRandom(); 
	private final String Cookie_Name = "API_CLIENT_ID";
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String ClientIp = request.getRemoteAddr();
		String clientToken = getOrGenerateCookieId(request, response);
		
		System.out.println(clientToken);
		
		filterChain.doFilter(request, response);
		
	}
	
	
	/*
	 * Generate secure token
	 * */
	private String generateSecureToken() {
		byte[] RandomBytes = new byte[32];
		securerand.nextBytes(RandomBytes);
		String securetoken = Base64.getUrlEncoder().withoutPadding().encodeToString(RandomBytes);
		return securetoken;
	}
	
	/*
	 * Get or generate Cookies token
	 * */
	private String getOrGenerateCookieId(HttpServletRequest request, HttpServletResponse response) {
		if (request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {
                if (Cookie_Name.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
		}
		
		/*
		 * if no cookies found 
		 * create a new cookie and set the cookie id to the requester
		 * */
		String ClientCookieId = generateSecureToken();
		Cookie newcookie = new Cookie(Cookie_Name, ClientCookieId);
		newcookie.setHttpOnly(true);
		newcookie.setSecure(false); // make it true in production so you can validate it over https
		newcookie.setPath("/");
		newcookie.setMaxAge(60 * 60 * 24 * 365);
		response.addCookie(newcookie);
		return ClientCookieId;
	}
}

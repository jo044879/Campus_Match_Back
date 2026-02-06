package com.pigs.holiday.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.pigs.holiday.domain.Club;
import com.pigs.holiday.repository.ClubRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
	
	private final ClubRepository clubRepository;
	private final AuthService authService;
	private final ExternalProperties externalProperties;

	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, ClubRepository clubRepository, AuthService authService
			, ExternalProperties externalProperties
	) {
		super(authenticationManager);
		this.clubRepository = clubRepository;
		this.authService = authService;
		this.externalProperties = externalProperties;
	}

	/**
     *  권한 인가를 위한 함수.
     *  Access Token을 검증하고 유효하면 Authentication을 직접 생성해 SecurityContextHolder에 넣는다.
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		String jwtHeader = request.getHeader(externalProperties.getAccessKey());

		if(jwtHeader == null || !jwtHeader.startsWith(externalProperties.getTokenPrefix())) {
			// 토큰 없을 시 Authentication 없는 상태로 doFilter -> Spring Security가 잡아낸다.
			chain.doFilter(request, response);
			return;
		}

		String accessToken = jwtHeader.substring(externalProperties.getTokenPrefix().length());

		Long userId = authService.verifyAccessToken(accessToken);

		// 유저 조회, 없을 시 return NoMatchingDataException(404)
		Club club = clubRepository.findById(userId).orElse(null);

		// PrincipalDetails 생성
		PrincipalDetails principalDetails = new PrincipalDetails(club);

		// Authentication 생성
		Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());
		// SecurityContextHolder에 Authentication을 담아서 Spring Security가 권한 처리 할 수 있게 한다.
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);

	}

}
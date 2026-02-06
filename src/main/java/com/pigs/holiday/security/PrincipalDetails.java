package com.pigs.holiday.security;

import com.pigs.holiday.domain.Club;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
public class PrincipalDetails implements UserDetails {
	
	private final Club club;
	
	public PrincipalDetails(Club club) {
		this.club = club;
	}
	
	public Club getUser() {
        return club;
    }

	@Override
	public String getPassword() {
		return club.getPassword();
	}

	@Override
	public String getUsername() {
		return club.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
    /**
	 *  User Role 파싱하는 함수
	 *  @return Collection<? extends GrantedAuthority> authorities
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(()->"ROLE_USER");

		return authorities;
	}

}
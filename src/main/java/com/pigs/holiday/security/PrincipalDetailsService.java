package com.pigs.holiday.security;

import lombok.RequiredArgsConstructor;
import com.pigs.holiday.domain.Club;
import com.pigs.holiday.exception.NoMatchingDataException;
import com.pigs.holiday.repository.ClubRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService {
	
	private final ClubRepository clubRepository;
	
    /**
	 *  principalDetails 생성 (username)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Club club = clubRepository.findByUsername(username).orElse(null);

		if(club == null) {
			throw new NoMatchingDataException("NoMatchData username: " + username);
		}

		return new PrincipalDetails(club);
	}
	
}
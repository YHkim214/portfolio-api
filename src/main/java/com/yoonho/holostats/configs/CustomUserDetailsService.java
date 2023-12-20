package com.yoonho.holostats.configs;

import com.yoonho.holostats.models.Member;
import com.yoonho.holostats.repositories.MemberRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    final MemberRepository memberRepository;

    public CustomUserDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.getMemberByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("No User Found"));
        return new User(member.getMemberName(),
                        member.getMemberPassword(),
                        toGrantedAuthorities(member.getMemberRole()));
    }

    private Collection<? extends GrantedAuthority> toGrantedAuthorities(String memberRole) {
        return List.of(new SimpleGrantedAuthority(memberRole));
    }
}

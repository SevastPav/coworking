package org.spm.coworking.services;

import lombok.Getter;
import org.spm.coworking.entity.UserProfile;
import org.spm.coworking.services.repo.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collection;
import java.util.Optional;

@Service
@SessionScope
@Getter
public class AuthService {

    @Autowired
    private UserProfileService userProfileService;

    public boolean hasRole(String role){
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();
        return roles.stream()
                .anyMatch(r -> ((GrantedAuthority) r).getAuthority().contains(role));
    }

    public boolean isAuth(){
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();
        return roles.stream()
                .anyMatch(r -> ((GrantedAuthority) r).getAuthority().contains("ROLE_ANONYMOUS"));
    }

    public UserProfile getCurrentUserProfile(){
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        return userProfileService.findByLogin(authentication.getName()).orElse(null);
    }

}

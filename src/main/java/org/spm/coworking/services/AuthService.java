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
        for (GrantedAuthority rle:roles) {
            if (rle.getAuthority().contains(role))
                return true;
        }
        return false;
    }

    public boolean isAuth(){
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();
        for (GrantedAuthority rle:roles) {
            if (rle.getAuthority().contains("ROLE_ANONYMOUS"))
                return false;
        }
        return true;
    }

    public UserProfile getCurrentUserProfile(){
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        Optional<UserProfile> user = userProfileService.findByLogin(authentication.getName());
        return user.orElse(null);
    }

}

package com.xuwenxing.securityDemo.config.security;

import com.xuwenxing.securityDemo.domain.system.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 构建真正用于SpringSecurity登录的安全用户(userDetails)
 * Created by xuwx on 2018/10/11.
 */
public class SecurityUser extends User implements UserDetails {

    private static final long SerialVersionUID = 1l;

    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    //构造器
    public SecurityUser(User user) {
        if (user != null) {
            this.setUserId(user.getId());
            this.setUsername(user.getUsername());
            this.setPassword(user.getPassword());
            this.setPhoneNumber(user.getPhoneNumber());
            this.setUserType(user.getUserType());
            this.setUserCode(user.getUserCode());
            this.setHeadPortrait(user.getHeadPortrait());
            this.setIsActive(user.getIsActive());
            this.setEmail(user.getEmail());
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        String username = this.getUsername();
        if (username != null){
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(username);
            authorities.add(authority);
        }
        return authorities;
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
}

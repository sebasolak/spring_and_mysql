package com.example.demo.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository("fakeAuth")
public class FakeApplicationUserDaoService implements ApplicationUserDao {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FakeApplicationUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUser()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }


    private List<ApplicationUser> getApplicationUser() {
        List<ApplicationUser> applicationUsers = Arrays.asList(
                new ApplicationUser("boss",
                        passwordEncoder.encode("pass"),
                        Arrays.asList(new SimpleGrantedAuthority("ROLE_BOSS"),
                                new SimpleGrantedAuthority("ROLE_MANAGER")),
                        true,
                        true,
                        true,
                        true
                ),
                new ApplicationUser("manager",
                        passwordEncoder.encode("pass"),
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_MANAGER")),
                        true,
                        true,
                        true,
                        true
                )
        );
        return applicationUsers;
    }
}

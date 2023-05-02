package com.moviebookingapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.moviebookingapp.model.User;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

class GroupUserDetailsTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link GroupUserDetails#GroupUserDetails(User)}
     *   <li>{@link GroupUserDetails#getPassword()}
     *   <li>{@link GroupUserDetails#getUsername()}
     *   <li>{@link GroupUserDetails#isAccountNonExpired()}
     *   <li>{@link GroupUserDetails#isAccountNonLocked()}
     *   <li>{@link GroupUserDetails#isCredentialsNonExpired()}
     *   <li>{@link GroupUserDetails#isEnabled()}
     * </ul>
     */
    @Test
    void testConstructor() {
        User user = new User();
        user.setContactNumber("42");
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.setLoginId("42");
        user.setPassword("testpass");
        user.setRole("Role");
        GroupUserDetails actualGroupUserDetails = new GroupUserDetails(user);
        assertEquals("testpass", actualGroupUserDetails.getPassword());
        assertEquals("42", actualGroupUserDetails.getUsername());
        assertTrue(actualGroupUserDetails.isAccountNonExpired());
        assertTrue(actualGroupUserDetails.isAccountNonLocked());
        assertTrue(actualGroupUserDetails.isCredentialsNonExpired());
        assertTrue(actualGroupUserDetails.isEnabled());
    }

    /**
     * Method under test: {@link GroupUserDetails#GroupUserDetails(User)}
     */
    @Test
    void testConstructor2() {
        User user = new User();
        user.setContactNumber("42");
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.setLoginId("42");
        user.setPassword("testpass");
        user.setRole("Role");
        GroupUserDetails actualGroupUserDetails = new GroupUserDetails(user);
        Collection<? extends GrantedAuthority> authorities = actualGroupUserDetails.getAuthorities();
        assertEquals(1, authorities.size());
        assertEquals("testpass", actualGroupUserDetails.getPassword());
        assertEquals("42", actualGroupUserDetails.getUsername());
        assertEquals("Role", ((List<? extends GrantedAuthority>) authorities).get(0).getAuthority());
    }
}


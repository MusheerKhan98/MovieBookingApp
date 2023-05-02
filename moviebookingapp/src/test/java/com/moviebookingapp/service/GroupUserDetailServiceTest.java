package com.moviebookingapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.moviebookingapp.model.User;
import com.moviebookingapp.repository.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {GroupUserDetailService.class})
@ExtendWith(SpringExtension.class)
class GroupUserDetailServiceTest {
    @Autowired
    private GroupUserDetailService groupUserDetailService;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link GroupUserDetailService#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername() {
        User user = new User();
        user.setContactNumber("42");
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.setLoginId("42");
        user.setPassword("testpass");
        user.setRole("Role");
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        UserDetails actualLoadUserByUsernameResult = groupUserDetailService.loadUserByUsername("42");
        Collection<? extends GrantedAuthority> authorities = actualLoadUserByUsernameResult.getAuthorities();
        assertEquals(1, authorities.size());
        assertEquals("testpass", actualLoadUserByUsernameResult.getPassword());
        assertEquals("42", actualLoadUserByUsernameResult.getUsername());
        assertEquals("Role", ((List<? extends GrantedAuthority>) authorities).get(0).getAuthority());
        verify(userRepository).findById(Mockito.<String>any());
    }

    /**
     * Method under test: {@link GroupUserDetailService#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername3() {
        when(userRepository.findById(Mockito.<String>any())).thenReturn(Optional.empty());
        User user = mock(User.class);
        when(user.getLoginId()).thenReturn("42");
        when(user.getPassword()).thenReturn("testpass");
        when(user.getRole()).thenReturn("Role");
        doNothing().when(user).setContactNumber(Mockito.<String>any());
        doNothing().when(user).setEmail(Mockito.<String>any());
        doNothing().when(user).setFirstName(Mockito.<String>any());
        doNothing().when(user).setLastName(Mockito.<String>any());
        doNothing().when(user).setLoginId(Mockito.<String>any());
        doNothing().when(user).setPassword(Mockito.<String>any());
        doNothing().when(user).setRole(Mockito.<String>any());
        user.setContactNumber("42");
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.setLoginId("42");
        user.setPassword("testpass");
        user.setRole("Role");
        assertThrows(UsernameNotFoundException.class, () -> groupUserDetailService.loadUserByUsername("42"));
        verify(userRepository).findById(Mockito.<String>any());
        verify(user).setContactNumber(Mockito.<String>any());
        verify(user).setEmail(Mockito.<String>any());
        verify(user).setFirstName(Mockito.<String>any());
        verify(user).setLastName(Mockito.<String>any());
        verify(user).setLoginId(Mockito.<String>any());
        verify(user).setPassword(Mockito.<String>any());
        verify(user).setRole(Mockito.<String>any());
    }
}


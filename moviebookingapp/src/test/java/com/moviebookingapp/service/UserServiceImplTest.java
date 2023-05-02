package com.moviebookingapp.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.moviebookingapp.custom.exception.InvalidPasswordException;
import com.moviebookingapp.custom.exception.UnregisteredUserException;
import com.moviebookingapp.custom.exception.UserAlreadyExistsException;
import com.moviebookingapp.custom.exception.UserNotFoundException;
import com.moviebookingapp.dto.LoginDTO;
import com.moviebookingapp.model.User;
import com.moviebookingapp.repository.UserRepository;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @MockBean
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * Method under test: {@link UserServiceImpl#register(User)}
     */
    @Test
    void testRegister() throws UserAlreadyExistsException {
        User user = new User();
        user.setContactNumber("42");
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.setLoginId("42");
        user.setPassword("testpass");
        user.setRole("Role");

        User user2 = new User();
        user2.setContactNumber("42");
        user2.setEmail("jane.doe@example.org");
        user2.setFirstName("Jane");
        user2.setLastName("Doe");
        user2.setLoginId("42");
        user2.setPassword("testpass");
        user2.setRole("Role");
        Optional<User> ofResult = Optional.of(user2);
        when(userRepository.save(Mockito.<User>any())).thenReturn(user);
        when(userRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        User user3 = new User();
        user3.setContactNumber("42");
        user3.setEmail("jane.doe@example.org");
        user3.setFirstName("Jane");
        user3.setLastName("Doe");
        user3.setLoginId("42");
        user3.setPassword("testpass");
        user3.setRole("Role");
        assertThrows(UserAlreadyExistsException.class, () -> userServiceImpl.register(user3));
        verify(userRepository).findById(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserServiceImpl#register(User)}
     */
    @Test
    void testRegister2() throws UserAlreadyExistsException {
        User user = new User();
        user.setContactNumber("42");
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.setLoginId("42");
        user.setPassword("testpass");
        user.setRole("Role");
        when(userRepository.save(Mockito.<User>any())).thenReturn(user);
        when(userRepository.findById(Mockito.<String>any())).thenReturn(Optional.empty());

        User user2 = new User();
        user2.setContactNumber("42");
        user2.setEmail("jane.doe@example.org");
        user2.setFirstName("Jane");
        user2.setLastName("Doe");
        user2.setLoginId("42");
        user2.setPassword("testpass");
        user2.setRole("Role");
        assertTrue(userServiceImpl.register(user2));
        verify(userRepository).save(Mockito.<User>any());
        verify(userRepository).findById(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserServiceImpl#authenticate(LoginDTO)}
     */
    @Test
    void testAuthenticate2() throws InvalidPasswordException, UnregisteredUserException {
        User user = mock(User.class);
        when(user.getPassword()).thenReturn("foo");
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
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        LoginDTO loginCredentials = new LoginDTO();
        loginCredentials.setUsername("42");
        loginCredentials.setPassword("testpass");
        assertThrows(InvalidPasswordException.class, () -> userServiceImpl.authenticate(loginCredentials));
        verify(userRepository, atLeast(1)).findById(Mockito.<String>any());
        verify(user).getPassword();
        verify(user).setContactNumber(Mockito.<String>any());
        verify(user).setEmail(Mockito.<String>any());
        verify(user).setFirstName(Mockito.<String>any());
        verify(user).setLastName(Mockito.<String>any());
        verify(user).setLoginId(Mockito.<String>any());
        verify(user).setPassword(Mockito.<String>any());
        verify(user).setRole(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserServiceImpl#authenticate(LoginDTO)}
     */
    @Test
    void testAuthenticate3() throws InvalidPasswordException, UnregisteredUserException {
        when(userRepository.findById(Mockito.<String>any())).thenReturn(Optional.empty());
        User user = mock(User.class);
        when(user.getPassword()).thenReturn("testpass");
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

        LoginDTO loginCredentials = new LoginDTO();
        loginCredentials.setUsername("42");
        loginCredentials.setPassword("testpass");
        assertThrows(UnregisteredUserException.class, () -> userServiceImpl.authenticate(loginCredentials));
        verify(userRepository).findById(Mockito.<String>any());
        verify(user).setContactNumber(Mockito.<String>any());
        verify(user).setEmail(Mockito.<String>any());
        verify(user).setFirstName(Mockito.<String>any());
        verify(user).setLastName(Mockito.<String>any());
        verify(user).setLoginId(Mockito.<String>any());
        verify(user).setPassword(Mockito.<String>any());
        verify(user).setRole(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserServiceImpl#updatePassword(String, User)}
     */
    @Test
    void testUpdatePassword() throws UserNotFoundException {
        User user = new User();
        user.setContactNumber("42");
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.setLoginId("42");
        user.setPassword("testpass");
        user.setRole("Role");
        Optional<User> ofResult = Optional.of(user);

        User user2 = new User();
        user2.setContactNumber("42");
        user2.setEmail("jane.doe@example.org");
        user2.setFirstName("Jane");
        user2.setLastName("Doe");
        user2.setLoginId("42");
        user2.setPassword("testpass");
        user2.setRole("Role");
        when(userRepository.save(Mockito.<User>any())).thenReturn(user2);
        when(userRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        User user3 = new User();
        user3.setContactNumber("42");
        user3.setEmail("jane.doe@example.org");
        user3.setFirstName("Jane");
        user3.setLastName("Doe");
        user3.setLoginId("42");
        user3.setPassword("testpass");
        user3.setRole("Role");
        assertSame(user2, userServiceImpl.updatePassword("42", "testpass"));
        verify(userRepository).save(Mockito.<User>any());
        verify(userRepository).findById(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserServiceImpl#updatePassword(String, User)}
     */
    @Test
    void testUpdatePassword2() throws UserNotFoundException {
        User user = new User();
        user.setContactNumber("42");
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.setLoginId("42");
        user.setPassword("testpass");
        user.setRole("Role");
        when(userRepository.save(Mockito.<User>any())).thenReturn(user);
        when(userRepository.findById(Mockito.<String>any())).thenReturn(Optional.empty());

        User user2 = new User();
        user2.setContactNumber("42");
        user2.setEmail("jane.doe@example.org");
        user2.setFirstName("Jane");
        user2.setLastName("Doe");
        user2.setLoginId("42");
        user2.setPassword("testpass");
        user2.setRole("Role");
        assertThrows(UserNotFoundException.class, () -> userServiceImpl.updatePassword("42", "testpass"));
        verify(userRepository).findById(Mockito.<String>any());
    }
}


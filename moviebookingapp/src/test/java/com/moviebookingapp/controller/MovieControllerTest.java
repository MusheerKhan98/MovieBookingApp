package com.moviebookingapp.controller;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moviebookingapp.dto.BookingRequest;
import com.moviebookingapp.model.Movie;
import com.moviebookingapp.model.MoviePK;
import com.moviebookingapp.service.MovieService;

import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {MovieController.class})
@ExtendWith(SpringExtension.class)
class MovieControllerTest {
    @Autowired
    private MovieController movieController;

    @MockBean
    private MovieService movieService;




    /**
     * Method under test: {@link MovieController#updateTicketStatus(int, MoviePK)}
     */
    @Test
    void testUpdateTicketStatus() throws Exception {
        when(movieService.updateTicketStatus(anyInt(), Mockito.<MoviePK>any())).thenReturn(true);

        MoviePK moviePK = new MoviePK();
        moviePK.setMovieName("Movie Name");
        moviePK.setTheatreName("Theatre Name");
        String content = (new ObjectMapper()).writeValueAsString(moviePK);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/v1.0/moviebooking/update/{count}", 3)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions resultActions = MockMvcBuilders.standaloneSetup(movieController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        ContentResultMatchers contentResult = MockMvcResultMatchers.content();
        resultActions.andExpect(contentResult.string(Boolean.TRUE.toString()));
    }


    /**
     * Method under test: {@link MovieController#deleteById(MoviePK)}
     */
    @Test
    void testDeleteById2() throws Exception {
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders
                .formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(movieController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link MovieController#searchByName(String)}
     */
    @Test
    void testSearchByName() throws Exception {
        when(movieService.getAllMoviesByName(Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1.0/moviebooking/search/{name}",
                "Name");
        MockMvcBuilders.standaloneSetup(movieController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link MovieController#searchByName(String)}
     */
    @Test
    void testSearchByName2() throws Exception {
        when(movieService.getAllMoviesByName(Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1.0/moviebooking/search/{name}",
                "Name");
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(movieController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link MovieController#viewAllMovies()}
     */
    @Test
    void testViewAllMovies() throws Exception {
        when(movieService.findAllMovies()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1.0/moviebooking/all");
        MockMvcBuilders.standaloneSetup(movieController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link MovieController#viewAllMovies()}
     */
    @Test
    void testViewAllMovies2() throws Exception {
        when(movieService.findAllMovies()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1.0/moviebooking/all");
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(movieController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}


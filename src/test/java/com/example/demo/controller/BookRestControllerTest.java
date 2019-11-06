package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.entity.Borrower;
import com.example.demo.repository.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookRestControllerTest {
    @MockBean
 BookRepository bookRepository;

    @Autowired
    MockMvc mockMvc;
    @Test
    public void addnewBook() {

    }

    @Test
    public void getBookByAuthorName() throws Exception {
        String payload="[\n" +
                "    [\n" +
                "        \"Oliver Twist\",\n" +
                "        \"Charles Dickens\",\n" +
                "        \"Social Novel\"\n" +
                "    ],\n" +
                "    [\n" +
                "        \"Tale of two Cities\",\n" +
                "        \"Charles Dickens\",\n" +
                "        \"Social Novel\"\n" +
                "    ]\n" +
                "]";
        mockMvc.perform(MockMvcRequestBuilders
                .get("/getBookByAuthor/Charles Dickens")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(payload)).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andReturn();



    }

    @Test
    public void getBookById() throws Exception{
        Book book=new Book();
        book.setId(84L);
        book.setAuthor("Karl Marx");
        book.setGenre("Political");
        book.setTitle("das Kapital");
        Borrower borrower=new Borrower(64L,"Mohamed","Darmstadt");
        book.setBorrower(borrower);
        Mockito.when(bookRepository.findById(84L)).thenReturn(Optional.of(book));


     String payload="{\n" +
             "    \"id\": 84,\n" +
             "    \"title\": \"das Kapital\",\n" +
             "    \"genre\": \"Political\",\n" +
             "    \"author\": \"Karl Marx\",\n" +
             "    \"borrower\": {\n" +
             "        \"id\": 64,\n" +
             "        \"name\": \"Mohamed\",\n" +
             "        \"address\": \"Darmstadt\"\n" +
             "    }\n" +
             "}";
     mockMvc.perform(MockMvcRequestBuilders.get("/findBookById/"+84)
            .accept(MediaType.APPLICATION_JSON)
             .contentType(MediaType.APPLICATION_JSON).content(payload)).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
             .andExpect(status().isFound()).andReturn();
    }

    @Test
    public void getBorrowerByBookTitle() {
    }
}
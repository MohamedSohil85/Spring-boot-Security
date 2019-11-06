package com.example.demo.controller;

import com.example.demo.entity.Borrower;
import com.example.demo.repository.BorrowerRepository;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BorrowerRestControllerTest {

    @MockBean
   BorrowerRepository borrowerRepository;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void saveBorrower() throws Exception {
        Borrower borrower=new Borrower();
        borrower.setName("Jannik");
        borrower.setAddress("Darmstadt");
        Mockito.when(borrowerRepository.save(borrower)).thenReturn(borrower);
        String payload="{\"name\":\"Jannik\",\"address\":\"Darmstadt\"}";
        mockMvc.perform(MockMvcRequestBuilders
                .post("/addBorrower")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                 .content(payload))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
               .andDo(MockMvcResultHandlers.print());





    }

    @Test
    public void testgetBorrowerbyKeyword() throws Exception{
        Borrower borrower=new Borrower();
        borrower.setName("Jannik");
        borrower.setAddress("Darmstadt");
        List<Borrower>borrowers=Arrays.asList(borrower);

        Mockito.when(borrowerRepository.findBorrowerByNameContaining("J")).thenReturn(borrowers);
        String payload="{\"name\":\"Mohamed\",\"address\":\"Darmstadt\"}";
        mockMvc.perform(MockMvcRequestBuilders.get("/findBorrowerByKeyword/J")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .content(payload)).andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType("application/json;charset=UTF-8")).andReturn();

    }
    @Test
    public void testsortListByName() throws Exception{
        String payload="[\n" +
                "    {\n" +
                "        \"id\": 121,\n" +
                "        \"name\": \"David\",\n" +
                "        \"address\": \"Giessen\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 161,\n" +
                "        \"name\": \"Ingo\",\n" +
                "        \"address\": \"Giessen\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 61,\n" +
                "        \"name\": \"Jannik\",\n" +
                "        \"address\": \"Darmstadt\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 101,\n" +
                "        \"name\": \"Julian\",\n" +
                "        \"address\": \"Giessen\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 123,\n" +
                "        \"name\": \"Karl\",\n" +
                "        \"address\": \"Freiburg\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 122,\n" +
                "        \"name\": \"Manfred\",\n" +
                "        \"address\": \"Marburg\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 64,\n" +
                "        \"name\": \"Mohamed\",\n" +
                "        \"address\": \"Darmstadt\"\n" +
                "    }\n" +
                "]";

        mockMvc.perform(MockMvcRequestBuilders
                .get("/getListofBorrowers")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(payload)).andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType("application/json;charset=UTF-8")).andReturn();
    }

   /* List<Book> books = Arrays.asList(
            new Book(1L, "Book A", "Ah Pig", new BigDecimal("1.99")),
            new Book(2L, "Book B", "Ah Dog", new BigDecimal("2.99")));

    when(mockRepository.findAll()).thenReturn(books);

        mockMvc.perform(get("/books"))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].id", is(1)))
            .andExpect(jsonPath("$[0].name", is("Book A")))
            .andExpect(jsonPath("$[0].author", is("Ah Pig")))
            .andExpect(jsonPath("$[0].price", is(1.99)))
            .andExpect(jsonPath("$[1].id", is(2)))
            .andExpect(jsonPath("$[1].name", is("Book B")))
            .andExpect(jsonPath("$[1].author", is("Ah Dog")))
            .andExpect(jsonPath("$[1].price", is(2.99)));

    verify(mockRepository, times(1)).findAll();*/
   @Test
    public void getBorrowers() throws Exception{
       String payload="[\n" +
               "    {\n" +
               "        \"id\": 121,\n" +
               "        \"name\": \"David\",\n" +
               "        \"address\": \"Giessen\"\n" +
               "    },\n" +
               "    {\n" +
               "        \"id\": 161,\n" +
               "        \"name\": \"Ingo\",\n" +
               "        \"address\": \"Giessen\"\n" +
               "    },\n" +
               "    {\n" +
               "        \"id\": 61,\n" +
               "        \"name\": \"Jannik\",\n" +
               "        \"address\": \"Darmstadt\"\n" +
               "    },\n" +
               "    {\n" +
               "        \"id\": 101,\n" +
               "        \"name\": \"Julian\",\n" +
               "        \"address\": \"Giessen\"\n" +
               "    },\n" +
               "    {\n" +
               "        \"id\": 123,\n" +
               "        \"name\": \"Karl\",\n" +
               "        \"address\": \"Freiburg\"\n" +
               "    },\n" +
               "    {\n" +
               "        \"id\": 122,\n" +
               "        \"name\": \"Manfred\",\n" +
               "        \"address\": \"Marburg\"\n" +
               "    },\n" +
               "    {\n" +
               "        \"id\": 64,\n" +
               "        \"name\": \"Mohamed\",\n" +
               "        \"address\": \"Darmstadt\"\n" +
               "    }\n" +
               "]";
       mockMvc.perform(MockMvcRequestBuilders.get("/getBorrowers").content(payload)
               .contentType(MediaType.APPLICATION_JSON))
               .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$",Matchers.hasSize(7))).andReturn();


   }
}
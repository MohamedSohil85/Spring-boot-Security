package com.example.demo.controller;

import com.example.demo.entity.Borrower;
import com.example.demo.repository.BorrowerRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class BorrowerControllerUT {

    @InjectMocks
    BorrowerRestController borrowerRestController;
    @Mock
    BorrowerRepository borrowerRepository;

    Borrower mockBorrower= Mockito.mock(Borrower.class);


    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void saveBorrower() {
        List<Borrower>borrowers= new ArrayList<>();
        borrowers.add(new Borrower(61L,"Mohamed","Darmstadt"));

        mockBorrower=new Borrower(61L,"Mohamed","Darmstadt");
        Mockito.when(borrowerRestController.saveBorrower(mockBorrower)).thenReturn(new ResponseEntity(HttpStatus.CREATED));



    }

    @Test
    public void getBorrowers() {
        List<Borrower>borrowers= Arrays.asList(new Borrower(61L,"Mohamed","Darmstadt"));
        mockBorrower=new Borrower(61L,"Mohamed","Darmstadt");

        Mockito.when(borrowerRestController.getBorrowers()).thenReturn(borrowers);

        assertEquals(borrowerRepository.findAll(),borrowers);
    }

    @Test
    public void getBorrowerbyKeyword() {
        List<Borrower>borrowers= Arrays.asList(new Borrower(61L,"Mohamed","Darmstadt"));
        mockBorrower=new Borrower(61L,"Mohamed","Darmstadt");
        Mockito.when(borrowerRestController.getBorrowerbyKeyword(mockBorrower.getName())).thenReturn(borrowers);
        assertEquals(borrowerRepository.findBorrowerByNameContaining(mockBorrower.getName()),borrowers);
    }



}
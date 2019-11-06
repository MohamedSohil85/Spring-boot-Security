package com.example.demo.repository;

import com.example.demo.entity.Borrower;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;




@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest


@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)


public class BorrowerRepositoryTest {
   @Autowired
   private TestEntityManager testEntityManager;
   @Autowired
   private BorrowerRepository borrowerRepository;
    @Test
    public void getAllBorrower() {

        List<Borrower>allBorrower= (List<Borrower>) borrowerRepository.findAll();
        Assertions.assertThat(allBorrower).hasSize(7);

    }
    @Test
    public void testSaveBorrower(){
        Borrower borrower=new Borrower();
        borrower.setAddress("Darmstadt");
        borrower.setName("Ahmed");
        Borrower borrower1=borrowerRepository.save(borrower);
        Assertions.assertThat(borrower).isEqualTo(borrower1);
    }
    @Test
    public void createBorrower(){
        Borrower borrower=new Borrower();
        borrower.setAddress("Giessen");
        borrower.setName("Ingo");
        Borrower saveinDB=testEntityManager.persist(borrower);
       Borrower getfromDB=borrowerRepository.save(borrower);
        Assertions.assertThat(saveinDB).isEqualTo(getfromDB);
    }
    @Test
    public void getBorrowers(){
        Borrower borrower=new Borrower();
        borrower.setAddress("Giessen");
        borrower.setName("Ingo");

        Borrower borrower2=new Borrower();
        borrower2.setAddress("Marburg");
        borrower2.setName("Daniel");

        Borrower saveinDB1=testEntityManager.persist(borrower);
        Borrower saveinDB2=testEntityManager.persist(borrower2);
        List<Borrower>borrowers=(List<Borrower>) borrowerRepository.findAll();
        Assertions.assertThat(borrowers).hasSize(9);
    }
    @Test
    public void testfindBorrowerByName(){

        Borrower borrower=new Borrower();
        borrower.setAddress("Giessen");
        borrower.setName("Ingo");
        borrower.setId(Long.valueOf(161));
        List<Borrower>borrowerList=new ArrayList<>();
        borrowerList.add(borrower);
      List<Borrower> getfromDB=borrowerRepository.findBorrowerByNameContaining("I");
      assertThat(getfromDB,hasSize(1));
    }

}
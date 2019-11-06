package com.example.demo.repository;

import com.example.demo.entity.Borrower;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowerRepository extends CrudRepository<Borrower,Long> {
    @Query(value = "select BORROWER.NAME,BORROWER.ID,BORROWER.ADDRESS from BOOK inner join BORROWER on BOOK.BORROWER_ID = BORROWER.ID where BORROWER.NAME =:NAME",nativeQuery = true)
    public Borrower getBorrowerByName(@Param("NAME")String name);


    @Query(value = "select * from BORROWER where BORROWER.NAME LIKE CONCAT(:keyword,'%')  ",nativeQuery = true)
    public List<Borrower> findBorrowerByNameContaining(@Param("keyword") String keyword);

    @Query(value = "select * from BORROWER ORDER BY BORROWER.NAME",nativeQuery = true)
    public List<Borrower> getBorrowersSortedByName();

}

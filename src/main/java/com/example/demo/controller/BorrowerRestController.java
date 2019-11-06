package com.example.demo.controller;

import com.example.demo.entity.Borrower;
import com.example.demo.exception.ResourceNotFound;
import com.example.demo.repository.BorrowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BorrowerRestController {

    @Autowired
    BorrowerRepository borrowerRepository;

    @RequestMapping(value = "/addBorrower",produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    public ResponseEntity saveBorrower(@RequestBody Borrower borrower){

         return new ResponseEntity(borrowerRepository.save(borrower),HttpStatus.CREATED);


    }
    @RequestMapping(value = "/getBorrowers",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Borrower> getBorrowers(){
        return (List)borrowerRepository.findAll();
    }

    @RequestMapping(value = "/findBorrowerByKeyword/{keyword}",produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    public List<Borrower>getBorrowerbyKeyword(@PathVariable("keyword")String keyword) {
        return borrowerRepository.findBorrowerByNameContaining(keyword);
    }
    @RequestMapping(value = "/deleteBorrowerById/{id}",produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.DELETE)
    public ResponseEntity deleteBorrowerById(@PathVariable("id")Long id) throws ResourceNotFound{
        return borrowerRepository.findById(id).map(b->{
             borrowerRepository.delete(b);
             return new ResponseEntity(HttpStatus.OK);
        }).orElseThrow(()->new ResourceNotFound("Borrower not found !"));
    }

    @RequestMapping(value = "/updateBorrowerbyId/{id}",method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_VALUE)
    public Borrower changeBorrowerById(@PathVariable("id")Long id,@RequestBody Borrower newBorrower) throws ResourceNotFound{
        return borrowerRepository.findById(id).map(b->{
            b.setName(newBorrower.getName());
            b.setAddress(newBorrower.getAddress());
            return borrowerRepository.save(b);
        }).orElseThrow(()->new ResourceNotFound("Borrower not found !"));
    }
@RequestMapping(value = "/getBorrowerssortedByName",produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    public List<Borrower>getBorrowerssorted(){
        return borrowerRepository.getBorrowersSortedByName();
}
@RequestMapping(value = "/getListofBorrowers",produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
public List<Borrower>sortListByName(){
    Comparator<Borrower>borrowerComparator=Comparator
            .comparing(Borrower::getName)
            .thenComparing(Borrower::getAddress);
        return getBorrowers().stream().sorted(borrowerComparator).collect(Collectors.toList());
}
}

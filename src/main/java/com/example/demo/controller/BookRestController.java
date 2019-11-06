package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.entity.Borrower;
import com.example.demo.exception.ResourceNotFound;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.BorrowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookRestController {

@Autowired
    BookRepository bookRepository;
@Autowired
    BorrowerRepository borrowerRepository;

@RequestMapping(value = "/getBooks",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List getallBooks(){
    return (List)bookRepository.findAll();
}
@RequestMapping(value = "/saveBook",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addnewBook(@RequestBody  Book book){

    return new ResponseEntity(bookRepository.save(book), HttpStatus.CREATED);
}
@RequestMapping(value = "/getBookByAuthor/{name}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Book>getBookByAuthorName(@PathVariable("name")String name){
    return bookRepository.getBookByAuthor(name);
}
@RequestMapping(value = "/findBookById/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getBookById(@PathVariable("id") Long id){
    Optional<Book>bookOptional=bookRepository.findById(id);
    if( !bookOptional.isPresent()){
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity(bookOptional.get(),HttpStatus.FOUND);
}
@RequestMapping(value = "/deleteBookById/{id}",method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteBookById(@PathVariable("id")Long id){
   return bookRepository.findById(id).map(book -> {
        bookRepository.delete(book);
       return new ResponseEntity(HttpStatus.OK);
   }).orElse(null);
}
@RequestMapping(value = "/addBorrowerToBook/{id}/addBook",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Book saveBorrowerBook(@PathVariable("id")Long id,@RequestBody Book book) throws ResourceNotFound {
    return borrowerRepository.findById(id).map(borrower -> {
        book.setBorrower(borrower);
        return bookRepository.save(book);
    }).orElseThrow(()-> new ResourceNotFound("not found !"));
}
    @RequestMapping(value = "/getBorrowerByTitle/{title}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List getBorrowerByBookTitle(@PathVariable("title")String title){
        return bookRepository.getBorrowerByBookName(title);
    }
    @RequestMapping(value="/addBorrowerBook/{name}/addmyBook",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Book createBook(@PathVariable("name")String name,@RequestBody Book book)throws ResourceNotFound{
      Borrower borrower=borrowerRepository.getBorrowerByName(name);

      book.setBorrower(borrower);
      return bookRepository.save(book);
    }

}

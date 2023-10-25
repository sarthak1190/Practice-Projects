package com.api.book.bootrestbook.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.book.bootrestbook.Entities.Book;
import com.api.book.bootrestbook.Services.BookService;

@RestController
public class BookController {
    

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks(){
        List<Book> list=bookService.getAllBooks();
        if(list.size()<=0){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(list);
    }

    //get single book handler
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") int id){
        Book book=bookService.getBookById(id);
        if(book==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book);
    } 

    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        try{
           this.bookService.addBook(book);
           return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") int id){
        try{
            this.bookService.deleteBook(id);
            return ResponseEntity.noContent().build();
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book,@PathVariable("id") int id){
        try{
            this.bookService.updateBook(book,id);
            return ResponseEntity.ok().build();
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}

package com.api.book.bootrestbook.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.book.bootrestbook.Dao.BookRepo;
import com.api.book.bootrestbook.Entities.Book;

@Component
public class BookService {
    
    // private static List<Book> list=new ArrayList<>();

    // static{
    //     list.add(new Book(1,"Java reference book","Sarthak Mehta"));
    //     list.add(new Book(2,"Head first in Java","Sid Sid"));
    //     list.add(new Book(3,"Think in Java","Sidharth Sid"));
    // }

    // //get all books
    // public List<Book> getAllBooks(){
    //     return list;
    // }

    // //get book by id
    // public Book getBookById(int id){
    //     Book book=null;
    //     try{
    //         book = list.stream().filter(b -> b.getId() == id).findFirst().get();
    //     }
    //     catch(Exception e){
    //         e.printStackTrace();
    //     }
    //     return book;
    // }

    // //adding a book
    // public Book addBook(Book b){
    //     list.add(b);
    //     return b;
    // }

    // //delete a book
    // public void deleteBook(int id){
    //     list=list.stream().filter(e->e.getId()!=id).collect(Collectors.toList());
    // }

    // //update the book
    // public void updateBook(Book book, int id) {
    //     list.stream().map(e->{
    //         if(e.getId()==id){
    //             e.setTitle(book.getTitle());
    //             e.setAuthor(book.getAuthor());
    //         }
    //         return e;
    //     }).collect(Collectors.toList());
    // }

    @Autowired
    private BookRepo bookRepo;

    //get all books
    public List<Book> getAllBooks(){
        List<Book> list=this.bookRepo.findAll();
        return list;
    }

    //get book by id
    public Book getBookById(int id){
        Book book=null;
        try{
            book=this.bookRepo.findById(id);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return book;
    }

    //adding a book
    public Book addBook(Book b){
        Book result=bookRepo.save(b);
        return result;
    }

    //delete a book
    public void deleteBook(int id){
        bookRepo.deleteById(id);
    }

    //update a book
    public void updateBook(Book newBook, int id){
        newBook.setId(id);
        bookRepo.save(newBook);
    }
}

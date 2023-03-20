package com.example.springREST.controller;

import com.example.springREST.entity.Book;
import com.example.springREST.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BookController {
    @Autowired
    BookRepository bookrepository;

    @GetMapping("/books")
    public List<Book> getAllBooks(){
        return bookrepository.findAll();
    }

    @GetMapping("books/{id}")
        public Book show(@PathVariable Long id){
        return bookrepository.findById(id).get();
    }

    @PostMapping("books/search")
        public List<Book> search(@RequestBody Map<String, String> body){
            String searchTerm = body.get("text");
            return bookrepository.findByTitleContainingOrDescriptionContaining(searchTerm, searchTerm);
    }

    @PostMapping("/books")
    public Book create(@RequestBody Book book){
        return bookrepository.save(book);
    }

    @PutMapping("/books/{id}")
        public Book update(@PathVariable Long id, @RequestBody Book book){
        Book bookToUpdate = bookrepository.findById(id).get();
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setDescription(book.getDescription());
        return bookrepository.save(bookToUpdate);
    }

    @DeleteMapping("books/{id}")
    public boolean delete(@PathVariable Long id){
        bookrepository.deleteById(id);
        return true;
    }
}

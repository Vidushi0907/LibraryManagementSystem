
package com.example.librarymanagementsystem;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    // Store books using ArrayList
    private List<Book> books = new ArrayList<>();

    // b) Add a new book
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {

        books.add(book);

        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    // c) Retrieve all books
    @GetMapping
    public List<Book> getAllBooks() {
        return books;
    }

    // Retrieve book by ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Integer id) {

        for (Book book : books) {
            if (book.getId().equals(id)) {
                return ResponseEntity.ok(book);
            }
        }

        return ResponseEntity.notFound().build();
    }

    // d) Update book details
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(
            @PathVariable Integer id,
            @RequestBody Book updatedBook) {

        for (Book book : books) {

            if (book.getId().equals(id)) {

                book.setTitle(updatedBook.getTitle());
                book.setAuthor(updatedBook.getAuthor());
                book.setCategory(updatedBook.getCategory());
                book.setAvailableCopies(updatedBook.getAvailableCopies());

                return ResponseEntity.ok(book);
            }
        }

        return ResponseEntity.notFound().build();
    }

    // e) Delete book by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Integer id) {

        boolean removed = books.removeIf(
                book -> book.getId().equals(id)
        );

        if (removed) {
            return ResponseEntity.ok("Book deleted successfully");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Book not found");
    }
}

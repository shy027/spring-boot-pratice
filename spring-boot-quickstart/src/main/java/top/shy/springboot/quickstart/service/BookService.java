package top.shy.springboot.quickstart.service;

import org.springframework.stereotype.Service;
import top.shy.springboot.quickstart.dto.BookDTO;

import java.util.List;

@Service
public class BookService {
    public List<BookDTO> getAllBooks() {
        return List.of(
                new BookDTO(1L, "java Programming", "Alice", 29.99)
        );
    }
}

package br.com.tqi.bookstore.controller.mapper;

import br.com.tqi.bookstore.controller.dto.BookCreateDTO;
import br.com.tqi.bookstore.controller.dto.BookDTO;
import br.com.tqi.bookstore.model.Book;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public BookDTO toBookDTO(Book book){
        return MODEL_MAPPER.map(book, BookDTO.class);
    }
    public List<BookDTO> toBookDTOList(List<Book> bookList) {
        return bookList.stream().map(this::toBookDTO).collect(Collectors.toList());
    }
    public Book toBook(BookDTO dto) {
        return MODEL_MAPPER.map(dto,Book.class);
    }
    public Book toBookCreate(BookCreateDTO dto){
        return MODEL_MAPPER.map(dto, Book.class);
    }
}

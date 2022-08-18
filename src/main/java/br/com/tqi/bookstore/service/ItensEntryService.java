package br.com.tqi.bookstore.service;


import br.com.tqi.bookstore.exception.BookQuantityNotEnougthToReverseException;
import br.com.tqi.bookstore.exception.IdNotFoundException;
import br.com.tqi.bookstore.exception.NameAlreadyRegisteredException;
import br.com.tqi.bookstore.model.Author;
import br.com.tqi.bookstore.model.Book;
import br.com.tqi.bookstore.model.ItensEntry;
import br.com.tqi.bookstore.repository.ItensEntryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ItensEntryService {

    private final ItensEntryRepository itensEntryRepository;

    private final BookService bookService;

    public ItensEntryService(ItensEntryRepository itensEntryRepository, BookService bookService) {
        this.itensEntryRepository = itensEntryRepository;
        this.bookService = bookService;
      }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    @Transactional(readOnly = true)
    public List<ItensEntry> findAll() {
        return itensEntryRepository.findAll();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public ItensEntry findById(String id) {
        return itensEntryRepository.findById(id).orElseThrow(() -> new IdNotFoundException(id)); //Find by id retorna um optinal
    }

    @Transactional
    public ItensEntry create(ItensEntry itensEntryCreate, String bookId) {
        String uuid = getUUID();
        Book book = bookService.findById(bookId);
        itensEntryCreate.setId(uuid);
        itensEntryCreate.setBook(bookId);
        itensEntryCreate.setDate(LocalDateTime.now());
        itensEntryRepository.save(itensEntryCreate);
        addEntryOnBook(itensEntryCreate, book);
        return itensEntryCreate;
    }

    private void addEntryOnBook(ItensEntry itensEntry, Book book) {
        List<ItensEntry> itensEntryList = book.getItensEntry();
        book.setQuantity(book.getQuantity()+ itensEntry.getQuantity());
        book.setPrice(itensEntry.getPrice());
        itensEntryList.add(itensEntry);
        book.setItensEntry(itensEntryList);
        bookService.update(book.getId(), book);
    }

    @Transactional
    public void reverseAndDelete(String id) throws BookQuantityNotEnougthToReverseException{
        ItensEntry itensEntry = findById(id);
        Book book = bookService.findById(itensEntry.getBook());
        if (book.getQuantity() < itensEntry.getQuantity()){
            new BookQuantityNotEnougthToReverseException();
        }else{
            book.setQuantity(book.getQuantity() - itensEntry.getQuantity());
        }
        itensEntryRepository.deleteById(id);
    }
}

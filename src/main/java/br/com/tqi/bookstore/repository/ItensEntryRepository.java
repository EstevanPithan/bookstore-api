package br.com.tqi.bookstore.repository;

import br.com.tqi.bookstore.model.Book;
import br.com.tqi.bookstore.model.ItensEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItensEntryRepository extends JpaRepository<ItensEntry, String> {
}

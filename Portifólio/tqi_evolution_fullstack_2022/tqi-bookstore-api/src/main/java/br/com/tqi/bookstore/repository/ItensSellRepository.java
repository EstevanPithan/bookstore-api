package br.com.tqi.bookstore.repository;

import br.com.tqi.bookstore.model.Author;
import br.com.tqi.bookstore.model.Book;
import br.com.tqi.bookstore.model.ItensSell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItensSellRepository extends JpaRepository<ItensSell, String> {
}

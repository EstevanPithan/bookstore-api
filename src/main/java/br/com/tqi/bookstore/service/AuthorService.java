package br.com.tqi.bookstore.service;

import br.com.tqi.bookstore.exception.AuthorCantBeDeleteException;
import br.com.tqi.bookstore.exception.NameAlreadyRegisteredException;
import br.com.tqi.bookstore.exception.IdNotFoundException;
import br.com.tqi.bookstore.model.Author;
import br.com.tqi.bookstore.model.Book;
import br.com.tqi.bookstore.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    @Transactional(readOnly = true)
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Author findById(String id) {
        return authorRepository.findById(id).orElseThrow(() -> new IdNotFoundException(id)); //Find by id retorna um optinal
    }

    @Transactional
    public Author create(Author authorCreate) throws NameAlreadyRegisteredException {
        verifyIfIsAlreadyRegistered(authorCreate.getName());
        String uuid = getUUID();
        authorCreate.setId(uuid);
        authorRepository.save(authorCreate);
        return authorCreate;
    }

    @Transactional
    public void delete(String id) throws AuthorCantBeDeleteException {
        Author author = findById(id);
//        verifyIfIsAuthorIsLinked(author);
        authorRepository.deleteById(id);
    }

    @Transactional
    public Author update(String id, Author authorUpdate) {
        Author author = findById(id);
        author.setName(authorUpdate.getName());
        author.setDescription(authorUpdate.getDescription());
        author.setImage(authorUpdate.getImage());
        author.setBook(authorUpdate.getBook());
        authorRepository.save(author);
        return author;
    }

    public List<Book> getBooksByAuthor(String authorId){
        Author author = findById(authorId);
        List<Book> bookList = author.getBook();
        return bookList;
    }



    private void verifyIfIsAlreadyRegistered(String name) throws NameAlreadyRegisteredException {
        Optional<Author> optionalAuthor = authorRepository.findByName(name);
        if (optionalAuthor.isPresent()) {
            throw new NameAlreadyRegisteredException(name);
        }
    }



//    private void verifyIfIsAuthorIsLinked(Author author) throws AuthorCantBeDeleteException {
//        if ((author.getBooks().isEmpty())) {
//            System.out.println("entrou na exception");
//            throw new AuthorCantBeDeleteException();
//        }
//
//    }

}

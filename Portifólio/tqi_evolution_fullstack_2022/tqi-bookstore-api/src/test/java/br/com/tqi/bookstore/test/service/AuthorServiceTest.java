//package br.com.tqi.bookstore.test.service;
//
//import br.com.tqi.bookstore.test.builder.AuthorBuilder;
//import br.com.tqi.bookstore.controller.dto.BookDTO;
//import br.com.tqi.bookstore.controller.mapper.AuthorMapper;
//import br.com.tqi.bookstore.controller.mapper.BookMapper;
//import br.com.tqi.bookstore.exception.IdNotFoundException;
//import br.com.tqi.bookstore.exception.NameAlreadyRegisteredException;
//import br.com.tqi.bookstore.model.Author;
//import br.com.tqi.bookstore.repository.AuthorRepository;
//import br.com.tqi.bookstore.service.AuthorService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.*;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.*;
//import static org.mockito.Mockito.times;
//
//@ExtendWith(MockitoExtension.class)
//public class AuthorServiceTest {
//
//    @Mock
//    private AuthorRepository authorRepository;
//    private AuthorMapper authorMapper;
//    @Mock
//    private BookMapper bookMapper;
//
//    @InjectMocks
//    private AuthorService authorService;
//
//    @Test
//    void whenAuthorInformedThenItShouldBeCreated() throws NameAlreadyRegisteredException {
//        Author expectedSavedAuthor = AuthorBuilder.builder().build().toAuthor();
//        String firstId = expectedSavedAuthor.getId();
//
//        when(authorRepository.findByName(expectedSavedAuthor.getName())).thenReturn(Optional.empty());
//
//        Author createdAuthor = authorService.create(expectedSavedAuthor);
//
//        assertThat(createdAuthor.getId(), not(equalTo(firstId)));
//        assertThat(createdAuthor.getName(), is(equalTo(expectedSavedAuthor.getName())));
//        assertThat(createdAuthor.getBirthday(), is(equalTo(expectedSavedAuthor.getBirthday())));
//    }
//
//    @Test
//    void whenNotRegisteredAuthorNameIsGivenThenThrowAnException() throws IdNotFoundException {
//        Author expectedSavedAuthor = AuthorBuilder.builder().build().toAuthor();
//
//        when(authorRepository.findById(expectedSavedAuthor.getId())).thenReturn(Optional.empty());
//
//        assertThrows(IdNotFoundException.class, () -> authorService.findById(expectedSavedAuthor.getId()));
//    }
//
//    @Test
//    void whenValidAuthorIdIsGivenThenReturnAAuthor() throws NameAlreadyRegisteredException, IdNotFoundException {
//        Author expectedSavedAuthor = AuthorBuilder.builder().build().toAuthor();
//        Author createdAuthor = authorService.create(expectedSavedAuthor);
//
//        when(authorRepository.findById(expectedSavedAuthor.getId())).thenReturn(Optional.of(createdAuthor));
//
//        assertThat(createdAuthor, is(equalTo(authorService.findById(createdAuthor.getId()))));
//    }
//
//    @Test
//    void whenListAuthorIsCalledThenReturnAListOfAuthors() {
//        Author expectedSavedAuthor = AuthorBuilder.builder().build().toAuthor();
//
//        when(authorRepository.findAll()).thenReturn(Collections.singletonList(expectedSavedAuthor));
//
//        List<Author> foundListAuthors = authorService.findAll();
//
//        assertThat(foundListAuthors, is(not(empty())));
//        assertThat(foundListAuthors.get(0), is(equalTo(expectedSavedAuthor)));
//    }
//
//    @Test
//    void whenDeleteIsCalledWithValidIdThenAAuthorShouldBeDeleted() throws IdNotFoundException {
//        Author expectedDeletedAuthor = AuthorBuilder.builder().build().toAuthor();
//        String idAuthor = "123456";
//
//        when(authorRepository.findById(idAuthor)).thenReturn(Optional.of(expectedDeletedAuthor));
//        doNothing().when(authorRepository).deleteById(idAuthor);
//
//        authorService.delete(idAuthor);
//
//        verify(authorRepository, times(1)).findById(idAuthor);
//        verify(authorRepository, times(1)).deleteById(idAuthor);
//    }
//
//    @Test
//    void whenUpdateCalledThanReturnAAuthorUpdated() throws IdNotFoundException {
//        Author expectedUpdatedAuthor = AuthorBuilder.builder().build().toAuthor();
//        Author update = new Author("567890", "author2", "15/11/1995", new ArrayList<>());
//
//        when(authorRepository.findById(expectedUpdatedAuthor.getId())).thenReturn(Optional.of(expectedUpdatedAuthor));
//
//        authorService.update(expectedUpdatedAuthor.getId(),update);
//
//        assertThat(expectedUpdatedAuthor.getName(), is(equalTo(update.getName())));
//    }
//
//    @Test
//    void whenGetBooksByAuthorIsCalledThanReturnAListOfBooks() throws IdNotFoundException {
//        Author author = AuthorBuilder.builder().build().toAuthor();
//
//        when(authorRepository.findById(author.getId())).thenReturn(Optional.of(author));
//        doReturn(author.getBook()).when(bookMapper).toBookDTOList(author.getBook());
//
//        List<BookDTO> bookDTO = authorService.getBooksByAuthor(author.getId());
//
//        assertThat(author.getBook(), is(equalTo(bookDTO)));
//    }
//
//}

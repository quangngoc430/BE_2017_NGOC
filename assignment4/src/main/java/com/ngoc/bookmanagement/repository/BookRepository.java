package com.ngoc.bookmanagement.repository;

import com.ngoc.bookmanagement.model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> getAllByEnabled(boolean enabled);

    List<Book> getAllByUserId(long userId);

    @Query("FROM Book book WHERE book.userId = :userId AND (book.author LIKE :author OR book.title LIKE :title)")
    List<Book> getAllByUserIdAndAuthorLikeOrTitleLike(@Param("userId") long userId,
                                                      @Param("author") String author,
                                                      @Param("title") String title);

    List<Book> getAllByUserIdEqualsAndAuthorLikeOrTitleLike(long userId, String author, String title);

    List<Book> getAllByUserIdAndEnabled(long userId, boolean enabled);

    List<Book> getAllByAuthorLikeOrTitleLike(String author, String title);
}

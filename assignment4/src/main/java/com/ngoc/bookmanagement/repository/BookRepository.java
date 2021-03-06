package com.ngoc.bookmanagement.repository;

import com.ngoc.bookmanagement.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {

    @Query("FROM Book book WHERE book.enabled = :enabled AND (book.author LIKE :wordsSearch OR book.title LIKE :wordsSearch)")
    Page<Book> getAllByEnabledAndAuthorLikeOrTitleLike(@Param("wordsSearch") String wordsSearch, 
                                                       @Param("enabled") boolean enabled, 
                                                       Pageable pageable);

    Page<Book> getAllByUserId(long userId, Pageable pageable);

    @Query("FROM Book book WHERE book.userId = :userId AND (book.author LIKE :author OR book.title LIKE :title)")
    Page<Book> getAllByUserIdAndAuthorLikeOrTitleLike(@Param("userId") long userId,
                                                      @Param("author") String author,
                                                      @Param("title") String title,
                                                      Pageable pageable);

    @Query("FROM Book book WHERE book.userId = :userId AND book.enabled = :enabled AND (book.author LIKE :author OR book.title LIKE :title)")
    Page<Book> getAllByUserIdAndEnabledAndAuthorLikeOrTitleIsLike(@Param("userId") long userId,
                                                                  @Param("author") String author,
                                                                  @Param("title") String title,
                                                                  @Param("enabled") boolean enabled,
                                                                  Pageable pageable);

    @Query("FROM Book book WHERE (book.enabled = true OR book.userId = :userId) AND (book.author LIKE :author OR book.title LIKE :title)")
    Page<Book> getAllByEnabledTrueAndUserIdAndEnabledDisable(@Param("userId") long userId,
                                                             @Param("author") String author,
                                                             @Param("title") String title,
                                                             Pageable pageable);

    Page<Book> getAllByUserIdAndEnabled(long userId, boolean enabled, Pageable pageable);

    Page<Book> getAllByAuthorLikeOrTitleLike(String author, String title, Pageable pageable);

    @Modifying 
    @Transactional
    @Query("DELETE FROM Book WHERE id = :bookId")
    void deleteById(@Param("bookId") long bookId);
}

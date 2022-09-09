package hello.hellospring.tddtest.repo;

import hello.hellospring.tddtest.domain.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach // 각 테스트 시작전에 한 번씩 실행
    public void 데이터준비() {
        String title = "junit";
        String author = "김성찬";
        Book book = Book.builder()
                .title(title)
                .author(author)
                .build();
       bookRepository.save(book);
    }

    // 1. 책 등록
    @Test
    public void save_test() {
        String title = "테스트의 정석";
        String author = "홍찬영";
        Book book = Book.builder()
                .title(title)
                .author(author)
                .build();

        // when (테스트 실행)
        Book bookPs = bookRepository.save(book);

        // then (검증)
        assertEquals(title, bookPs.getTitle());
        assertEquals(author, bookPs.getAuthor());

    } // 트랜잭션 종료 (저장된 데이터를 초기화함)

    // 2. 책 목록보기
    @Test
    public void 책목록_test() {
        // given
        String title = "junit";
        String author = "김성찬";
        
        // when
        List<Book> books = bookRepository.findAll();
        // then
        assertEquals(title, books.get(0).getTitle());
        assertEquals(author, books.get(0).getAuthor());
    }

    // 3. 책 한건보기
    @Test
    public void 책한건보기_test() {

        String title = "junit";
        String author = "김성찬";

        Book book1 = bookRepository.findById(1L).get();

        assertEquals(title, book1.getTitle());
        assertEquals(author, book1.getAuthor());

    }
}
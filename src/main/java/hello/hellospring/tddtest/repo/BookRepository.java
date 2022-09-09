package hello.hellospring.tddtest.repo;

import hello.hellospring.tddtest.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}

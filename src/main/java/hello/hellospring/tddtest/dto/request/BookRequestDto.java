package hello.hellospring.tddtest.dto.request;

import hello.hellospring.tddtest.domain.Book;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
public class BookRequestDto {
    private String title;
    private String author;

    public Book toEntity(){
        return Book.builder()
                .title(title)
                .author(author)
                .build();
    }
}

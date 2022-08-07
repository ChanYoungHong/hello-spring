package hello.hellospring.security.entity;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@Getter
@EntityListeners(value = {AuditingEntityListener.class})
abstract public class BaseEntity {
}

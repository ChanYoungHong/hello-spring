package hello.hellospring.security.repo;

import hello.hellospring.security.entity.ClubMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubMemberRepo extends JpaRepository<ClubMember, String> {

}

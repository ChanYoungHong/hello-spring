package hello.hellospring.security.repo;

import hello.hellospring.entity.ClubMember;
import hello.hellospring.repo.ClubMemberRepo;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class ClubMemberRepoTest {

    @Autowired
    private ClubMemberRepo clubMemberRepo;

    @Test
    void findByEmail() {

        Optional<ClubMember> result = clubMemberRepo.findByEmail("user95@naver.com", false);

        ClubMember clubMember = result.get();
        System.out.println(clubMember);
    }
}
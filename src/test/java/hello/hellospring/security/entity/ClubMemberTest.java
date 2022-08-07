package hello.hellospring.security.entity;

import static org.junit.jupiter.api.Assertions.*;

import hello.hellospring.security.repo.ClubMemberRepo;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class ClubMemberTest {

    @Autowired
    private ClubMemberRepo clubMemberRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertDummies() {

        IntStream.rangeClosed(1, 100).forEach(i -> {
            ClubMember clubMember = ClubMember.builder()
                .email("user" + i + "@zerock.org")
                .name("사용자" + i)
                .fromSocial(false)
                .password(passwordEncoder.encode("1111"))
                .build();

            // default role
            clubMember.addMemberRole(ClubMemberRole.USER);
            if(i > 80) {
                clubMember.addMemberRole(ClubMemberRole.MANAGER);
            }

            if(i > 90) {
                clubMember.addMemberRole(ClubMemberRole.ADMIN);
            }
            clubMemberRepo.save(clubMember);
        });

    }

}
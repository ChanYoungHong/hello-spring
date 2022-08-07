package hello.hellospring.security.entity;

import hello.hellospring.entity.ClubMember;
import hello.hellospring.entity.ClubMemberRole;
import hello.hellospring.repo.ClubMemberRepo;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class ClubMemberTests {

    @Autowired
    private ClubMemberRepo clubMemberRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertDummies() {

        IntStream.rangeClosed(1, 100).forEach(i -> {
            ClubMember clubMember = ClubMember.builder()
                .email("user" + i + "@naver.com")
                .name("사용자" + i)
                .password(passwordEncoder.encode("1111"))
                .fromSocial(false)
                .build();


            clubMember.addMemberRole(ClubMemberRole.USER);

            if(i > 80){
                    clubMember.addMemberRole(ClubMemberRole.MANAGER);
            }

            if(i > 90){
                    clubMember.addMemberRole(ClubMemberRole.ADMIN);
            }

            clubMemberRepo.save(clubMember);
        });
    }
}

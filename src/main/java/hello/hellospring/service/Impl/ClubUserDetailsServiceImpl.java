package hello.hellospring.service.Impl;

import hello.hellospring.entity.ClubMember;
import hello.hellospring.repo.ClubMemberRepo;
import hello.hellospring.dto.ClubAuthMemberDTO;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class ClubUserDetailsServiceImpl implements UserDetailsService {

    private final ClubMemberRepo clubMemberRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername" + username);

        Optional<ClubMember> result = clubMemberRepo.findByEmail(username, false);

        if (result.isPresent()) {
            throw new UsernameNotFoundException("Check Email or Social");
        }

        ClubMember clubMember = result.get();

        log.info("========================");
        log.info(clubMember);

        // UserDetails 타입으로 처리하기 위해서 ClubAuthMemberDTO 타입으로 반환.
        ClubAuthMemberDTO clubAuthMember = new ClubAuthMemberDTO(
            clubMember.getEmail(),
            clubMember.getPassword(),
            clubMember.isFromSocial(),
            clubMember.getRoleSet().stream()
                .map(role -> new SimpleGrantedAuthority
                    ("ROLE_" + role.name())).collect(Collectors.toSet())
        );

        clubAuthMember.setName(clubAuthMember.getName());
        clubAuthMember.setFromSocial(clubMember.isFromSocial());

        return clubAuthMember;
    }
}

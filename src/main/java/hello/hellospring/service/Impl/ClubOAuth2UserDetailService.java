package hello.hellospring.service.Impl;


import hello.hellospring.entity.ClubMember;
import hello.hellospring.entity.ClubMemberRole;
import hello.hellospring.repo.ClubMemberRepo;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class ClubOAuth2UserDetailService extends DefaultOAuth2UserService {

    private final ClubMemberRepo clubMemberRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        log.info("-----------------------------------");
        log.info("userRequest : " + userRequest);

        String clientName = userRequest.getClientRegistration().getClientName();

        log.info("clientName : " + clientName);
        log.info(userRequest.getAdditionalParameters());

        OAuth2User oAuth2User = super.loadUser(userRequest);

        log.info("=============================");
        oAuth2User.getAttributes().forEach((k, v) -> {
            log.info(k + ":" + v); // sub, picture, email, email_verified, EMAIL
        });

        String email = null;

        if(clientName.equals("Google")) {
            email = oAuth2User.getAttribute("email");
        }

        log.info("EMAIL: " + email);

        ClubMember member = saveSocialMember(email);

        return oAuth2User;
    }

    private ClubMember saveSocialMember(String email) {

        Optional<ClubMember> result = clubMemberRepo.findByEmail(email, true);

        if(result.isPresent()){
            return result.get();
        }

        ClubMember clubMember = ClubMember.builder()
            .name(email)
            .password(passwordEncoder.encode("1111"))
            .fromSocial(true)
            .build();


        clubMember.addMemberRole(ClubMemberRole.USER);
        clubMemberRepo.save(clubMember);

        return clubMember;
    }
}

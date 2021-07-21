package WhenWhenBackEnd.api;

import WhenWhenBackEnd.repository.MemberRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberRepository memberRepository;

    @PostMapping("/api/sign-up")
    public CreateMemberResponse signUpMember(@RequestBody CreateMemberRequest request) {

    }

    @GetMapping 조회용??
    //

    @Data
    static class CreateMemberRequest {

    }

    @Data
    static class CreateMemberResponse {

    }


}

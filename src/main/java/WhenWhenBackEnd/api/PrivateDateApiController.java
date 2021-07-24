package WhenWhenBackEnd.api;

import WhenWhenBackEnd.domain.Availability;
import WhenWhenBackEnd.domain.Member;
import WhenWhenBackEnd.domain.PrivateDate;
import WhenWhenBackEnd.dto.SimpleDateDTO;
import WhenWhenBackEnd.dto.SimpleDateDTO2;
import WhenWhenBackEnd.dto.privatedate.GetMyPrivateScheduleRequestDTO;
import WhenWhenBackEnd.dto.privatedate.GetMyPrivateScheduleResponseDTO;
import WhenWhenBackEnd.dto.privatedate.SetMyPrivateScheduleRequestDTO;
import WhenWhenBackEnd.dto.privatedate.SetMyPrivateScheduleResponseDTO;
import WhenWhenBackEnd.repository.MemberRepository;
import WhenWhenBackEnd.repository.PrivateDateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.scanner.ScannerImpl;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PrivateDateApiController {

    private final MemberRepository memberRepository;
    private final PrivateDateRepository privateDateRepository;

    @PostMapping("/set-my-schedule")
    public SetMyPrivateScheduleResponseDTO setMySchedule(@RequestBody SetMyPrivateScheduleRequestDTO dto) {
       Member member = memberRepository.findByIdToken(dto.getIdToken());

        List<PrivateDate> list = dto.getDates().stream()
                .map(dateDTO -> new PrivateDate(member, dateDTO.getLocalDate(), dateDTO.getAvailability()))
                .collect(Collectors.toList());

        list.stream().forEach(privateDate -> privateDateRepository.save(privateDate));

        return new SetMyPrivateScheduleResponseDTO(member.getIdToken());
    }

    @PostMapping("/get-my-schedule")
    public GetMyPrivateScheduleResponseDTO getMySchedule(@RequestBody GetMyPrivateScheduleRequestDTO dto) {
        Member member = memberRepository.findByIdToken(dto.getIdToken());

        List<PrivateDate> privateDateList = privateDateRepository.findByMember(member);


        List<SimpleDateDTO2> list = privateDateList.stream()
                .map(privateDate -> new SimpleDateDTO2(privateDate.getLocalDate(), privateDate.getAvailability().ordinal()))
                .collect(Collectors.toList());


        return new GetMyPrivateScheduleResponseDTO(member.getIdToken(), list);
    }

}

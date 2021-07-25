package WhenWhenBackEnd.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

// 스케줄
@Entity @Table(name = "_schedule")
@Getter
public class Schedule {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long id;

    // 스케줄 이름 ex) 멋사 프로젝트 일정
    private String name;

    // 스케줄 랜덤 문자열 키
    private String scheduleKey;

    // 주최자
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member host;

    // 스케줄의 시작 날짜, 종료 날짜
    private LocalDate startDate;
    private LocalDate endDate;

    // 호스트가 지정한 인원 수
    private Long expectedMemberCnt;

    // 실제 설문 응시를 완료한 인원 수
    private Long joinedMemberCnt;

    protected Schedule() { }

    public Schedule(String name, Member host, LocalDate startDate, LocalDate endDate, Long expectedMemberCnt) {
        this.name = name;
        this.host = host;
        this.startDate = startDate;
        this.endDate = endDate;
        this.expectedMemberCnt = expectedMemberCnt;
        this.joinedMemberCnt = 0L;

        // 랜덤 스트링 생성
        this.scheduleKey = createRandomString();
    }

    private String createRandomString() {
        int leftLimit = 97; // 'a'
        int rightLimit = 122; // 'z'
        int targetStringLength = 10;

        // 문자열로 이루어진 랜덤 문자열 생성
        String generatedString = new Random().ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        // + 현재 시간을 문자열로 변환해서 추가
        return generatedString.concat(String.valueOf(new Date().getTime()));
    }

    public Long addJoinedMemberCnt() {
        return ++joinedMemberCnt;
    }

    public Long decreaseJoinedMemberCnt() {
        return --joinedMemberCnt;
    }

    public Long increaseExpectedMemberCnt() {
        return ++expectedMemberCnt;
    }

    public Long decreaseExpectedMemberCnt() {
        return --expectedMemberCnt;
    }
}

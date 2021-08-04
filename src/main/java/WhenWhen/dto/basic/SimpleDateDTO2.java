package WhenWhen.dto.basic;

import WhenWhen.domain.Availability;
import lombok.Data;

@Data
public class SimpleDateDTO2 {

    private Long availability;
    private Long possibleCnt;
    private Long adjustableCnt;
    private Long impossibleCnt;

    public SimpleDateDTO2(Long availability, Long possibleCnt, Long adjustableCnt, Long impossibleCnt) {
        this.availability = availability;
        this.possibleCnt = possibleCnt;
        this.adjustableCnt = adjustableCnt;
        this.impossibleCnt = impossibleCnt;
    }

    public void addPossibleCnt() { possibleCnt++; }
    public void addImpossibleCnt() { impossibleCnt++; }
    public void addAdjustableCnt() { adjustableCnt++; }

}

package WhenWhen.service;

import WhenWhen.domain.Member;
import WhenWhen.domain.PrivateDate;
import WhenWhen.repository.PrivateDateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PrivateDateService {

    private final PrivateDateRepository privateDateRepository;

    public void save(PrivateDate privateDate) {
        privateDateRepository.save(privateDate);
    }

    public void saveAll(List<PrivateDate> list) {
        for (PrivateDate privateDate : list) {
            privateDateRepository.save(privateDate);
        }
    }

    public List<PrivateDate> findByMember(Member member) {
        List<PrivateDate> list = privateDateRepository.findByMember(member);

        return list;
    }

    public PrivateDate findOne(Long id) {
        PrivateDate result = privateDateRepository.findById(id);

        return result;
    }

    public void delete(PrivateDate privateDate) {
        privateDateRepository.delete(privateDate);
    }
}

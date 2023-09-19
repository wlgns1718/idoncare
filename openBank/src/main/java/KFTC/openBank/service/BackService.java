package KFTC.openBank.service;


import KFTC.openBank.repository.BankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BackService {

    public final BankRepository bankRepository;

}

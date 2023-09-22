package d209.Idontcare.account.service;

import d209.Idontcare.account.dto.req.TransactionHistoryRes;
import d209.Idontcare.account.entity.TransactionHistory;
import d209.Idontcare.account.exception.TransactionHistoryException;
import d209.Idontcare.account.repository.TransactionHistoryRepository;
import d209.Idontcare.user.entity.User;
import d209.Idontcare.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class TransactionHistoryService {

    private final TransactionHistoryRepository transactionHistoryRepository;
    private final UserService userService;

    //년월별 가상 계좌의 연월별 거래내역 조회
    @Transactional(readOnly = true)
    public List<TransactionHistoryRes> userTransactionHistoryByDate(Long userId, int year, int month){
        List<TransactionHistoryRes> transactionHistoryResDtos = new ArrayList<>();
        for(TransactionHistory trans : transactionHistoryRepository.findTransactionHistory(userId, year, month)){
            transactionHistoryResDtos.add(TransactionHistoryRes.TransactionHistoryToDto(trans));
        }
        if(transactionHistoryResDtos.size() == 0){
            throw new TransactionHistoryException(204, "거래 내역이 없습니다.");
        }
        return transactionHistoryResDtos;
    }
    
    //내용별로 계좌의 거래 내역 조회
    public List<TransactionHistoryRes> userTransactionHistoryByContent(Long userId, String content){
        List<TransactionHistoryRes> transactionHistoryResDtos = new ArrayList<>();
        for(TransactionHistory trans : transactionHistoryRepository.findTransactionHistoryByContent(userId, content)){
            transactionHistoryResDtos.add(TransactionHistoryRes.TransactionHistoryToDto(trans));
        }
        if(transactionHistoryResDtos.size() == 0){
            throw new TransactionHistoryException(204, "거래 내역이 없습니다.");
        }
        return transactionHistoryResDtos;
    }

    //가상 계좌 입출금 시 거래내역 추가
    public void recordTransactionHistory(TransactionHistoryRes trans){
        User user = userService.findByUserId(trans.getUserId()).get();
        TransactionHistory tran = new TransactionHistory().builder()
                                    .user(user)
                                    .content(trans.getContent())
                                    .localDateTime(trans.getLocalDateTime())
                                    .amount(trans.getAmount())
                                    .type(trans.getType())
                                    .balance(trans.getBalance())
                                    .build();
        transactionHistoryRepository.save(tran);
    }
}

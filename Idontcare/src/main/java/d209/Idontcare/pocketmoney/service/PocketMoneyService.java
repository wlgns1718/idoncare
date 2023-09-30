package d209.Idontcare.pocketmoney.service;

import d209.Idontcare.account.exception.VirtualAccountException;
import d209.Idontcare.common.exception.*;
import d209.Idontcare.pocketmoney.dto.req.*;
import d209.Idontcare.pocketmoney.dto.res.*;
import d209.Idontcare.pocketmoney.dto.res.GetRegularPocketMoneysResDto;
import d209.Idontcare.pocketmoney.entity.RegularPocketMoney;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface PocketMoneyService {
  List<GetRegularPocketMoneysResDto> getRegularPocketMoneys(Long parentUserId);
  
  RegularPocketMoney registryRegularPocketMoney(Long parentUserId, RegistRegularPocketMoneyReqDto req, LocalDateTime now)
      throws AuthenticationException, BadRequestException, NoSuchUserException, MustChildException, DuplicatedException;
  
  void sendPocketMoney(Long parentUserId, SendPocketMoneyReqDto req);
  
  void requestPocketMoney(Long childUserId, RequestPocketMoneyReqDto req)
      throws MustParentException, MustChildException;
  
  List<GetPocketMoneyRequestResDto> getPocketMoneyRequest(Long userId);
  
  void processPocketMoneyRequest(Long parentUserId, ProcessPocketMoneyRequestReqDto req)
      throws MustParentException, NoSuchContentException, VirtualAccountException;
}

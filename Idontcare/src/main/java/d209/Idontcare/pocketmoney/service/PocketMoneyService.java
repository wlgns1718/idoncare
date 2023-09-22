package d209.Idontcare.pocketmoney.service;

import d209.Idontcare.account.exception.VirtualAccountException;
import d209.Idontcare.common.exception.*;
import d209.Idontcare.pocketmoney.dto.req.ProcessPocketMoneyRequestReqDto;
import d209.Idontcare.pocketmoney.dto.req.RegistRegularPocketMoneyReqDto;
import d209.Idontcare.pocketmoney.dto.req.RequestPocketMoneyReqDto;
import d209.Idontcare.pocketmoney.dto.req.SendPocketMoneyReqDto;
import d209.Idontcare.pocketmoney.dto.res.GetPocketMoneyRequestResDto;
import d209.Idontcare.pocketmoney.entity.RegularPocketMoney;
import d209.Idontcare.user.entity.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface PocketMoneyService {
  RegularPocketMoney registryRegularPocketMoney(Long parentUserId, RegistRegularPocketMoneyReqDto req, LocalDateTime now)
      throws AuthenticationException, MustParentException, BadRequestException, NoSuchUserException, MustChildException, DuplicatedException;
  
  void sendPocketMoney(Long parentUserId, SendPocketMoneyReqDto req)
      throws MustChildException;
  
  void requestPocketMoney(Long childUserId, RequestPocketMoneyReqDto req)
      throws MustParentException, MustChildException;
  
  List<GetPocketMoneyRequestResDto> getPocketMoneyRequest(Long userId)
      throws MustParentException;
  
  void processPocketMoneyRequest(Long parentUserId, ProcessPocketMoneyRequestReqDto req)
      throws AuthorizationException, MustParentException, NoSuchContentException, VirtualAccountException;
}

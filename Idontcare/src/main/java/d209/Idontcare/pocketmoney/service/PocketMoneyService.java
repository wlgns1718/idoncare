package d209.Idontcare.pocketmoney.service;

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
  RegularPocketMoney registryRegularPocketMoney(User parent, RegistRegularPocketMoneyReqDto req, LocalDateTime now)
      throws AuthenticationException, MustParentException, BadRequestException, NoSuchUserException, MustChildException, DuplicatedException;
  
  void sendPocketMoney(User parent, SendPocketMoneyReqDto req)
      throws MustParentException, MustChildException;
  
  void requestPocketMoney(User child, RequestPocketMoneyReqDto req)
      throws MustParentException, MustChildException;
  
  List<GetPocketMoneyRequestResDto> getPocketMoneyRequest(User parent)
      throws MustParentException;
  
  void processPocketMoneyRequest(User parent, ProcessPocketMoneyRequestReqDto req)
      throws AuthorizationException, MustParentException, NoSuchContentException;
}

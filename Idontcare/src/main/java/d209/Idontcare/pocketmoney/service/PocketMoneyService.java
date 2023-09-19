package d209.Idontcare.pocketmoney.service;

import d209.Idontcare.TUser;
import d209.Idontcare.common.exception.AuthenticationException;
import d209.Idontcare.common.exception.DuplicatedException;
import d209.Idontcare.common.exception.MustChildException;
import d209.Idontcare.common.exception.MustParentException;
import d209.Idontcare.pocketmoney.dto.req.RegistRegularPocketMoneyReqDto;
import d209.Idontcare.pocketmoney.dto.req.RequestPocketMoneyReqDto;
import d209.Idontcare.pocketmoney.dto.req.SendPocketMoneyReqDto;
import d209.Idontcare.pocketmoney.dto.res.GetPocketMoneyRequestResDto;
import d209.Idontcare.pocketmoney.entity.RegularPocketMoney;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface PocketMoneyService {
  RegularPocketMoney registryRegularPocketMoney(TUser parent, RegistRegularPocketMoneyReqDto req, LocalDateTime now)
      throws AuthenticationException, MustParentException, MustChildException, DuplicatedException;
  
  void sendPocketMoney(TUser parent, SendPocketMoneyReqDto req)
      throws MustParentException, MustChildException;
  
  void requestPocketMoney(TUser child, RequestPocketMoneyReqDto req)
      throws MustParentException, MustChildException;
  
  List<GetPocketMoneyRequestResDto> getPocketMoneyRequest(TUser parent)
      throws MustParentException;
}

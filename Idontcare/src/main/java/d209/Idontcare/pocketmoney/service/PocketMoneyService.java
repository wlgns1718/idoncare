package d209.Idontcare.pocketmoney.service;

import d209.Idontcare.common.exception.AuthenticationException;
import d209.Idontcare.common.exception.DuplicatedException;
import d209.Idontcare.common.exception.MustChildException;
import d209.Idontcare.common.exception.MustParentException;
import d209.Idontcare.pocketmoney.dto.req.RegistRegularPocketMoneyReqDto;
import d209.Idontcare.pocketmoney.entity.RegularPocketMoney;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public interface PocketMoneyService {
  RegularPocketMoney registryRegularPocketMoney(User parent, RegistRegularPocketMoneyReqDto req, LocalDateTime now)
      throws AuthenticationException, MustParentException, MustChildException, DuplicatedException;
}

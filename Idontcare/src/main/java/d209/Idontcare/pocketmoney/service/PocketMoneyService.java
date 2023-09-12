package d209.Idontcare.pocketmoney.service;

import d209.Idontcare.common.exception.CommonException;
import d209.Idontcare.pocketmoney.dto.req.RegistRegularPocketMoneyReqDto;
import org.springframework.stereotype.Service;

@Service
public interface PocketMoneyService {
  public Long registryRegularPocketMoney(RegistRegularPocketMoneyReqDto req) throws CommonException;
}

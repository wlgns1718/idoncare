package d209.Idontcare.pocketmoney.service;

import d209.Idontcare.common.exception.CommonException;
import d209.Idontcare.pocketmoney.dto.req.RegistRegularPocketMoneyReqDto;

import javax.transaction.Transactional;

@Transactional
public class PocketMoneyServiceImpl implements PocketMoneyService {
  @Override
  public Long registryRegularPocketMoney(RegistRegularPocketMoneyReqDto req) throws CommonException {
    return null;
  }
}

package d209.Idontcare.pocketmoney.repository;

import d209.Idontcare.pocketmoney.entity.RegularPocketMoneyRejected;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import java.util.Arrays;
import java.util.List;

public interface RegularPocketMoneyRejectedRepository extends JpaRepository<RegularPocketMoneyRejected, Long> {
  
  @Query("select r.regularPocketMoneyRejectedId as regularPocketMoneyRejectedId, r.amount as amount, r.dueDate as dueDate" +
      " from RegularPocketMoneyRejected r" +
      " where r.regularPocketMoney.regularPocketMoneyId = :regularPocketMoneyId")
  List<Tuple> findByRegularPocketMoneyId(@Param("regularPocketMoneyId") Long regularPocketMoneyId);
  
  @Modifying
  @Query("DELETE" +
      " FROM RegularPocketMoneyRejected r" +
      " WHERE r.parent.userId = :parentUserId AND r.child.userId = :childUserId")
  void deleteAllByParentUserIdAndChildUserId(@Param("parentUserId") Long parentUserId, @Param("childUserId") Long childUserId);
}

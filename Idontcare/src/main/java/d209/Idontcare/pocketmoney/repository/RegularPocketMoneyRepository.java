package d209.Idontcare.pocketmoney.repository;

import d209.Idontcare.pocketmoney.entity.RegularPocketMoney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import java.util.List;

public interface RegularPocketMoneyRepository extends JpaRepository<RegularPocketMoney, Long> {
  
  @Query("select r.regularPocketMoneyId as regularPocketMoneyId, r.child.userId as childUserId, r.child.name as childName, " +
      "r.type as type, r.cycle as cycle, r.amount as amount, r.createdAt as createdAt " +
      "from RegularPocketMoney r where r.parent.userId = :parentUserId")
  List<Tuple> findAllByParentUserId(@Param("parentUserId") Long parentUserId);
}

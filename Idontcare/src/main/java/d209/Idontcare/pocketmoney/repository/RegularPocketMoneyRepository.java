package d209.Idontcare.pocketmoney.repository;

import d209.Idontcare.pocketmoney.entity.RegularPocketMoney;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import java.util.List;

public interface RegularPocketMoneyRepository extends JpaRepository<RegularPocketMoney, Long> {
  
  @Query("select r.regularPocketMoneyId as regularPocketMoneyId, r.child.userId as userId, r.child.name as name, " +
      "r.type as type, r.cycle as cycle, r.amount as amount, r.createdAt as createdAt " +
      "from RegularPocketMoney r where r.parent.userId = :parentUserId")
  List<Tuple> findAllByParentUserId(@Param("parentUserId") Long parentUserId);
  
  @Query("select r.regularPocketMoneyId as regularPocketMoneyId, r.parent.userId as userId, r.parent.name as name, " +
      "r.type as type, r.cycle as cycle, r.amount as amount, r.createdAt as createdAt " +
      "from RegularPocketMoney r where r.child.userId = :childUserId")
  List<Tuple> findAllByChildUserId(@Param("childUserId") Long childUserId);
  
  @EntityGraph(attributePaths = {"parent.userId", "child.userId"}, type= EntityGraph.EntityGraphType.LOAD)
  List<RegularPocketMoney> findAllByDueDate(Integer dueDate);
  
  @Modifying
  @Query("DELETE" +
      " FROM RegularPocketMoney r" +
      " WHERE r.parent.userId = :parentUserId AND r.child.userId = :childUserId")
  void deleteAllByParentUserIdAndChildUserId(@Param("parentUserId") Long parentUserId, @Param("childUserId") Long childUserId);
}

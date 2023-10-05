package d209.Idontcare.mission.repository;


import d209.Idontcare.mission.dto.MissionSimpleDto;
import d209.Idontcare.mission.entity.Mission;
import d209.Idontcare.mission.entity.Type;
import d209.Idontcare.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.util.List;


@Repository
public interface MissionRepository extends JpaRepository<Mission,Long> {

    @Query("select m.missionId as missionId,  m.title as title, m.amount as amount ,m.type as type" +
            " from Mission m where m.parent.userId = :parent_userId")
    List<Tuple> findByParent_UserId(Long parent_userId);

    @Query("select m.missionId as missionId, m.title as title, m.amount as amount ,m.type as type " +
            "from Mission m where m.parent.userId = :parent_userId and m.type = :type")
    List<Tuple> findByParent_UserIdAndType(Long parent_userId, Type type);

    @Query("select m.missionId as missionId, m.title as title, m.amount as amount ,m.type as type " +
            "from Mission m where m.child.userId = :child_userId")
    List<Tuple> findByChild_UserId(Long child_userId);

    @Query("select m.missionId as missionId, m.title as title, m.amount as amount ,m.type as type " +
            "from Mission m where m.child.userId = :child_userId and m.type = :type")
    List<Tuple> findByChild_UserIdAndType(Long child_userId, Type type);


    @Query("select m.missionId as missionId, m.title as title, m.amount as amount, m.type as type, " +
            "m.child.userId as childId, m.parent.userId as parentId, m.parent.nickName as parentName, " +
            "m.child.nickName as childName  from Mission m where m.parent.userId = :userId" )
    List<Tuple> findAllByParent_UserId(@Param("userId") Long userId);

    @Query("select m.missionId as missionId, m.title as title, m.amount as amount, m.type as type, " +
            "m.child.userId as childId, m.parent.userId as parentId, m.parent.nickName as parentName, " +
            "m.child.nickName as childName  from Mission m where m.child.userId = :userId" )
    List<Tuple> findAllByChild_UserId(@Param("userId") Long userId);


}

package d209.Idontcare.relationship.repository;

import d209.Idontcare.relationship.entity.Relationship;
import d209.Idontcare.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import java.util.List;

public interface RelationshipRepository extends JpaRepository<Relationship, Long> {

  @Query("select r.relationshipId from Relationship r where r.parent.userId = :parentUserId and r.child.userId = :childUserId")
  Page<Long> existsByParentAndChild(@Param("parentUserId") Long parentUserId, @Param("childUserId") Long childUserId, Pageable pageable);
  
  @Query("select r.relationshipId as relationshipId, r.child.userId as userId, r.child.name as userName, r.createdAt as createdAt" +
      " from Relationship r where r.parent.userId = :parentUserId")
  List<Tuple> findAllByParent(@Param("parentUserId") Long parentUserId);
  
  @Query("select r.relationshipId as relationshipId, r.parent.userId as userId, r.parent.name as userName, r.createdAt as createdAt" +
      " from Relationship r where r.child.userId = :childUserId")
  List<Tuple> findAllByChild(@Param("childUserId") Long childUserId);
}

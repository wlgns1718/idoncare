package d209.Idontcare.relationship.repository;

import d209.Idontcare.User;
import d209.Idontcare.relationship.dto.res.RelationshipResDto;
import d209.Idontcare.relationship.entity.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import java.util.List;
import java.util.Optional;

public interface RelationshipRepository extends JpaRepository<Relationship, Long> {
  
  @Query("select r from Relationship r where r.parent.userId = :parentUserId and r.child.userId = :childUserId")
  Optional<Relationship> findOneByParentAndChild(@Param("parentUserId") Long parentUserId, @Param("childUserId") Long childUserId);
  
  @Query("select r.relationshipId as relationshipId, r.child.userId as userId, r.child.name as userName, r.createdAt as createdAt" +
      " from Relationship r where r.parent = :parent")
  List<Tuple> findAllByParent(@Param("parent") User parent);
  
  @Query("select r.relationshipId as relationshipId, r.parent.userId as userId, r.parent.name as userName, r.createdAt as createdAt" +
      " from Relationship r where r.child = :child")
  List<Tuple> findAllByChild(@Param("child") User child);
}

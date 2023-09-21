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

  @Query("select count(r.relationshipId) > 0 from Relationship r where r.parent = :parent and r.child = :child")
  Page<Integer> existsByParentAndChild(@Param("parent") User parent, @Param("child") User child, Pageable pageable);
  
  @Query("select r.relationshipId as relationshipId, r.child.userId as userId, r.child.name as userName, r.createdAt as createdAt" +
      " from Relationship r where r.parent = :parent")
  List<Tuple> findAllByParent(@Param("parent") User parent);
  
  @Query("select r.relationshipId as relationshipId, r.parent.userId as userId, r.parent.name as userName, r.createdAt as createdAt" +
      " from Relationship r where r.child = :child")
  List<Tuple> findAllByChild(@Param("child") User child);
}

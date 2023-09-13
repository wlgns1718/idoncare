package d209.Idontcare.relationship.repository;

import d209.Idontcare.relationship.entity.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RelationshipRepository extends JpaRepository<Relationship, Long> {
  
  @Query("select r from Relationship r where r.parent.userId = :parentUserId and r.child.userId = :childUserId")
  Optional<Relationship> findOneByParentAndChild(@Param("parentUserId") Long parentUserId, @Param("childUserId") Long childUserId);
}

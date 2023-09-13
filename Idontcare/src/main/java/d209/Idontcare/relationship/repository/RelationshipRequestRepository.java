package d209.Idontcare.relationship.repository;

import d209.Idontcare.relationship.entity.Relationship;
import d209.Idontcare.relationship.entity.RelationshipRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RelationshipRequestRepository extends JpaRepository<RelationshipRequest, Long> {
  
  @Query("select r from RelationshipRequest r where r.parent.userId = :parentUserId and r.child.userId = :childUserId")
  Optional<RelationshipRequest> findOneByParentAndChild(@Param("parentUserId") Long parentUserId, @Param("childUserId") Long childUserId);
}

package d209.Idontcare.relationship.repository;

import d209.Idontcare.User;
import d209.Idontcare.relationship.entity.Relationship;
import d209.Idontcare.relationship.entity.RelationshipRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RelationshipRequestRepository extends JpaRepository<RelationshipRequest, Long> {
  
  @Query("select r from RelationshipRequest r where r.parent.userId = :parentUserId and r.child.userId = :childUserId")
  Optional<RelationshipRequest> findOneByParentAndChild(@Param("parentUserId") Long parentUserId, @Param("childUserId") Long childUserId);
  
  @Query("select r, p.name from RelationshipRequest r join fetch r.parent p where r.child = :child")
  List<RelationshipRequest> findAllByChild(@Param("child") User child);
}

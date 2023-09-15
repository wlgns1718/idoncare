package d209.Idontcare.relationship.repository;

import d209.Idontcare.relationship.entity.RelationshipRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import java.util.List;
import java.util.Optional;

public interface RelationshipRequestRepository extends JpaRepository<RelationshipRequest, Long> {
  
  @Query("select r from RelationshipRequest r where r.parent.userId = :parentUserId and r.child.userId = :childUserId")
  Optional<RelationshipRequest> findOneByParentAndChild(@Param("parentUserId") Long parentUserId, @Param("childUserId") Long childUserId);
  
  @Query("select r.relationshipRequestId as relationshipRequestId, r.createdAt as createdAt, r.parent.name as parentName" +
      " from RelationshipRequest r where r.child = :child")
  List<Tuple> findAllByChild(@Param("child") User child);
}

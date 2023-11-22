package d209.Idontcare.relationship.repository;

import d209.Idontcare.relationship.entity.RelationshipRequest;
import d209.Idontcare.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import java.util.List;
import java.util.Optional;

public interface RelationshipRequestRepository extends JpaRepository<RelationshipRequest, Long> {
  
  @Query("select r.relationshipRequestId from RelationshipRequest r where r.parent.userId = :parentUserId and r.child.userId = :childUserId")
  Page<Long> existsByParentAndChild(@Param("parentUserId") Long parentUserId, @Param("childUserId") Long childUserId, Pageable pageable);
  
  @Query("select r.relationshipRequestId as relationshipRequestId, r.createdAt as createdAt, r.parent.name as parentName, r.parent.phoneNumber as parentPhoneNumber" +
      " from RelationshipRequest r where r.child.userId = :childUserId")
  List<Tuple> findAllByChild(@Param("childUserId") Long childUserId);
}

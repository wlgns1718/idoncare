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
  
  @Query("select count(r.relationshipRequestId) > 0 from RelationshipRequest r where r.parent = :parent and r.child = :child")
  Page<Integer> existsByParentAndChild(@Param("parent") User parent, @Param("child") User child, Pageable pageable);
  
  @Query("select r.relationshipRequestId as relationshipRequestId, r.createdAt as createdAt, r.parent.name as parentName" +
      " from RelationshipRequest r where r.child = :child")
  List<Tuple> findAllByChild(@Param("child") User child);
}

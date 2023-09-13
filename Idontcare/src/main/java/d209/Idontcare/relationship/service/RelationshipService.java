package d209.Idontcare.relationship.service;

import d209.Idontcare.User;
import d209.Idontcare.common.exception.DuplicatedException;
import d209.Idontcare.common.exception.MustChildException;
import d209.Idontcare.common.exception.MustParentException;
import d209.Idontcare.common.exception.NoSuchUserException;
import d209.Idontcare.relationship.dto.req.RequestRelationshipReqDto;
import d209.Idontcare.relationship.entity.RelationshipRequest;
import org.springframework.stereotype.Service;

@Service
public interface RelationshipService {
  
  RelationshipRequest requestRelationship(User parent, RequestRelationshipReqDto req)
      throws NoSuchUserException, MustParentException, MustChildException, DuplicatedException;
}

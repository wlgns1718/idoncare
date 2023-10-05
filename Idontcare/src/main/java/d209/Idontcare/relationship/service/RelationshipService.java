package d209.Idontcare.relationship.service;

import d209.Idontcare.common.exception.*;
import d209.Idontcare.relationship.dto.req.*;
import d209.Idontcare.relationship.dto.res.*;
import d209.Idontcare.relationship.entity.RelationshipRequest;
import d209.Idontcare.user.entity.Role;
import d209.Idontcare.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RelationshipService {
  
  /**
   * 부모가 아이에게 관계 맺기 요청
   */
  RelationshipRequest requestRelationship(Long parentUserId, RequestRelationshipReqDto req)
      throws NoSuchUserException, MustParentException, MustChildException, DuplicatedException;
  
  /**
   * 아이가 받은 관계 맺기 요청
   */
  List<ReceivedRequestResDto> getReceivedRequestList(Long childUserId)
      throws MustChildException;
  
  /**
   * 아이가 받은 관계 맺기 요청에 대해 수락/거절 처리
   */
  void processReceivedRequest(Long childUserId, ProcessReceivedRequestReqDto req)
      throws MustChildException, NoSuchContentException, AuthorizationException;
  
  /**
   * 유저가 맺고 있는 관계의 리스트를 반환
   */
  List<RelationshipResDto> getRelationshipList(Long userId, Role role);
  
  /**
   * 해당 유저간에 관계가 존재하는지 확인
   */
  boolean relationExistsByParentAndChild(Long parentUserId, Long childUserId);
  
  void deleteRelationship(Long parentUserId, Long relationshipId)
      throws NoSuchContentException, AuthorizationException;
}

package d209.Idontcare.common.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;


@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
  
  @CreatedDate
  @Column(updatable=false)
  private LocalDateTime createdAt;
  
  @Override
  public int hashCode(){
    return createdAt.hashCode();
  }
  
  @Override
  public boolean equals(Object other){
    LocalDateTime o = (LocalDateTime) other;
    return this.createdAt.equals(o);
  }
}

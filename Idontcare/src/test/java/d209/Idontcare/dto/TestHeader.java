package d209.Idontcare.dto;

import lombok.Data;

public class TestHeader{
  String head1;
  Integer head2;
  
  public String getHead1() {
    return head1;
  }
  
  public void setHead1(String head1) {
    this.head1 = head1;
  }
  
  public Integer getHead2() {
    return head2;
  }
  
  public void setHead2(Integer head2) {
    this.head2 = head2;
  }
  
  public TestHeader(String head1, Integer head2){
    this.head1 = head1;
    this.head2 = head2;
  }
}
package d209.Idontcare.dto;


import lombok.Data;

public class TestBody {
  private String name;
  private Integer[] age;
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public Integer[] getAge() {
    return age;
  }
  
  public void setAge(Integer[] age) {
    this.age = age;
  }
}

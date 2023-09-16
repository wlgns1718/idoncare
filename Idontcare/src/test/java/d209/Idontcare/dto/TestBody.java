package d209.Idontcare.dto;


public class TestBody {
  private String name;
  private Integer age;
  
  public String getName() {return name;}
  public Integer getAge() {return age;}
  
  @Override
  public String toString() {
    return "TestBody{" +
        "name='" + name + '\'' +
        ", age=" + age +
        '}';
  }
}

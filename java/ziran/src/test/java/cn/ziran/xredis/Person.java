package cn.ziran.xredis;

import java.io.Serializable;

public class Person implements Serializable {

  private static final long serialVersionUID = -6563695166651942420L;
  private String id;
  private String name;
  private Integer age;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

}

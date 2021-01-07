package com.terence.itech.base.AAtest.set;

/**
 * @author tiancai
 * @version $$Id: Person, v 0.1 2020/6/3 17:10 ***  Exp $$
 */
public class Person {
  private String id;
  private String name;
  private int age;
  private String address;

  public Person(){}

  @Override
  public int hashCode(){
    return name.hashCode();
  }
  @Override
  public boolean equals(Object obj){
    if(obj instanceof Person){
      Person p = (Person)obj;
      if(p.getName().equals(this.getName())){
        p.setId(this.getId()+"."+p.getId());
        return true;
      }else{
        return false;
      }
    }
    return false;
  }

  public Person(String id, String name, int age, String address) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.address = address;
  }

  @Override
  public String toString() {
    return "Person{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", age=" + age +
            ", address='" + address + '\'' +
            '}';
  }

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

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

}

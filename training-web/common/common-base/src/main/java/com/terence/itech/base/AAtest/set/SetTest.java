package com.terence.itech.base.AAtest.set;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author tiancai
 * @version $$Id: SetTest, v 0.1 2020/6/4 9:10 ***  Exp $$
 */
public class SetTest {
  public static void main(String[] args){
    System.out.println(java.util.UUID.randomUUID().toString());

    List<Person> personList=new ArrayList<Person>(){{
      add(new Person("1","lili",10,"湘潭路"));
      add(new Person("2","lucy",32,"翠竹路"));
      add(new Person("3","lili",11,"干将路"));
      add(new Person("4","lucy",32,"翠竹路"));
      add(new Person("5","lili",11,"干将路"));
    }};
    Set<Person> people=new HashSet<>();
    people.addAll(personList);
    System.out.println(people.toString());
  }
}

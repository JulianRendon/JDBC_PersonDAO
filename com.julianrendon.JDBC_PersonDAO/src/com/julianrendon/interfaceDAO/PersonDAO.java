package com.julianrendon.interfaceDAO;

import java.util.List;

import com.julianrendon.model.Person;

public interface PersonDAO {

	void create(Person p);

	void update(Person p);

	void delete(int id);

	List<Person> findAll();

	Person findById(int id);

}

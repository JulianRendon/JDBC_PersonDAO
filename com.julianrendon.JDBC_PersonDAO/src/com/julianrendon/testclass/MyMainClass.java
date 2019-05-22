package com.julianrendon.testclass;

import java.util.List;
import java.util.Scanner;

import com.julianrendon.interfaceDAO.PersonDAO;
import com.julianrendon.interfaceDAOImplementation.PersonDAOImplementation;
import com.julianrendon.model.Person;

public class MyMainClass {

	public static void main(String[] args) {

		// create DAO object for PersonDAO
		PersonDAO dao = new PersonDAOImplementation();

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String continueOption = "y";
		String menuTitle;

		while (continueOption.toLowerCase().equals("y")) {

			menuTitle = "MAIN MENU - PERSON TABLE";
			System.out.println(menuTitle);
			System.out.println("Enter 1 to create a new person");
			System.out.println("Enter 2 to update a person");
			System.out.println("Enter 3 to delete a person");
			System.out.println("Enter 4 to display all people");

			int menuOption = sc.nextInt();

			/**
			 * *** MAIN MENU ****
			 */
			switch (menuOption) {

			/**
			 * *** CREATE A NEW PERSON ****
			 */
			case 1:
				System.out.println("Enter person's id:");
				int id = sc.nextInt();
				System.out.println("Enter person's name:");
				String name = sc.next();

				Person person = new Person();
				person.setId(id);
				person.setName(name);

				dao.create(person);
				System.out.println("New person '" + name + "' has been created!");
				break;

			/**
			 * *** UPDATE PERSON'S INFO ****
			 */
			case 2:
				System.out.println("Enter id:");
				id = sc.nextInt();

				Person personSelected = dao.findById(id);
				if (personSelected.getId() == 0) {
					System.out.println("This id does not exist");
				} else {
					System.out.println("Selected = " + personSelected.getName());
					System.out.println("Enter the new name:");
					String newName = sc.next();

					Person personUpdate = new Person();
					personUpdate.setId(id);
					personUpdate.setName(newName);

					dao.update(personUpdate);
					System.out.println("Person with id = " + id + " has been updated!");
				}
				break;

			/**
			 * *** DELETE PERSON ****
			 */
			case 3:
				System.out.println("Enter id:");
				id = sc.nextInt();

				Person personToBeDeleted = dao.findById(id);
				if (personToBeDeleted.getId() == 0) {
					System.out.println("This id does not exist");
				} else {
					System.out.println("Selected = " + personToBeDeleted.getName());
					String confirmation = null;
					System.out.println("Are you sure you want to delete '" + personToBeDeleted.getName() + "'? (y/n)");
					confirmation = sc.next();
					if (confirmation.toLowerCase().equals("y")) {
						dao.delete(id);
						System.out.println("Person id = " + id + " has been deleted!");
					} else if (confirmation.toLowerCase().equals("n")) {
						System.out.println("Delete has been canceled!");
					} else {
						System.out.println("Wrong option..start over again!");
					}
				}
				break;

			/**
			 * *** DISPLAY ALL PEOPLE ****
			 */
			case 4:
				List<Person> allPeople = dao.findAll();

				if (allPeople.isEmpty()) {
					System.out.println("There is no people to display");
				} else {
					System.out.println("List of all people");
					for (Person p : allPeople) {
						System.out.println("Person id:" + p.getId() + " name: " + p.getName());
					}
				}
				break;

			default:
				System.out.println("Wrong choise!");
				break;
			}

			System.out.println("Do you want to continue on the Main Menu? (y or n)");
			continueOption = sc.next();
		}

		System.out.println("Thank you. Your session has ended");
	}
}

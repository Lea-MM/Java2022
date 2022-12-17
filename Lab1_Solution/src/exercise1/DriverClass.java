//	Author: Lea Marie Magbalot
// 	Date: September 12, 2022

package exercise1;

import java.time.LocalDate;
import java.util.Scanner;

public class DriverClass {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		// create a Singers object
		Singers singer1 = new Singers();
		
		// display default values of singer1
		System.out.println("--Default values of the instance variables--");
		System.out.printf("The id of singer1 is %d%n", singer1.GetId());
		System.out.printf("The name of singer1 is %s%n", singer1.GetName());
		System.out.printf("The address of singer1 is %s%n", singer1.GetAddress());
		System.out.printf("The birthdate of singer1 is on %s%n", singer1.GetBirthdate());
		System.out.printf("The number of albums published by singer1 are %d%n%n", singer1.GetNumOfAlbums());
		
		// set values for id, name, address, birthdate, and number of albums for singer1
		System.out.println("--Setting values for singer 1--");
		
		int singerId = 0;
		System.out.println("Enter the id: ");
		String id = input.nextLine().strip();
		if(Singers.IsValidIntegerInput(id)) {
			singerId = Integer.parseInt(id);
		}
		singer1.SetId(singerId);
		
		System.out.println("Enter the name: ");
		String singerName = input.nextLine().strip();
		singer1.SetName(singerName);
		
		System.out.println("Enter the address: ");
		String singerAddress = input.nextLine().strip();
		singer1.SetAddress(singerAddress);
		
		System.out.println("Enter the birthdate (yyyy-mm-dd): ");
		String singerBirthDate = input.nextLine().strip();
		String[] inputArray = singerBirthDate.split("-");
		String[] newArray = new String[3];
		if(!Singers.IsBirthdateValid(inputArray)) {
			newArray[0] = "0001";
			newArray[1] = "01";
			newArray[2] = "01";
		}
		else {
			newArray = inputArray;
		}
		singer1.SetBirthdate(LocalDate.of(Integer.parseInt(newArray[0]), Integer.parseInt(newArray[1]), Integer.parseInt(newArray[2])));
		
		int numberOfAlbums = 0;
		System.out.println("Enter the number of albums: ");
		String numOfAlbums = input.nextLine().strip();
		if(Singers.IsValidIntegerInput(numOfAlbums)) {
			numberOfAlbums = Integer.parseInt(numOfAlbums);
		}
		singer1.SetNumOfAlbums(numberOfAlbums);
		
		System.out.println();
		
		// display stored values of singer1
		System.out.printf("The id of singer1 is %d%n", singer1.GetId());
		System.out.printf("The name of singer1 is %s%n", singer1.GetName());
		System.out.printf("The address of singer1 is %s%n", singer1.GetAddress());
		System.out.printf("The birthdate of singer1 is on %s%n", singer1.GetBirthdate());
		System.out.printf("The number of albums published by singer1 are %d%n", singer1.GetNumOfAlbums());
		
		input.close();
	}

}
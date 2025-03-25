package hex.main;




 
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import hex.entity.Customer;
import hex.entity.Lease;
import hex.entity.Vehicle;
import hex.exception.CarNotFoundException;
import hex.exception.CustomerNotFoundException;
import hex.exception.LeaseNotFoundException;
import hex.dao.*;
 
public class Main{
    public static void main(String[] args) throws Throwable {
        Scanner scanner = new Scanner(System.in);
        DAO carLeaseRepo = new DAO_IMP();
        
        while (true) {
            System.out.println("\n--- Car Rental System ---");
            System.out.println("1. Add New Car");
            System.out.println("2. Remove Car");
            System.out.println("3. List Available Cars");
            System.out.println("4. List Rented Cars");
            System.out.println("5. Find Car by ID");
            System.out.println("6. Add Customer");
            System.out.println("7. Remove Customer");
            System.out.println("8. List Customers");
            System.out.println("9. Find Customer by ID");
            System.out.println("10. Create Lease");
            System.out.println("11. Return Car");
            System.out.println("12. Record Payment");
            System.out.println("13. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addCar(scanner, carLeaseRepo);
                    break;
                case 2:
                    removeCar(scanner, carLeaseRepo);
                    break;
                case 3:
                    listAvailableCars(carLeaseRepo);
                    break;
                case 4:
                    listRentedCars(carLeaseRepo);
                    break;
                case 5:
                    findCarById(scanner, carLeaseRepo);
                    break;
                case 6:
                    addCustomer(scanner, carLeaseRepo);
                    break;
                case 7:
                    removeCustomer(scanner, carLeaseRepo);
                    break;
                case 8:
                    listCustomers(carLeaseRepo);
                    break;
                case 9:
                    findCustomerById(scanner, carLeaseRepo);
                    break;
                case 10:
                    createLease(scanner, carLeaseRepo);
                    break;
//                case 11:
//                    returnCar(scanner, carLeaseRepo);
//                    break;
//                case 12:
//                    recordPayment(scanner, carLeaseRepo);
//                    break;
                case 11:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
 
    private static void addCar(Scanner scanner, DAO repo) {
        System.out.print("Enter Make: ");
        String make = scanner.next();
        System.out.print("Enter Model: ");
        String model = scanner.next();
        System.out.print("Enter Year: ");
        int year = scanner.nextInt();
        System.out.print("Enter Daily Rate: ");
        double dailyRate = scanner.nextDouble();
        System.out.print("Enter Status (available/notAvailable): ");
        String status = scanner.next();
        System.out.print("Enter Passenger Capacity: ");
        int passengerCapacity = scanner.nextInt();
        System.out.print("Enter Engine Capacity: ");
        int engineCapacity = scanner.nextInt();
 
        Vehicle car = new Vehicle(0, make, model, year, dailyRate, status, passengerCapacity, engineCapacity);
        repo.addCar(car);
        System.out.println("Car added successfully!");
    }
 
    private static void removeCar(Scanner scanner, DAO repo) {
        System.out.print("Enter Car ID to remove: ");
        int carID = scanner.nextInt();
        repo.removeCar(carID);
        System.out.println("Car removed successfully!");
    }
 
    private static void listAvailableCars(DAO repo) {
        List<Vehicle> cars = repo.listAvailableCars();
        for (Vehicle car : cars) {
            System.out.println(car);
        }
    }
 
    private static void listRentedCars(DAO repo) {
        List<Vehicle> cars = repo.listRentedCars();
        for (Vehicle car : cars) {
            System.out.println(car);
        }
    }
 
    private static void findCarById(Scanner scanner,DAO repo) {
        System.out.print("Enter Car ID: ");
        int carID = scanner.nextInt();
        try {
            Vehicle car = repo.findCarById(carID);
            System.out.println(car);
        } catch (CarNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
 
    private static void addCustomer(Scanner scanner,DAO repo) {
        System.out.print("Enter First Name: ");
        String firstName = scanner.next();
        System.out.print("Enter Last Name: ");
        String lastName = scanner.next();
        System.out.print("Enter Email: ");
        String email = scanner.next();
        System.out.print("Enter Phone Number: ");
        String phoneNumber = scanner.next();
 
        Customer customer = new Customer(0, firstName, lastName, email, phoneNumber);
        repo.addCustomer(customer);
        System.out.println("Customer added successfully!");
    }
 
    private static void removeCustomer(Scanner scanner, DAO repo) {
        System.out.print("Enter Customer ID to remove: ");
        int customerID = scanner.nextInt();
        repo.removeCustomer(customerID);
        System.out.println("Customer removed successfully!");
    }
 
    private static void listCustomers(DAO repo) {
        List<Customer> customers = repo.listCustomers();
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }
 
    private static void findCustomerById(Scanner scanner, DAO repo) {
        System.out.print("Enter Customer ID: ");
        int customerID = scanner.nextInt();
        try {
            Customer customer = repo.findCustomerById(customerID);
            System.out.println(customer);
        } catch (CustomerNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
 
    private static void createLease(Scanner scanner, DAO repo) {
        System.out.print("Enter Customer ID: ");
        int customerID = scanner.nextInt();
        System.out.print("Enter Car ID: ");
        int carID = scanner.nextInt();
        System.out.print("Enter Start Date (YYYY-MM-DD): ");
        String startDate = scanner.next();
        System.out.print("Enter End Date (YYYY-MM-DD): ");
        String endDate = scanner.next();
        System.out.print("Enter Lease Type (Daily/Monthly): ");
        String type = scanner.next();
 
        Lease lease = repo.createLease(customerID, carID, startDate, endDate, type);
        if (lease != null) {
            System.out.println("Lease created successfully! Lease ID: " + lease.getLeaseID());
        } else {
            System.out.println("Failed to create lease.");
        }
    }
 
//    private static void returnCar(Scanner scanner,DAO repo) {
//        System.out.print("Enter Lease ID: ");
//        int leaseID = scanner.nextInt();
//        try {
//            Lease lease = repo.returnCar(leaseID);
//            System.out.println("Car returned successfully! Lease Info: " + lease);
//        } catch (LeaseNotFoundException e) {
//            System.out.println(e.getMessage());
//        }
//    }
 
    
}
 

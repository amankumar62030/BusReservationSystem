package Projects;

import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;


class PassengerDetails {
	    String name;
	    int bus_num;
	    int num_of_seats;
	}

public class BusReservationSystem {

	    static Scanner scanner = new Scanner(System.in);

	    public static void main(String[] args) {
	        int menu_choice;
	        
	        do {
	            clearScreen();
	            System.out.println("\n=================================");
	            System.out.println("    BUS RESERVATION SYSTEM");
	            System.out.println("=================================");
	            System.out.println("1>> Reserve A Ticket");
	            System.out.println("------------------------");
	            System.out.println("2>> View All Available Bus");
	            System.out.println("------------------------");
	            System.out.println("3>> Exit");
	            System.out.println("------------------------");
	            System.out.print("\n\n-->");
	            menu_choice = scanner.nextInt();
	            
	            switch (menu_choice) {
	                case 1:
	                    reservation();
	                    break;
	                case 2:
	                    viewDetails();
	                    System.out.println("\n\nPress any key to go to Main Menu..");
	                    scanner.nextLine(); // Consume newline character
	                    scanner.nextLine(); // Wait for user input
	                    break;
	                case 3:
	                    System.exit(0);
	                default:
	                    System.out.println("\nInvalid choice");
	            }
	        } while (menu_choice != 3);
	    }

	    static void viewDetails() {
	        clearScreen();
	        System.out.println("\nBUS.No\tBUS Name\t\t\tDestinations\t\tfare\t\tTime");
	        System.out.println("\n533101\tBus A\tKerala to Karnataka\t\t       Rs.5000\t\t9am");
	        System.out.println("533102\tBus B\tKarnataka to Maharashtra\t\t   Rs.5000\t\t12pm");
	        System.out.println("533103\tBus C\tKerala to Tamilnadu\t\t       Rs.4500\t\t8am");
	        System.out.println("533104\tBus D\tTamilnadu to Andhra Pradesh\t\tRs.4500\t\t11am");
	        System.out.println("533105\tBus E\tAndhra Pradesh to Telangana\t\tRs.5000\t\t7am");
	    }

	    static void reservation() {
	        char confirm;
	        float charges;
	        PassengerDetails passdetails = new PassengerDetails();
	        PrintWriter writer = null;
	        
	        try {
	            writer = new PrintWriter(new FileWriter("seats_reserved.txt", true));

	            clearScreen();
	            scanner.nextLine(); // Consume newline character
	            System.out.print("\nEnter Your Name:> ");
	            passdetails.name = scanner.nextLine();

	            System.out.print("\nEnter Number of seats:> ");
	            passdetails.num_of_seats = scanner.nextInt();

	            System.out.print("\n\n>>Press Enter To View Available Bus<< ");
	            scanner.nextLine(); // Consume newline character
	            clearScreen();
	            viewDetails();

	            System.out.print("\n\nEnter bus number:> ");
	            passdetails.bus_num = scanner.nextInt();

	            if (passdetails.bus_num >= 533101 && passdetails.bus_num <= 533105) {
	                charges = charge(passdetails.bus_num, passdetails.num_of_seats);
	                printTicket(passdetails.name, passdetails.num_of_seats, passdetails.bus_num, charges, writer);
	            } else {
	                System.out.print("\nInvalid bus Number! Enter again--> ");
	                reservation();
	                return;
	            }

	            System.out.print("\n\nConfirm Ticket (y/n):>");
	            confirm = scanner.next().charAt(0);

	            if (confirm == 'y') {
	                writer.printf("%s\t\t%d\t\t%d\t\t%.2f\n", passdetails.name, passdetails.num_of_seats,
	                        passdetails.bus_num, charges);
	                System.out.println("==================");
	                System.out.println(" Reservation successful");
	                System.out.println("==================");
	                System.out.println("Press any key to go back to Main menu");
	                scanner.nextLine(); // Consume newline character
	                scanner.nextLine(); // Wait for user input
	            } else if (confirm == 'n') {
	                System.out.println("\nReservation Not Done!\nPress any key to go back to Main menu!");
	                scanner.nextLine(); // Consume newline character
	                scanner.nextLine(); // Wait for user input
	            } else {
	                System.out.println("\nInvalid choice entered! Enter again-----> ");
	                reservation();
	            }
	        } catch (Exception e) {
	            System.out.println("Error: " + e.getMessage());
	        } finally {
	            if (writer != null) {
	                writer.close();
	            }
	        }
	    }

	    static float charge(int bus_num, int num_of_seats) {
	        if (bus_num == 533101 || bus_num == 533102 || bus_num == 533105) {
	            return 5000.0f * num_of_seats;
	        } else if (bus_num == 533103 || bus_num == 533104) {
	            return 4500.0f * num_of_seats;
	        } else {
	            return 0.0f;
	        }
	    }

	    static void printTicket(String name, int num_of_seats, int bus_num, float charges, PrintWriter writer) {
	        clearScreen();
	        System.out.println("-------------------");
	        System.out.println("\tTICKET");
	        System.out.println("-------------------");
	        System.out.println("Name:\t\t\t" + name);
	        System.out.println("Number Of Seats:\t" + num_of_seats);
	        System.out.println("Bus Number:\t\t" + bus_num);
	        specificBus(bus_num);
	        System.out.println("Charges:\t\t" + charges);
	    }

	    static void specificBus(int bus_num) {
	        switch (bus_num) {
	            case 533101:
	                System.out.println("\nBus:\t\t\tBUS A");
	                System.out.println("Destination:\t\tKerala to Karnataka");
	                System.out.println("Departure:\t\t9am ");
	                break;
	            case 533102:
	                System.out.println("\nBus:\t\t\tBUS B");
	                System.out.println("Destination:\t\tKarnataka to Maharashtra");
	                System.out.println("Departure:\t\t12pm");
	                break;
	            case 533103:
	                System.out.println("\nBus:\t\t\tBUS C");
	                System.out.println("Destination:\t\tKerala to Tamilnadu");
	                System.out.println("Departure:\t\t8am");
	                break;
	            case 533104:
	                System.out.println("\nBus:\t\t\tBUS D");
	                System.out.println("Destination:\t\tTamilnadu to Andhra Pradesh");
	                System.out.println("Departure:\t\t11am ");
	                break;
	            case 533105:
	                System.out.println("\nBus:\t\t\tBUS E");
	                System.out.println("Destination:\t\tAndhra Pradesh to Telangana");
	                System.out.println("Departure:\t\t7am");
	                break;
	            default:
	                break;
	        }
	    }

	    static void clearScreen() {
	        // Clearing screen for Windows, Linux, and macOS
	        try {
	            if (System.getProperty("os.name").contains("Windows")) {
	                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	            } else {
	                Runtime.getRuntime().exec("clear");
	            }
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	    }
	}



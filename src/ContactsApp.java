import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactsApp {
    String directory = "src";
    String filename = "contacts.txt";
    Path contactsFilePath = Paths.get(directory, filename);
    List<String> contactsList = new ArrayList<>();

    public static void main(String[] args) {
        ContactsApp app = new ContactsApp();
        app.contactsMenu();

    }


    public void addContact(String firstName, String lastName, String phoneNumber) {
        src.Contact contactName = new src.Contact(firstName, lastName, phoneNumber);

        contactsList.add(contactName.contactCreator());

        writeFile(contactsFilePath, contactsList);
    }

    public void contactsMenu(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n" +
                "1. View contacts.\n" +
                "2. Add a new contact.\n" +
                "3. Search a contact by name.\n" +
                "4. Delete an existing contact.\n" +
                "5. Exit.\n" +
                "Enter an option (1, 2, 3, 4 or 5): \n");
        int menuSelect = scanner.nextInt();
            if (menuSelect == 1) {
                viewContacts(contactsFilePath, true);
                contactsMenu();
            }
            if (menuSelect == 2){
                System.out.println("Enter the contact first name here: ");
                String firstName = scanner.next();
                System.out.println("Enter the contact last name here: ");
                String lastName = scanner.next();
                System.out.println("Enter the contact phone number here: ");
                String phoneNumber = scanner.next();
                addContact(firstName, lastName, phoneNumber);
                contactsMenu();
            }
            if (menuSelect == 3){
                System.out.println("What contact would you like to search for?");
                String search = scanner.next();
                contactSearch(search, contactsFilePath);
            }
            if (menuSelect == 4){
                String userInput = "";

                List<String> lines = viewContacts(contactsFilePath, false);
                System.out.println("What contact would you like to delete?");
                String deleteSearch = scanner.next();
                for (String line : lines) {

                    if(line.contains(deleteSearch)){
                        System.out.println("Are you sure you want to remove this contact? (y/n)\n" + line);
                        userInput = scanner.next();

                    }

                    if(userInput.equals("y")){
                        lines.remove(line);
                        System.out.println(lines);
                        writeFile(contactsFilePath, lines);
                    }
                }
                contactsMenu();
            }
    }

//Menu option 1
    public static List<String> viewContacts(Path contacts, boolean print){
        List<String> lines;
        try{
            lines = Files.readAllLines(contacts);
            if(print == true){
                System.out.println("Name | Phone number\n" +
                        "---------------");
                for (String line: lines) {
                    System.out.println(line);
                }
                return lines;
            }
            return lines;
        } catch (IOException e){
            System.out.println("Problems reading the file");
            e.printStackTrace();
            return null;
        }
    }

    public static void writeFile(Path aFile, List<String> aList){
        try {
            Files.write(aFile, aList, StandardOpenOption.APPEND);
        } catch (IOException e){
            System.out.println("Problems writing in the file");
            e.printStackTrace();
        }
    }

    private void contactSearch(String needle, Path aFile) {
        List<String> lines = viewContacts(aFile, false);
        for (String line : lines) {
            if(line.contains(needle)){
                System.out.println(line);
            }
        }
        contactsMenu();
    }
}

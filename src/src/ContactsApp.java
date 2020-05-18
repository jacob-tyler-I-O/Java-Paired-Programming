package src;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        app.addContact();

    }


    public void addContact() {
        src.Contact jacob = new src.Contact("Jacob", "Hensley", "1234567890");
        src.Contact tyler = new src.Contact("Tyler", "Trudgeon", "0987654321");

        contactsList.add(jacob.contactCreator());
        contactsList.add(tyler.contactCreator());

        writeFile(contactsFilePath, contactsList);
    }


    public void contactsMenu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println(
                "1. View contacts.\n" +
                "2. Add a new contact.\n" +
                "3. Search a contact by name.\n" +
                "4. Delete an existing contact.\n" +
                "5. Exit.\n" +
                "Enter an option (1, 2, 3, 4 or 5):");
        int menuSelect = scanner.nextInt();
            if (menuSelect == 1) {
                viewContacts(contactsFilePath, true);
            }
    }


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
            Files.write(aFile, aList);
//            Files.write(aFile, aList, StandardOpenOption.APPEND);
        } catch (IOException e){
            System.out.println("Problems writing in the file");
            e.printStackTrace();
        }
    }
}

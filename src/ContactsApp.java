package src;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ContactsApp {
    public static void main(String[] args) {
        String directory = "src";
        String filename = "contacts.txt";
        List<String> contactsList = new ArrayList<>();

        Contact jacob = new Contact("Jacob", "Hensley", "1234567890");
        Contact tyler = new Contact("Tyler", "Trudgeon", "0987654321");

        contactsList.add(jacob.ContactCreator());
        contactsList.add(tyler.ContactCreator());

        Path contactsFilePath = Paths.get(directory, filename);

        writeFile(contactsFilePath, contactsList);
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

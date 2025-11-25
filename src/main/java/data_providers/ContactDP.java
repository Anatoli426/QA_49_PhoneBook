package data_providers;

import dto.Contact;
import org.testng.annotations.DataProvider;
import utils.PropertiesReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ContactDP {

    @DataProvider
    public Iterator<Contact> dataProviderContactFile() {
        List<Contact> list = new ArrayList<>();
        String fileName = PropertiesReader.getProperty("base.properties", "file_contact_csv");
        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader("src/test/resources/data_csw"+ File.separator+fileName)
        )) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] splitArray = line.split(",");
                list.add(Contact.builder()
                        .name(splitArray[0])
                        .lastName(splitArray[1])
                        .email(splitArray[2])
                        .phone(splitArray[3])
                        .address(splitArray[4])
                        .description(splitArray[5])
                        .build());
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("IO exception");
        }
        return list.listIterator();
    }
}
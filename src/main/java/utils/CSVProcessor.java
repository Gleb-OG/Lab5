package utils;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvException;
import data.Organization;
import managers.CollectionManager;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class CSVProcessor {
    public static void loadFromCSV(String filename) throws FileNotFoundException {
        try (Reader reader = new FileReader(filename)) {
            CsvToBean<Organization> csvToBean = new CsvToBeanBuilder<Organization>(reader)
                    .withType(Organization.class).withIgnoreLeadingWhiteSpace(true).build();

            List<Organization> organizations = csvToBean.parse();

            for (Organization organization : organizations) {
                CollectionManager.getCollection().put(organization.getID(), organization);
            }
        } catch (IOException e) {
            throw new FileNotFoundException("Ошибка при чтении файла: " + e);
        }
    }

    public static void saveToCSV(String filename) throws CsvException, IOException {
        try (Writer writer = new FileWriter(filename)) {
            StatefulBeanToCsv<Organization> beanToCsv = new StatefulBeanToCsvBuilder<Organization>(writer)
                    .withApplyQuotesToAll(false)
                    .build();

            List<Organization> organizations = new ArrayList<>(CollectionManager.getCollection().values());


            beanToCsv.write(organizations);
        } catch (IOException e) {
            throw new IOException("Ошибка при записи в CSV-файл: " + e.getMessage());
        } catch (CsvException e) {
            throw new CsvException("Ошибка при записи в CSV-файл: " + e.getMessage());
        }
    }

}

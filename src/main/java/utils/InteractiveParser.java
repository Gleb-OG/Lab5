package utils;

import exceptions.InvalidDataException;
import java.util.Scanner;


public class InteractiveParser {
    public static String readOrganizationName() throws InvalidDataException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Введите название организации: ");
            String input = scanner.nextLine().trim();

        }
    }
}
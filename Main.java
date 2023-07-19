package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UserInfoApp {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Введите данные: ");
            String input = scanner.nextLine();

            String[] data = input.split(" ");

            if (data.length != 6) {
                System.out.println("Ошибка: недостаточно данных или лишние данные");
                return;
            }

            String lastName = data[0];
            String firstName = data[1];
            String middleName = data[2];
            String dateOfBirth = data[3];
            String phoneNumber = data[4];
            String gender = data[5];

            if (!isValidDateFormat(dateOfBirth)) {
                throw new InvalidDataFormatException("Ошибка: некорректный формат даты рождения");
            }

            if (!isValidPhoneNumberFormat(phoneNumber)) {
                throw new InvalidDataFormatException("Ошибка: некорректный формат номера телефона");
            }

            if (!isValidGender(gender)) {
                throw new InvalidDataFormatException("Ошибка: некорректное значение пола");
            }

            String filename = lastName + ".txt";
            FileWriter fileWriter = new FileWriter(filename, true);

            String userInfo = lastName + " " + firstName + " " + middleName + " " +
                    dateOfBirth + " " + phoneNumber + " " + gender;

            fileWriter.write(userInfo + "\n");

            fileWriter.close();

            System.out.println("Данные сохранены в файл: " + filename);
        } catch (IOException e) {
            System.out.println("Ошибка при чтении/записи файла: " + e.getMessage());
        } catch (InvalidDataFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    private static boolean isValidDateFormat(String date) {
        String regex = "^(0[1-9]|[12][0-9]|3[01])\\.(0[1-9]|1[0-2])\\.\\d{4}$";
        return Pattern.matches(regex, date);
    }

    private static boolean isValidPhoneNumberFormat(String phoneNumber) {
        String regex = "^[0-9]+$";
        return Pattern.matches(regex, phoneNumber);
    }

    private static boolean isValidGender(String gender) {
        return gender.equalsIgnoreCase("f") || gender.equalsIgnoreCase("m");
    }
}

class InvalidDataFormatException extends Exception {
    public InvalidDataFormatException(String message) {
        super(message);
    }
}

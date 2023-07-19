package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class InvalidDataFormatException extends Exception {
    public InvalidDataFormatException(String message) {
        super(message);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Введите данные в формате: Фамилия Имя Отчество датарождения номертелефона пол");
            String input = scanner.nextLine();

            String[] data = input.split(" ");
            if (data.length != 6) {
                throw new InvalidDataFormatException("Неверное количество данных. Ожидалось 6 значений.");
            }

            String lastName = data[0];
            String firstName = data[1];
            String middleName = data[2];
            String birthDate = data[3];
            long phoneNumber = Long.parseLong(data[4]);
            char gender = data[5].charAt(0);

            if (gender != 'f' && gender != 'm') {
                throw new InvalidDataFormatException("Неверный формат пола. Поле 'пол' должно быть 'f' или 'm'.");
            }

            String fileName = lastName + ".txt";
            String content = lastName + " " + firstName + " " + middleName + " " + birthDate + " " + phoneNumber + " " + gender;

            try (FileWriter writer = new FileWriter(fileName, true)) {
                writer.write(content + System.lineSeparator());
                System.out.println("Данные успешно записаны в файл " + fileName);
            } catch (IOException e) {
                System.out.println("Ошибка при записи в файл: " + e.getMessage());
            }
        } catch (InvalidDataFormatException e) {
            System.out.println("Ошибка в формате данных: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат номера телефона. Поле 'номертелефона' должно быть целым числом.");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}

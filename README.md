# Java_exeptions

**Задание 1**
```Java
package org.example;

import java.util.Scanner;

public class Answer {
    public static float getUserFloatInput() {
        Scanner scanner = new Scanner(System.in);
        float result = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print("Введите дробное число: ");
                result = Float.parseFloat(scanner.nextLine());
                validInput = true; // Если ввод успешно преобразован в float, выходим из цикла
            } catch (NumberFormatException e) {
                System.out.println("Ошибка! Введите корректное дробное число.");
            }
        }

        return result;
    }

    public static void main(String[] args) {
        float userInput = getUserFloatInput();
        System.out.println("Вы ввели: " + userInput);
    }
}
```
**Задание 2**
Для исправления кода и предотвращения деления на ноль (ошибка, которая возникает в вашем коде), мы должны переместить операцию деления в блок try. Поскольку в данном случае мы пытаемся делить значение массива на d, а d у нас равно нулю, это приведет к исключению ArithmeticException. Вот исправленный код:

```java
public class Exept {
    public static void main(String[] args) {
        int[] intArray = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        try {
            int d = 0;
            double catchedRes1 = intArray[8] / (double) d;
            System.out.println("catchedRes1 = " + catchedRes1);
        } catch (ArithmeticException e) {
            System.out.println("Catching exception: " + e);
        }
    }
}
```
Добавилено (double) перед d в операции деления, чтобы выполнить деление на дробное число вместо целочисленного, что позволяет избежать исключения деления на ноль. Теперь программа должна вывести сообщение "Catching exception: java.lang.ArithmeticException: / by zero" вместо возникновения ошибки деления на ноль.

**Задание 3**

В коде есть две проблемы:

1. Метод `printSum` объявлен с исключением `FileNotFoundException`, но в нем нет кода, который вызывает это исключение. Мы можем удалить объявление `throws FileNotFoundException`, поскольку его отсутствие не вызовет ошибку.

2. В блоке `catch` после вывода сообщения "Что-то пошло не так...", следует удалить остальные блоки `catch`. Потому что в коде, который предоставлен, исключение `Throwable` уже будет обрабатывать все возможные исключения, включая `NullPointerException` и `IndexOutOfBoundsException`. Удаление этих лишних блоков `catch` поможет избежать недостижимого кода.


```java
import java.io.FileNotFoundException;

public class Answer {
    public static void main(String[] args) {
        try {
            int a = 90;
            int b = 3;
            System.out.println(a / b);
            printSum(23, 234);
            int[] abc = { 1, 2 };
            abc[3] = 9;
        } catch (Throwable ex) {
            System.out.println("Что-то пошло не так...");
        }
    }

    public static void printSum(Integer a, Integer b) {
        System.out.println(a + b);
    }
}
```

Теперь код должен работать без ошибок, и исключение `Throwable` будет перехватывать все возможные исключения, которые могут возникнуть в блоке `try`.

**Задание 4**
Разработайте программу, которая выбросит Exception, когда пользователь вводит пустую строку. Пользователю должно показаться сообщение, что пустые строки вводить нельзя.

```java
import java.util.Scanner;

public class EmptyStringException extends Exception {
    public EmptyStringException(String message) {
        super(message);
    }
}

public class Answer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Введите текст: ");
            String input = scanner.nextLine();

            if (input.isEmpty()) {
                throw new EmptyStringException("Пустые строки вводить нельзя!");
            }

            // Дальнейшая обработка введенного текста, если не пустой
            System.out.println("Вы ввели: " + input);
        } catch (EmptyStringException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
```

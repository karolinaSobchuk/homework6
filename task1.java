//Подумать над структурой класса Ноутбук для магазина техники — выделить поля и методы. Реализовать в Java.
//
//Создать множество ноутбуков.
//
//Написать метод, который будет запрашивать у пользователя критерий (или критерии) фильтрации и выведет
// ноутбуки, отвечающие фильтру. Критерии фильтрации можно хранить в Map. Например:
//
//“Введите цифру, соответствующую необходимому критерию:
//
//1 - ОЗУ
//
//2 - Объём ЖД
//
//3 - Операционная система
//
//4 - Цвет …
//
//Далее нужно запросить минимальные и максимальные значения для указанных критериев — сохранить
// параметры фильтрации можно также в Map.
//Отфильтровать ноутбуки их первоначального множества и вывести проходящие по условиям.

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;
public class task1 { public static void main(String[] args) {
    notebook nob1 = new notebook("Hyundai", "HyBook 14 HT14CCIC44EGP", 4, 1000, "Windows 10", "серый");
    notebook nob2 = new notebook("HP", "EliteBook 845 G8 (4R9R7EA)", 16, 256, "Windows 10 Pro", "серый");
    notebook nob3 = new notebook("Lenovo", " Legion 5 17ACH6", 16, 512, "Windows 11", "черный");
    notebook nob4 = new notebook("MSI", "GF63 Thin 11UC-255XRU", 8, 256, "Windows 11", "черный");
    notebook nob5 = new notebook("Asus", "VivoBook 15 M1502I 90NB0Y52-M002R0", 8, 512, "Windows 10", "серебристый");
    notebook nob6 = new notebook("Tecno", "Megabook T1", 12, 256, "Без ОС", "серый");


    HashSet<notebook> notebooks = new HashSet<notebook>(
            Arrays.asList(nob1, nob2, nob3, nob4, nob5, nob6));

    Scanner sc = new Scanner(System.in, "cp866");
    System.out.println();
    System.out.println("Добро пожаловать в интернет-магазин 'ТехноСтолица'!\n" +
            "Введите цифру, соответствующую необходимому критерию: \n");
    boolean filter = true;
    while (filter) {
        System.out.println("1 - Выбрать по ОЗУ.\n" +
                "2 - Выбрать по объему жесткого диска.\n" +
                "3 - Выбрать по операционной системе.\n" +
                "4 - Выбрать по цвету.\n" +
                "5 - Посмотреть все модели.\n" +
                "0 - Для выхода из каталога.");

        String operation = sc.nextLine();
        switch (operation) {
            case "1":
                filterRAM(notebooks, sc);
                break;
            case "2":
                filterHardDisk(notebooks, sc);
                break;
            case "3":
                filterOS(notebooks, sc);
                break;
            case "4":
                filterColor(notebooks, sc);
                break;
            case "5":
                showCatalog(notebooks);
                break;
            case "0":
                System.out.println("Ждем вас снова!");
                filter = false;
                break;
            default:
                System.out.println("Упс! Что-то пошло не так :(");
                System.out.println();
                break;
        }
    }

}
    public static void filterRAM(HashSet<notebook> notebooks, Scanner scanner) {
        TreeSet<Integer> ram = new TreeSet<>();
        for (notebook note : notebooks) {
            ram.add(note.getRam());
        }
        System.out.println();
        System.out.println("В нашем каталоге представлены ноутбуки с оперативной памятью "
                + ram.toString().replaceAll("^\\[|\\]$", "") + " Гб\n" +
                "Введите интересующее значение: ");

        String oper = scanner.nextLine();
        int filter = Integer.parseInt(oper);
        if (ram.contains(filter)) {
            System.out.println();
            System.out.println("Подходящие модели по вашему запросу: ");
            System.out.println();
            for (notebook note : notebooks) {
                if (note.getRam() == filter) {
                    note.showInfo();
                }
            }
        } else {
            System.out.println("Неправильное значение");
            filterRAM(notebooks, scanner);
        }

    }
    public static void filterHardDisk(HashSet<notebook> notebooks, Scanner scanner) {
        TreeSet<Integer> hardDisk = new TreeSet<>();
        for (notebook note : notebooks) {
            hardDisk.add(note.getHardDisk());
        }
        System.out.println();
        System.out.println("В нашем каталоге представлены ноутбуки с объемом жесткого диска "
                + hardDisk.toString().replaceAll("^\\[|\\]$", "") + " Гб\n" +
                "Введите интересующее значение: ");

        String oper = scanner.nextLine();
        int filter = Integer.parseInt(oper);
        if (hardDisk.contains(filter)) {
            System.out.println();
            System.out.println("Подходящие модели по вашему запросу: ");
            System.out.println();
            for (notebook note : notebooks) {
                if (note.getHardDisk() == filter) {
                    note.showInfo();
                }
            }
        } else {
            System.out.println("Неправильное значение");
            filterHardDisk(notebooks, scanner);
        }
    }
    // Функция поиска по ОС
    public static void filterOS(HashSet<notebook> notebooks, Scanner scanner) {
        TreeSet<String> operSystems = new TreeSet<>();
        for (notebook note : notebooks) {
            operSystems.add(note.getOperSystem());
        }
        System.out.println();
        System.out.println("В нашем каталоге представлены операционные системы: "
                + operSystems.toString().replaceAll("^\\[|\\]$", "") + "\n" +
                "Введите интересующую ОС: ");

        String oper = scanner.nextLine().toUpperCase();
        if (operSystems.contains(oper)) {
            System.out.println();
            System.out.println("Подходящие модели по вашему запросу: ");
            System.out.println();
            for (notebook note : notebooks) {
                if (note.getOperSystem().equals(oper)) {
                    note.showInfo();
                }
            }
        } else {
            System.out.println("Нет такой ОС");
            filterOS(notebooks, scanner);

        }
    }

    public static void filterColor(HashSet<notebook> notebooks, Scanner scanner){
        TreeSet<String> colors = new TreeSet<>();
        for (notebook note: notebooks){
            colors.add(note.getColor());
        }
        System.out.println();
        System.out.println("В нашем каталоге представлены ноутбуки следующих цветов: "
                + colors.toString().replaceAll("^\\[|\\]$", "") + "\n" +
                "Введите интересующий цвет: ");

        String oper = scanner.nextLine().toLowerCase();
        if (colors.contains(oper)) {
            System.out.println();
            System.out.println("Подходящие модели по вашему запросу: ");
            System.out.println();
            for (notebook note : notebooks) {
                if (note.getColor().equals(oper)) {
                    note.showInfo();
                }
            }
        } else {
            System.out.println("Нет такого цвета");
            filterColor(notebooks, scanner);
        }
    }
    public static void showCatalog(HashSet<notebook> notebooks){
        System.out.println();
        System.out.println("Полный каталог ноутбуков: ");
        System.out.println();
        for (notebook note : notebooks) {
            note.showInfo();
        }
    }
}


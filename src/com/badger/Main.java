package com.badger;

import com.badger.diary.Diary;

import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {

    private static final String[] diaryCommands = new String[]{
            "SelectDay [дата], чтобы открыть план на определенную дату",
            "CreateDay [дата], чтобы создать план на определенную дату",
            "DeleteDay [дата], чтобы удалить план по определенной дате",
            "Exit, чтобы закончить работу"
    };

    private static final String[] planCommands = new String[]{
            "CompleteTask [номер задачи], чтобы отмеить задачу как выполненную",
            "FailTask [номер задачи], чтобы отметить задачу как проваленную",
            "CreateTask [текст задачи], чтобы создать новую задачу",
            "RemoveTask [номер задачи], чтобы удалить задачу"
    };

    private static final String WARNING_INVALID_DATA = "Дата введена неверно, формат dd.MM.yyyy";

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public static void main(String[] args) {

        Diary diary = Diary.getInstance();
        Scanner input = new Scanner(System.in);

        boolean flag = true;

        while (flag) {

            if (diary.getSelectedDay() != null) {
                diary.getSelectedDay().showDayPlan();

                System.out.println("_".repeat(15));

                for (String commandText : planCommands) {
                    System.out.println(commandText);
                }
            }

            for (String commandText : diaryCommands) {
                System.out.println(commandText);
            }

            System.out.println("Введите команду, без квадратный скобок (пример: SelectDay 22.04.2019): ");

            String actualCommand = input.nextLine().trim();

            String actualArgument;
            boolean validData, dayIsSelected, isIndex;

            if (actualCommand.split("\\s").length > 1) {
                actualArgument = actualCommand.substring(actualCommand.split("\\s")[0].length()).trim();
                validData = isValidDate(actualArgument);
            } else {
                validData = false;
                actualArgument = "None";
            }

            actualCommand = actualCommand.split("\\s")[0];

            dayIsSelected = diary.getSelectedDay() != null;

            try {
                Integer.parseInt(actualArgument);
                isIndex = true;
            } catch (NumberFormatException e) {
                isIndex = false;
            }

            switch (actualCommand) {

                case "CompleteTask":
                    if (dayIsSelected && isIndex) {
                        if (!diary.getSelectedDay().acceptScheduleItem(Integer.parseInt(actualArgument))) {
                            System.out.println("Неверно введен номер задачи");
                        }
                    } else {
                        System.out.println("Команда введена неправильно");
                    }
                    break;
                case "FailTask":
                    if (dayIsSelected && isIndex) {
                        if (!diary.getSelectedDay().cancelScheduleItem(Integer.parseInt(actualArgument))) {
                            System.out.println("Неверно введен номер задачи");
                        }
                    } else {
                        System.out.println("Команда введена неправильно");
                    }
                    break;
                case "CreateTask":
                    if (dayIsSelected) {
                        if (!diary.getSelectedDay().addScheduleItem(actualArgument)) {
                            System.out.println("Такая задача уже существует");
                        }
                    } else {
                        System.out.println("В данный момент команда не доступна, сначала выберите день");
                    }
                    break;
                case "RemoveTask":
                    if (dayIsSelected) {
                        if (!diary.getSelectedDay().deleteScheduleItem(Integer.parseInt(actualArgument))) {
                            System.out.println("Неверно введен номер задачи");
                        }
                    } else {
                        System.out.println("В данный момент команда не доступна, сначала выберите день");
                    }

                    break;

                case "SelectDay":

                    if (validData) {
                        if (!diary.selectDay(actualArgument)) {
                            System.out.println("Плана на этот день нет в базе");
                        }
                    } else {
                        System.out.println(WARNING_INVALID_DATA);
                    }
                    break;

                case "CreateDay":

                    if (validData) {
                        if (!diary.createDay(actualArgument)) {
                            System.out.println("План на эту дату уже существует");
                        }
                    } else {
                        System.out.println(WARNING_INVALID_DATA);
                    }
                    break;

                case "DeleteDay":

                    if (validData) {
                        if (!diary.removeDay(actualArgument)) {
                            System.out.println("Плана на это день нет в базе");
                        }
                    } else {
                        System.out.println(WARNING_INVALID_DATA);
                    }

                    break;

                case "Exit":

                    flag = false;
                    break;

                default:
                    System.out.println("Некорректный ввод");
            }

            System.out.println("\n".repeat(2));
        }
    }

    public static boolean isValidDate(String date) {

        try {

            dateFormat.parse(date);
            return true;

        } catch (Exception e) {
            return false;
        }
    }
}


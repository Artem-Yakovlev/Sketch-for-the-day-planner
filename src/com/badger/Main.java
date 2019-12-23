package com.badger;

import com.badger.correctdata.ValidCheck;
import com.badger.diary.Diary;

import java.util.Scanner;

public class Main {

    private static final String[] DIARY_COMMANDS = new String[]{
            "SelectDay [дата], чтобы открыть план на определенную дату",
            "CreateDay [дата], чтобы создать план на определенную дату",
            "DeleteDay [дата], чтобы удалить план по определенной дате",
            "Exit, чтобы закончить работу"
    };

    private static final String[] PLAN_COMMANDS = new String[]{
            "CompleteTask [номер задачи], чтобы отмеить задачу как выполненную",
            "FailTask [номер задачи], чтобы отметить задачу как проваленную",
            "CreateTask [текст задачи], чтобы создать новую задачу",
            "RemoveTask [номер задачи], чтобы удалить задачу"
    };

    private static final String WARNING_INVALID_DATA = "Дата введена неверно, формат dd.MM.yyyy";
    private static final String PLAN_NOT_FOUND = "Плана на этот день нет в базе";
    private static final String COMMAND_UNAVAILABLE = "В данный момент команда не доступна, сначала выберите день";
    private static final String COMMAND_INCORRECTLY = "Команда введена неправильно";
    private static final String INVALID_INDEX = "Неверно введен индекс";
    private static final String OBJECT_ALREADY_EXIST = "Такой объект уже существует";

    public static void main(String[] args) {

        Diary diary = Diary.getInstance();
        Scanner input = new Scanner(System.in);

        boolean flag = true;

        while (flag) {

            if (diary.getSelectedDay() != null) {
                diary.getSelectedDay().showDayPlan();

                System.out.println("_".repeat(15));

                for (String commandText : PLAN_COMMANDS) {
                    System.out.println(commandText);
                }
            }

            for (String commandText : DIARY_COMMANDS) {
                System.out.println(commandText);
            }

            System.out.println("Введите команду, без квадратный скобок (пример: SelectDay 22.04.2019): ");

            String actualCommand = input.nextLine().trim();

            String actualArgument = "None";
            boolean validDate;

            if (actualCommand.split("\\s").length > 1) {
                actualArgument = actualCommand.substring(actualCommand.split("\\s")[0].length()).trim();
                validDate = ValidCheck.isDate(actualArgument);
            } else {
                validDate = false;
            }

            actualCommand = actualCommand.split("\\s")[0];

            switch (actualCommand) {

                case "CompleteTask":
                    if (diary.getSelectedDay() != null && ValidCheck.isNumber(actualArgument)) {
                        if (!diary.getSelectedDay().acceptScheduleItem(Integer.parseInt(actualArgument))) {
                            System.out.println(INVALID_INDEX);
                        }
                    } else {
                        System.out.println(COMMAND_INCORRECTLY);
                    }
                    break;
                case "FailTask":
                    if (diary.getSelectedDay() != null && ValidCheck.isNumber(actualArgument)) {
                        if (!diary.getSelectedDay().cancelScheduleItem(Integer.parseInt(actualArgument))) {
                            System.out.println(INVALID_INDEX);
                        }
                    } else {
                        System.out.println(COMMAND_INCORRECTLY);
                    }
                    break;
                case "CreateTask":
                    if (diary.getSelectedDay() != null) {
                        if (!diary.getSelectedDay().addScheduleItem(actualArgument)) {
                            System.out.println(OBJECT_ALREADY_EXIST);
                        }
                    } else {
                        System.out.println(COMMAND_UNAVAILABLE);
                    }
                    break;
                case "RemoveTask":
                    if (diary.getSelectedDay() != null) {
                        if (!diary.getSelectedDay().deleteScheduleItem(Integer.parseInt(actualArgument))) {
                            System.out.println(INVALID_INDEX);
                        }
                    } else {
                        System.out.println(COMMAND_UNAVAILABLE);
                    }

                    break;

                case "SelectDay":

                    if (validDate) {
                        if (!diary.selectDay(actualArgument)) {
                            System.out.println(PLAN_NOT_FOUND);
                        }
                    } else {
                        System.out.println(WARNING_INVALID_DATA);
                    }
                    break;

                case "CreateDay":

                    if (validDate) {
                        if (!diary.createDay(actualArgument)) {
                            System.out.println(OBJECT_ALREADY_EXIST);
                        }
                    } else {
                        System.out.println(WARNING_INVALID_DATA);
                    }
                    break;

                case "DeleteDay":

                    if (validDate) {
                        if (!diary.removeDay(actualArgument)) {
                            System.out.println(PLAN_NOT_FOUND);
                        }
                    } else {
                        System.out.println(WARNING_INVALID_DATA);
                    }

                    break;

                case "Exit":

                    flag = false;
                    break;

                default:
                    System.out.println(COMMAND_INCORRECTLY);
            }

            System.out.println("\n".repeat(2));
        }
    }
}


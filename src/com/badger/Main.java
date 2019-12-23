package com.badger;

import com.badger.correctdata.ValidCheck;
import com.badger.diary.Diary;
import java.util.Scanner;

/**
 * @author Artem Yakovlev
 * @version 0.3
 *
 * Здесь вызываются все команды и выполняется основной процесс программы.
 * */
public class Main {

    // Команды для изменения словаря
    private static final String[] DIARY_COMMANDS = new String[]{
            "> SelectDay [дата], чтобы открыть план на определенную дату",
            "> CreateDay [дата], чтобы создать план на определенную дату",
            "> DeleteDay [дата], чтобы удалить план по определенной дате",
            "> Exit, чтобы закончить работу"
    };

    // Команды для изменения плана
    private static final String[] PLAN_COMMANDS = new String[]{
            "> CompleteTask [номер задачи], чтобы отмеить задачу как выполненную",
            "> FailTask [номер задачи], чтобы отметить задачу как проваленную",
            "> CreateTask [текст задачи], чтобы создать новую задачу",
            "> RemoveTask [номер задачи], чтобы удалить задачу"
    };

    // Константы для выводимых на экран подсказок
    private static final String WARNING_INVALID_DATA = "Дата введена неверно, формат dd.MM.yyyy";
    private static final String PLAN_NOT_FOUND = "Плана на этот день нет в базе";
    private static final String COMMAND_UNAVAILABLE = "В данный момент команда не доступна, сначала выберите день";
    private static final String COMMAND_INCORRECTLY = "Команда введена неправильно";
    private static final String INVALID_INDEX = "Неверно введен индекс";
    private static final String OBJECT_ALREADY_EXIST = "Такой объект уже существует";
    private static final String REQUEST_FOR_INPUT = "\nВведите команду, без квадратный скобок (SelectDay 22.04.2019): ";
    private static final String PLAN_IS_EMPTY = "Пока пуст, но Вы можете это исправить";
    private static final String PLAN_NAME = "План дня на ";

    public static void main(String[] args) {

        //Создаем дневник и сканер
        Diary diary = Diary.getInstance();
        Scanner input = new Scanner(System.in);

        //Флаг для цикла
        boolean mainProcess = true;

        while (mainProcess) {

            // Разделение текста
            System.out.println("\n".repeat(2));

            //Если страница дневника открыта
            if (diary.getSelectedDay() != null) {
                // Выводим список дел на этот день
                System.out.println(PLAN_NAME + diary.getSelectedDay().getDate() + ":");
                for (int i = 0; i < diary.getSelectedDay().getDayPlan().size(); i++)
                    System.out.println(i + ") " + diary.getSelectedDay().getDayPlan().get(i).getTextOfSchedule());

                // Вывод текста, если план пустой
                if (diary.getSelectedDay().getDayPlan().size() == 0)
                    System.out.println(PLAN_IS_EMPTY);
                System.out.println();

                // Выводим список команд для редактирования плана
                for (String commandText : PLAN_COMMANDS) {
                    System.out.println(commandText);
                }
            }

            // Выводим список команд для редактирования дневника
            for (String commandText : DIARY_COMMANDS) {
                System.out.println(commandText);
            }

            // Запрашиваем команду
            System.out.println(REQUEST_FOR_INPUT);
            String query = input.nextLine().trim();

            // Считываем команду
            String actualCommand = query.split("\\s")[0];
            String actualArgument = "";

            // Если 2 или более слова, то считываем аргумент
            if (query.split("\\s").length > 1) {
                actualArgument = query.substring(actualCommand.length()).trim();
            }
            // Вызываем нужную команду
            switch (actualCommand) {

                // Задание выполнено
                case "CompleteTask":
                    if (diary.getSelectedDay() != null && ValidCheck.isNumber(actualArgument)) {

                        if (!diary.getSelectedDay().acceptScheduleItem(Integer.parseInt(actualArgument)))
                            System.out.println(INVALID_INDEX);

                    } else {
                        System.out.println(COMMAND_INCORRECTLY);
                    }
                    break;

                // Задание провалено
                case "FailTask":
                    if (diary.getSelectedDay() != null && ValidCheck.isNumber(actualArgument)) {

                        if (!diary.getSelectedDay().cancelScheduleItem(Integer.parseInt(actualArgument)))
                            System.out.println(INVALID_INDEX);

                    } else {
                        System.out.println(COMMAND_INCORRECTLY);
                    }
                    break;

                // Добавить новое задание
                case "CreateTask":
                    if (diary.getSelectedDay() != null) {

                        if (!diary.getSelectedDay().addScheduleItem(actualArgument))
                            System.out.println(OBJECT_ALREADY_EXIST);

                    } else {
                        System.out.println(COMMAND_UNAVAILABLE);
                    }
                    break;

                // Удалить задание
                case "RemoveTask":
                    if (diary.getSelectedDay() != null  && ValidCheck.isNumber(actualArgument)) {

                        if (!diary.getSelectedDay().deleteScheduleItem(Integer.parseInt(actualArgument)))
                            System.out.println(INVALID_INDEX);

                    } else {
                        System.out.println(COMMAND_UNAVAILABLE);
                    }

                    break;

                // Выбрать план по дате
                case "SelectDay":
                    if (ValidCheck.isDate(actualArgument)) {

                        if (!diary.selectDay(actualArgument))
                            System.out.println(PLAN_NOT_FOUND);

                    } else {
                        System.out.println(WARNING_INVALID_DATA);
                    }
                    break;

                // Создать план на новую дату
                case "CreateDay":

                    if (ValidCheck.isDate(actualArgument)) {

                        if (!diary.createDay(actualArgument))
                            System.out.println(OBJECT_ALREADY_EXIST);

                    } else {
                        System.out.println(WARNING_INVALID_DATA);
                    }
                    break;

                // Удалить план по дате
                case "DeleteDay":

                    if (ValidCheck.isDate(actualArgument)) {

                        if (!diary.removeDay(actualArgument))
                            System.out.println(PLAN_NOT_FOUND);

                    } else {
                        System.out.println(WARNING_INVALID_DATA);
                    }
                    break;

                // Выход из программы
                case "Exit":
                    mainProcess = false;
                    break;
                default:
                    System.out.println(COMMAND_INCORRECTLY);
            }
        }
    }
}


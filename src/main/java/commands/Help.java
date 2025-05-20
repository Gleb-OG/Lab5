package commands;

import interfaces.Command;
import managers.CollectionManager;


public class Help implements Command {
    @Override
    public void execute(CollectionManager collectionManager, String[] args) {
        String helpMessage = """
                - help: Справка по доступным командам
                - info: Информация о коллекции
                - show: Вывести все элементы коллекции
                - insert null {element}: Добавить элемент
                - update id {element}: Обновить значение по ключу
                - remove_key null: Удалить элемент по ключу
                - clear: Очистить коллекцию
                - save: Сохранить коллекцию в файл
                - execute_script file_name: Запустить скрипт
                - exit: Выйти без сохранения
                - remove_greater {element}: Удалить элементы, превышающие введенный
                - replace_if_lowe null {element}: Заменить значение по ключу, если новое значение меньше старого
                - remove_lower_key null: Удалить все элементы, ключ которых меньше, чем заданный
                - sum_of_annual_turnover: Вывести сумму годового оборота всех организаций
                - filter_by_annual_turnover annualTurnover: Вывести организации, годовой оборот которых равен введенному
                - filter_greater_than_official_address officialAddress: Вывести организации, официальный адрес которых больше введенного.""";
        System.out.println(helpMessage);
    }
}

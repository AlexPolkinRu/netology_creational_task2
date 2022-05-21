import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Random rnd = new Random();
        List<Integer> list = new ArrayList<>();
        List<Integer> filteredList;
        Filter filter;

        Logger logger = Logger.getInstance();

        logger.log("Запускаем программу");
        logger.log("Просим пользователя ввести входные данные для списка");

        System.out.print("Введите размер списка: ");
        int listSize = Integer.parseInt(scanner.next());

        System.out.print("Введите верхнюю границу значений элементов в списке: ");
        int maxValue = Integer.parseInt(scanner.next());

        logger.log("Создаём и наполняем список");

        System.out.print("Вот случайный список:");
        for (int i = 0; i < listSize; i++) {
            list.add(rnd.nextInt(maxValue));
            System.out.print(" " + list.get(list.size() - 1));
        }
        System.out.println();

        logger.log("Просим пользователя ввести входные данные для фильтрации");

        System.out.print("Введите пороговое число для фильтрации списка: ");
        int thresholdValue = Integer.parseInt(scanner.next());

        filter = new Filter(thresholdValue);

        logger.log("Запускаем фильтрацию");

        filteredList = filter.filterOut(list);

        logger.log("Прошло фильтр " + filteredList.size() + " элемент[а|ов] из " + list.size());
        logger.log("Выводим результат на экран");
        System.out.print("Отфильтрованный список:");
        for (int x : filteredList) {
            System.out.print(" " + x);
        }
        System.out.println();

        logger.log("Завершаем программу");
    }
}

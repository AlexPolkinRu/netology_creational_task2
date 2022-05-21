# Задача «Логгер»

## Описание
В этом задании попрактикуемся с шаблоном *Singleton* (*Одиночка*). Мы пишем программу, которая будет каждый свой шаг обильно логгировать в консоль, но не напрямую через `System.out.println`, а через объект-логгер нашего собственного класса-синглтона.

### Функционал программы
1. Программа здоровается с пользователем, просит ввести два числа: размер списка `N` и верхнюю границу значений элементов в списке `M`.
2. Программа создаёт список `ArrayList` из `N` элементов и заполняет их случайными числами от `0` до `M`.
3. Программа просит пользователя ввести число `f` для фильтрации списка.
4. Программа создаёт объект `filter` вашего класса `Filter`, передав в конструктор значение `f`
5. Программа вызывает у `filter` метод `List<Integer> filterOut(List<Integer> list)`, передавая созданный случайный список в качестве параметра и принимая в качестве ответа список, который идентичен исходному, если пропустить элементы меньше `f`
6. Программа выводит итоговый список на экран и завершает свою работу

### Логгер
Каждое действие программы, будь то некорректный ввод пользователем входных данных или решение не добавлять элемент в результирующий список в методе `filterOut`, должно быть залоггировано. Для этого надо создать синглтон-класс `Logger` у которого будет метод `void log(String msg)` для вывода на экран сообщения. Сообщение должно выводиться в формате: `[<дата и номер сообщения>] <текст сообщения>`, где `<номер сообщения>` это порядковый номер выводимого логгером сообщения, а `<текст сообщения>` это значение параметра `msg`.

## Пример запуска
*Жирным шрифтом в примере это ввод пользователем данных*
> [31.12.2019 15:38:22 1] Запускаем программу  <br/>
> [31.12.2019 15:38:22 2] Просим пользователя ввести входные данные для списка <br/>
> Введите размер списка: **7**<br/>
> Введите верхнюю границу для значений: **10** <br/>
> [31.12.2019 15:38:23 3] Создаём и наполняем список <br/>
> Вот случайный список: 3 5 5 1 0 3 6 <br/>
> [31.12.2019 15:38:23 4] Просим пользователя ввести входные данные для фильтрации <br/>
> Введите порог для фильтра: **4** <br/>
> [31.12.2019 15:38:23 5] Запускаем фильтрацию <br/>
> [31.12.2019 15:38:23 6] Элемент "3" не проходит <br/>
> [31.12.2019 15:38:23 7] Элемент "5" проходит <br/>
> [31.12.2019 15:38:23 8] Элемент "5" проходит <br/>
> [31.12.2019 15:38:23 9] Элемент "1" не проходит <br/>
> [31.12.2019 15:38:23 10] Элемент "0" не проходит <br/>
> [31.12.2019 15:38:24 11] Элемент "3" не проходит <br/>
> [31.12.2019 15:38:24 12] Элемент "6" проходит <br/>
> [31.12.2019 15:38:24 13] Прошло фильтр 3 элемента из 7 <br/>
> [31.12.2019 15:38:24 14] Выводим результат на экран <br/>
> Отфильтрованный список: 5 5 6 <br/>
> [31.12.2019 15:38:24 15] Завершаем программу <br/>

## Реализация
1. Создайте класс `Logger` с методом `void log(String msg)` для логгирования сообщения в консоль описанным выше форматом (для поддержки счётчика сообщений заведите у логгера и инкрементируйте при логгировании числовое поле `int num`).
```java
public class Logger {
  protected int num = 1;

  public void log(String msg) {
    System.out.println("[" + num++ + "] " + msg);
  }
}
```
2. Примените шаблон *Singleton* (*Одиночка*) к классу `Logger` чтобы во всей программе у этого класса был только один объект, для чего подобно примеру из лекции сделайте конструктор приватным и создайте статичный метод `Logger getInstance()` для получения одного и того же объекта класса `Logger` при любом повторном вызове (сам же этот объект храните в статичном приватном поле `private static Logger instance`).
```java
public class Logger {
  //...

  // В этом поле храним ссылку на тот
  // единственный объект этого класса
  // который будем отдавать пользователям
  private static Logger logger;

  // Запрещаем пользователям пользоваться
  // конструктором нашего класса
  private Logger() {}

  // Пользователи которым нужен объект
  // нашего класса получают всегда один
  // и тот же объект, который мы храним
  // в приватном статичном поле, которое
  // мы заполняем в этом методе если оно
  // до того не было заполнено
  public static Logger getInstance() {
    //...
  }
}
```
3. Создайте класс `Filter` с конструктором, принимающим параметр `f`, и методом `List<Integer> filterOut(List<Integer> list)` для фильтрации списка. Используйте внутри него логгирование (можно как в примере вывода выше), но не передавайте логгер объекту фильтра через параметры, а сделайте `Logger logger = Logger.getInstance()` прямо там в коде, где он нужен.
```java
public class Filter {
  protected int treshold;

  public Filter(int treshold) {
    this.treshold = treshold;
  }

  public List<Integer> filterOut(List<Integer> source) {
    Logger logger = Logger.getInstance();
    List<Integer> result = new ArrayList<>();
    //..
    return result;
  }
}
```
4. Создайте класс `Main`, в котором вы будете коммуницировать с пользователем и реализовывать функционал программы, не забывая обильно логгировать свои шаги через написанный вами логгер.
```java
public class Main {
  public static void main(String[] args) {
    Logger logger = Logger.getInstance();
    //...
  }
}
```
5. Для заполнения списка `ArrayList` случайными числами используйте генератор случайных чисел `Random random = new Random()` и его метод `random.nextInt(maxValue)`.
6. Протестируйте работу программы. Не забывайте про правила форматирования кода (для автоформата можете выделить код в идее и нажать **Ctrl+Alt+L**).
[(https://skr.sh/i/210522/bQ8aeUpV.jpg?download=1&name=%D0%A1%D0%BA%D1%80%D0%B8%D0%BD%D1%88%D0%BE%D1%82%2021-05-2022%2023:22:05.jpg)]

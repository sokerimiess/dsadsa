import java.util.*;

public class Main {
        //подключение сканера для хранения
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        // Ввод значений для ArrayList
        System.out.print("ArrayList: ");
        String[] arrayValues = scanner.nextLine().split(" ");
        for (String value : arrayValues) {
            arrayList.add(Integer.parseInt(value));
        }

        // Ввод значений для LinkedList
        System.out.print("LinkedList: ");
        String[] linkedValues = scanner.nextLine().split(" ");
        for (String value : linkedValues) {
            linkedList.add(Integer.parseInt(value));
        }

        // Объединение списков с сортировкой по индексу
        List<Integer> combinedList = new ArrayList<>();
        int maxSize = Math.max(arrayList.size(), linkedList.size());
        for (int i = 0; i < maxSize; i++) {
            if (i < arrayList.size()) {
                combinedList.add(arrayList.get(i));
            }
            if (i < linkedList.size()) {
                combinedList.add(linkedList.get(i));
            }
        }
        //вывод результата пользователю
        System.out.println(combinedList);
    }
}

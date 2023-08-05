import java.util.Arrays;
import java.util.List;

public class MyArrayList<E> implements MyList<E> {

    private Object[] elements;
    private int size;

    // Конструктор
    public MyArrayList() {
        this.elements = new Object[10]; // Начальный размер массива 10
        this.size = 0;
    }

    @Override
    public int size() { //размер списка
        return size;
    }

    @Override
    public boolean contains(Object o) { //наличие в спискке
        for (int i = 0; i < size; i++) {
            if (o.equals(elements[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void add(E item) {//элемент в конец
        if (size == elements.length) {
            ensureCapacity(); // проверка на наличие места, если нет, увеличиваем размер массива
        }
        elements[size++] = item;
    }

    @Override
    public void add(E item, int index) { //добавление элемента по индекву
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        if (size == elements.length) {
            ensureCapacity();
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);//сдвиг после указанного индекса
        elements[index] = item;
        size++;
    }

    @Override
    public boolean remove(E item) {
        for (int i = 0; i < size; i++) {
            if (item.equals(elements[i])) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public E remove(int index) { //удаление по индексу
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        E removedElement = (E) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null;
        return removedElement;
    }

    @Override
    public void clear() { //очистка списка
        Arrays.fill(elements, null);
        size = 0;
    }

    @Override
    public E get(int index) { //получение элемента по индексу
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        return (E) elements[index];
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (o.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void sort() {
        Arrays.sort(elements, 0, size);
    }

    @Override
    public void addAll(List<E> additionalNumbers) {

    }

    private void ensureCapacity() { //увеличение размера
        int newCapacity = elements.length * 2;
        elements = Arrays.copyOf(elements, newCapacity);
    }
}

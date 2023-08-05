import java.util.List;

public class MyLinkedList<E> implements MyList<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    private static class Node<E> { //для представления узла
        E data;
        Node<E> next;
        Node<E> prev;

        Node(E element) {
            data = element;
            next = null;
            prev = null;
        }
    }

    @Override
    public int size() { //размер
        return size;
    }

    @Override //наличие
    public boolean contains(Object o) {
        Node<E> currentNode = head;
        while (currentNode != null) {
            if (o.equals(currentNode.data)) {
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    @Override
    public void add(E item) { //новый узел
        Node<E> newNode = new Node<>(item);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else { //последний в списке
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++; //увеличения списка
    }

    @Override
    public void add(E item, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        if (index == size) {
            add(item);
        } else {
            Node<E> newNode = new Node<>(item);
            if (index == 0) {
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            } else {
                Node<E> currentNode = getNode(index);
                Node<E> prevNode = currentNode.prev;

                newNode.next = currentNode;
                newNode.prev = prevNode;

                prevNode.next = newNode;
                currentNode.prev = newNode;
            }
            size++;
        }
    }

    @Override
    public boolean remove(E item) {//удаление элемента
        Node<E> currentNode = head;
        while (currentNode != null) {
            if (item.equals(currentNode.data)) {
                removeNode(currentNode);
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        Node<E> nodeToRemove = getNode(index);
        removeNode(nodeToRemove);
        return nodeToRemove.data;
    }

    @Override
    public void clear() { //очистка списка
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        Node<E> currentNode;
        if (index < size / 2) {
            currentNode = head;
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.next;
            }
        } else {
            currentNode = tail;
            for (int i = size - 1; i > index; i--) {
                currentNode = currentNode.prev;
            }
        }
        return currentNode.data;
    }

    @Override
    public int indexOf(Object o) {
        Node<E> currentNode = head;
        int index = 0;
        while (currentNode != null) {
            if (o.equals(currentNode.data)) {
                return index;
            }
            currentNode = currentNode.next;
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        Node<E> currentNode = tail;
        int index = size - 1;
        while (currentNode != null) {
            if (o.equals(currentNode.data)) {
                return index;
            }
            currentNode = currentNode.prev;
            index--;
        }
        return -1;
    }

    @Override
    public void sort() {
        // Реализуйте сортировку
    }

    @Override
    public void addAll(List<E> additionalNumbers) {

    }

    private Node<E> getNode(int index) {//возврат по индексу
        if (index < size / 2) {
            Node<E> currentNode = head;
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.next;
            }
            return currentNode;
        } else {
            Node<E> currentNode = tail;
            for (int i = size - 1; i > index; i--) {
                currentNode = currentNode.prev;
            }
            return currentNode;
        }
    }

    private void removeNode(Node<E> nodeToRemove) {//удаление по индексу
        Node<E> prevNode = nodeToRemove.prev;
        Node<E> nextNode = nodeToRemove.next;

        if (prevNode != null) {
            prevNode.next = nextNode;
        } else {
            head = nextNode;
        }

        if (nextNode != null) {
            nextNode.prev = prevNode;
        } else {
            tail = prevNode;
        }

        size--;
    }
}

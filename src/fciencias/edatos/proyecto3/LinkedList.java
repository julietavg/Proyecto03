/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p3;

/**
 * Clase que implementa una lista simplemente ligada.
 * 
 * @author Reyes Ramos Luz María 318211073
 * @author Vargas Gutiérrez Julieta 318341945
 * @version 2.0 Diciembre 11, 2021
 * @since EDD-2022-1
 */
public class LinkedList<E> {

    /** Cabeza de la lista */
    private Node<E> head;

    /** Guarda la longitud de la lista */
    private int size;

    private class Node<T> {

        /** Contiene el elemento de la lista */
        private T element;

        /** Referencia al nodo siguiente */
        private Node<T> next;

        /**
         * Constructor de un nodo
         * 
         * @param element El elemento a almacenar
         */
        public Node(T element) {
            this.element = element;
        }

        /**
         * Obtiene el elemento almacenado en el nodo.
         * 
         * @return Elemento almacenado.
         */
        public T getElement() {
            return element;
        }

        /**
         * Obtiene la referencia siguiente a un nodo.
         * 
         * @return el nodo siguiente.
         */
        public Node<T> getNext() {
            return next;
        }

        /**
         * Modifica el elemento que contiene un nodo.
         * 
         * @param element Nuevo elemento.
         */
        public void setElement(T element) {
            this.element = element;
        }

        /**
         * Modifica la referencia siguiente de un nodo.
         * 
         * @param next Nodo siguiente
         */
        public void setNext(Node<T> next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return element.toString();

        }
    }

    /**
     * Añade un elemento a la lista.
     * 
     * @param element El elemento a agregar.
     */
    public void add(E element) {
        Node<E> newElement = new Node<E>(element);
        newElement.setNext(head);
        head = newElement;
        size++;
    }

    @Override
    public String toString() {
        Node<E> iterator = head;
        String list = "";
        while (iterator != null) {

            list += iterator.getElement() + "\t";
            iterator = iterator.getNext();
        }
        return list;
    }

    /**
     * Determina si la lista está vacía.
     * 
     * @return true si la lista es vacía , false en otro caso.
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Obtiene el elemento de una lista dado un indíce.
     * 
     * @param i indice del elemento a buscar.
     * @return El elemento almacenado en el índice dado.
     */
    public E get(int i) {
        // Si la lista es vacia
        try {
            if (isEmpty()) {
                System.out.println("\n\tLa lista está vacía");
                return null;
            }
            Node<E> iterador = head;

            for (int n = 0; n < size; n++) {
                if (n == i) {
                    return iterador.getElement();
                }
                iterador = iterador.getNext();
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    /**
     * Obtiene la longitud de la lista.
     * 
     * @return longitud de la lista.
     */
    public int size() {
        return size;
    }

    /**
     * Vacía la lista.
     */
    public void clean() {
        head = null;
        size = 0;
    }

    /**
     * Determina si un elemento está contenido en la lista.
     * 
     * @param element El elemento a buscar.
     * @return true si esta en la lista, false en otro caso.
     */
    public boolean contains(E element) {
        if (isEmpty()) {
            return false;
        }
        Node<E> iterator = head;
        for (int n = 0; n < size(); n++) {
            if (element.equals(iterator.getElement()))
                return true;
            iterator = iterator.getNext();
        }
        return false;
    }

    public int getIndex(E e) {
        if (isEmpty()) {
            return -1;
        }
        Node<E> iterator = head;
        for (int n = 0; n < size(); n++) {
            if (e.equals(iterator.getElement())) {
                return n;
            }
            iterator = iterator.getNext();
        }
        return -1;

    }

    public E remove(int i) {
        Node<E> eliminado = null;
        try {
            // List vacia
            if (isEmpty()) {
                System.out.println("\n\tLa lista está vacía");
                return null;
            }
            // Si i es 0
            if (i == 0) {
                eliminado = head;
                head = head.getNext();
                size--;
                return eliminado.getElement();
            }
            // Algun otro indice
            Node<E> iterador = head;
            for (int n = 0; n < size(); n++) {
                if (n == i) {
                    eliminado = iterador;
                    iterador.setNext(iterador.getNext().getNext());
                    size--;
                    return eliminado.getElement();
                }
                iterador = iterador.getNext();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return eliminado.getElement();
    }

    public void add(int i, E e) {
        try {
            Node<E> newNode = new Node<>(e);
            // si es vacia
            if (isEmpty()) {
                newNode.setNext(head);
                head = newNode;
                size++;
                return;
            }

            // Cualquier otro indice
            Node<E> iterator = head;
            for (int n = 0; n < size(); n++) {
                if (n == i - 1) {
                    newNode.setNext(iterator.getNext());
                    iterator.setNext(newNode);
                    size++;
                    return;
                }
                iterator = iterator.getNext();
            }
        } catch (Exception ex) {
            // TODO: handle exception
        }
    }
}


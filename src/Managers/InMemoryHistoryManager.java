package Managers;

import Tasks.Task;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    private final LinkedList<Task> history = new LinkedList<>();

    private  Node head;

    @Override
    public void addTask(Task task) {
        if (history.size() <= 10) {
            history.add(task);
        } else {
            history.remove(0);
            history.add(task);
        }
    }

    @Override
    public void remove(int id) {
        //todo написать метод

    }

    @Override
    public List<Task> getHistory() {
        int historyListSize = history.size();
        System.out.println("история просмотров:");  //убрать после проверки метод не должен выводит на экран
        for (int i = 0; i < historyListSize; i++) {
            System.out.println(history.get(i).getId());
        }
        return history;
    }

    class CustomLinkedList {


        public void linkLast() {

        }

        public void getTask() {

        }
    }

    static class Node {

        private Task task;
        private Node prev;
        private Node next;

        public Node(Node prev, Task task, Node next) {
            this.task = task;
            this.prev = prev;
            this.next = next;
        }

        public Task getTask() {
            return task;
        }

        public void setTask(Task task) {
            this.task = task;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "task=" + task +
                    ", prev=" + prev +
                    ", next=" + next +
                    '}';
        }
    }
}

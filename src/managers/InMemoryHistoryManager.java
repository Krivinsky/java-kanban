package managers;
import tasks.Task;
import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {

    private Node head; //first
    private Node tail;  //last

    private final Map<Integer, Node> nodeMap =  new HashMap<>();

    @Override
    public List<Task> getHistory() {
        return getTaskHistory();
    }

    @Override
    public void addTask(Task task) {
       if (task == null) {
           return;
       }
       final int id = task.getId();
       linkLast(task);
       nodeMap.put(id, tail);
       remove(id);
    }

    private void linkLast(Task task) {
        final  Node node = new Node(tail, task, null);
        if (head == null) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
    }

    @Override
    public void remove(int id) {
        Node node = nodeMap.get(id);
        removeNode(node);
        nodeMap.remove(node.getTask().getId());  //добавил
    }

    private void removeNode(Node node) {
        if (node == null) {
            return;
        }
        if (node.next != null && node.prev != null) {
            //середина
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        if (node.next == null && node.prev != null) {
            //хвост
            tail = node.prev;
            node.prev.next = null;  // proverka
           // nodeMap.remove(node.getTask().getId());

        }
        if (node.prev == null && node.next != null) {
            // голова
            head = node.next;
            node.next.prev = null;   // proverka
           // nodeMap.remove(node.getTask().getId());
        }
        if (node == tail && node == head) {
          //  nodeMap.remove(node.getTask().getId());
            node = null;  // proverka
        }
    }

    private List<Task> getTaskHistory() {
        List<Task> result = new ArrayList<>();
        Node node = head;
        while (node != null){
            result.add(node.getTask());
            node = node.next;
        }
        return result;
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

//        @Override
//        public String toString() {
//            return "Node{" +
//                    "task=" + task +
//                    ", prev=" + prev +
//                    ", next=" + next +
//                    '}';
//        }
    }
}

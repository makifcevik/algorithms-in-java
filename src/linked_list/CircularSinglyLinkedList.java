package linked_list;

public class CircularSinglyLinkedList<T>
{
    private Node head, tail;
    private int N;
    private class Node
    {
        T value;
        Node next;

        public Node() {}
        public Node(T value)
        {
            this.value = value;
        }
    }

    public CircularSinglyLinkedList(T value)
    {
        head = tail = new Node(value);
        tail.next = head;
        N = 1;
    }

    public boolean isEmpty() {return N == 0;}

    public int size() {return N;}

    public T get(int location)
    {
        if(location < 0 || location >= N)
            throw new IndexOutOfBoundsException(String.format("Invalid position at %s", location));

        if(location == 0)
        {
            return head.value;
        }

        Node traverse = head;
        for(int i = 0; i < location; i++)
        {
            traverse = traverse.next;
        }
        return traverse.value;
    }

    public int indexOf(T value)
    {
        Node traverse = head;
        int loc = 0;
        while(traverse != tail)
        {
            if(traverse.value.equals(value))
            {
                return loc;
            }
            traverse = traverse.next;
            loc++;
        }
        return -1;
    }

    public void insert(int location, T value)
    {
        Node node = new Node(value);
        if(head == null)
        {
            head = tail = node;
            tail.next = head;
        }
        else if(location <= 0)
        {
            node.next = head;
            head = node;
            tail.next = head;
        }
        else if(location >= N)
        {
            tail.next = node;
            tail = node;
            tail.next = head;
        }
        else
        {
            Node traverse = head;
            for(int i = 0; i < location - 1; i++)
            {
                traverse = traverse.next;
            }
            node.next = traverse.next;
            traverse.next = node;
        }
        N++;
    }

    public void delete(int location)
    {
        if(head == null) return;
        else if(N == 1)
        {
            head = tail = null;
        }
        else if(location <= 0)
        {
            head = head.next;
            tail = head;
        }
        else if(location >= N)
        {
            Node traverse = head;
            while(traverse.next != tail)
            {
                traverse = traverse.next;
            }
            //for the case where there are only 2 elements
            if(traverse == head)
            {
                head.next = null;
                head = tail = null;
                return;
            }
            traverse.next = head;
            tail = traverse;
        }
        else
        {
            Node traverse = head;
            for(int i = 0; i < location - 1; i++)
            {
                traverse = traverse.next;
            }
            traverse.next = traverse.next.next;
        }
        N--;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        Node traverse = head;
        for(int i = 0; i < N; i++)
        {
            assert traverse != null;
            builder.append(traverse.value);
            if(traverse.next != null)
                builder.append(" -> ");
            traverse = traverse.next;
        }
        return builder.toString();
    }
}

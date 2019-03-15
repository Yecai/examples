package _3_collectionStackAndQueue._99_exercise;

import java.util.Collection;
import java.util.Iterator;

/**
 * 通过只调整链（而不是数据）来交换两个相邻的元素，使用
 * a、单链表
 * b、双链表
 */
public class _3_2 {
	public static void main(String[] args) {
		SinglyLinkedList list = new SinglyLinkedList();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		
		list.print();
		System.out.println("exchangeHead()");
		list.exchangeHead();
		list.print();
		System.out.println("exchangeMiddle()");
		list.exchangeMiddle();
		list.print();
		
	}
}

class SinglyLinkedList {
	private Node head;

	public boolean add(Object e) {
		if (head == null) {
			head = new Node(e);
		} else {
			Node newNode = new Node(e);
			last().setNext(newNode);
		}
		return true;
	}
	
	public Node last() {
		Node last = head.getNext();
		if (last == null) {
			return head;
		}
		
		while (last.getNext() != null) {
			last = last.getNext();
		}
		return last;
	}
	
	public void exchangeHead() {
		Node next = head.getNext();
		head.setNext(next.getNext());
		next.setNext(head);
		head = next;
	}
	
	public void exchangeMiddle() {
		System.out.println("交换e1、e2 ： ...-> pre -> e1 -> e2 -> ...");
		Node n1 = head.getNext();
		Node n2 = n1.getNext();
		Node pre = head;
		
		n1.setNext(n2.getNext());
		n2.setNext(pre.getNext());
		pre.setNext(n1.getNext());
	}
	
	public void print() {
		System.out.println();
		System.out.print("singly linked list:");
		if (head != null) {
			System.out.print(head.getData());
		}
		Node next = head.getNext();
		while (next != null) {
			System.out.print(next.getData());
			next = next.getNext();
		}
		System.out.println();
	}
	
}

class Node {
	private Object data;
	private Node next;
	public Node(Object o) {
		this(o, null);
	}
	public Node(Object data, Node next) {
		this.data = data;
		this.next = next;
	}
	public Node getNext() {
		return this.next;
	}
	public void setNext(Node next) {
	    this.next = next;
	}
	public Object getData() {
		return this.data;
	}
}

package a1;

public class SListNode {
	byte information;
	private SListNode next;
	private SListNode prev;
	
	public SListNode(byte information) {
		this.information = information;
		this.next = null;
		this.prev = null;

	}
	
	public void setNext(SListNode next) {
		this.next = next;
	}

	public void setPrev(SListNode prev) {
		this.prev = prev;
	}
	public SListNode getPrev() {
		return this.prev;
	}
	
	public SListNode getNext() {
		return this.next;
	}
	public byte getInformation() {
		return this.information;
	}



}
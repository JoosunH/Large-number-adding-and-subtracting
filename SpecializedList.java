package a1;

public class SpecializedList implements SpecializedListInterface {

	private SListNode listFirst;
	private SListNode listLast;
	private SListNode forward;
	private SListNode backward;
	private int size = 0;

	public SpecializedList() {
		this.listFirst = null;
		this.listLast = null;
		this.forward = null;
		this.backward = null;
	}

	@Override
	public void resetForward() {

		forward = listFirst;

	}

	@Override
	public byte getNextItem() {
		if (forward == null) {
			resetForward();
		}
		byte result = forward.getInformation();
		if (forward == listLast) {
			forward = listFirst;
		} else
			forward = forward.getNext();

		return result;
	}

	@Override
	public void resetBackward() {

		backward = listLast;

	}

	@Override
	public byte getPriorItem() {
		if (backward == null) {
			resetBackward();
		}
		byte result = backward.getInformation();
		if (backward == listFirst) {
			backward = listLast;
		} else
			backward = backward.getPrev();

		return result;
	}

	@Override
	public int lengthIs() {

		return size;
	}

	@Override
	public void insertFront(byte item) {

		SListNode node = new SListNode(item);
		if (listFirst == null) {

			listFirst = node;
			listLast = node;

		} else
			node.setNext(listFirst);
		node.setPrev(null);
		listFirst = node;
		size++;
	}

	@Override
	public void insertEnd(byte item) {

		SListNode node = new SListNode(item);
		if (listLast == null) {

			listFirst = node;
			listLast = node;

		} else
			listLast.setNext(node);
		node.setPrev(listLast);
		listLast = node;
		size++;
	}

	public String toString() {
		String out = "[";
		SListNode temp = listFirst;
		while (temp != null) {
			out += temp.getInformation() + " ";
			temp = temp.getNext();
		}
		out += "]\n";
		return out;
	}

}

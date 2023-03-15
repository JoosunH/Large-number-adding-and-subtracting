package a1;

public class LargeInt implements LargeIntInterface {

	private boolean isNegative;
	private SpecializedList node;

	public LargeInt() {
		this.node = new SpecializedList();
	}

	public LargeInt(String input) {
		isNegative = false;

		if (input.charAt(0) == '-') {
			isNegative = true;
			input = input.substring(1);
		}
		node = new SpecializedList();

		for (int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i);
			node.insertEnd((byte) (ch - '0'));

		}
	}

	public SpecializedList getNumber() {
		return node;
	}

	@Override
	public LargeInt add(LargeInt input) {
		LargeInt sum = new LargeInt();
		LargeInt answer = null;
		SpecializedList num1 = this.node;
		SpecializedList num2 = input.getNumber();
		int comparing = compare(input);

		if (comparing == 1) {
			int length = Math.min(num1.lengthIs(), num2.lengthIs());

			num1.resetBackward();
			num2.resetBackward();

			int carry = 0;

			if (this.isNegative == false && input.isNegative == true) {
				LargeInt result = this.subtractDifferent(input);
				result.isNegative = false;
				return result;
			}

			if (this.isNegative == true && input.isNegative == false) {
				LargeInt result = this.subtractDifferent(input);
				result.isNegative = true;
				return result;
			}

			if (this.isNegative == true && input.isNegative == true) {
				sum.isNegative = true;
			}

			if (this.isNegative == false && input.isNegative == false) {
				sum.isNegative = false;
			}

			for (int i = 0; i < length; i++) {
				int n1 = num1.getPriorItem();
				int n2 = num2.getPriorItem();
				int digit = (n1 + n2 + carry) % 10;
				carry = (n1 + n2 + carry) / 10;
				sum.getNumber().insertFront((byte) digit);
			}

			SpecializedList remaining = num1.lengthIs() > num2.lengthIs() ? num1 : num2;

			for (int i = 0; i < remaining.lengthIs() - length; i++) {
				int rem = remaining.getPriorItem();
				int digit = (rem + carry) % 10;
				carry = (rem + carry) / 10;
				sum.getNumber().insertFront((byte) digit);
			}
			if (carry > 0) {
				sum.getNumber().insertFront((byte) carry);
			}

			answer = sum.removeZero(sum.toString());
		} else if (comparing == -1) {
			int length = Math.min(num1.lengthIs(), num2.lengthIs());

			num1.resetBackward();
			num2.resetBackward();

			int carry = 0;

			if (this.isNegative == false && input.isNegative == true) {

				LargeInt result = this.subtractDifferent(input);
				result.isNegative = true;
				return result;
			}

			if (this.isNegative == true && input.isNegative == false) {

				LargeInt result = this.subtractDifferent(input);
				result.isNegative = false;
				return result;
			}

			if (this.isNegative == true && input.isNegative == true) {
				sum.isNegative = true;
			}

			if (this.isNegative == false && input.isNegative == false) {
				sum.isNegative = false;
			}

			for (int i = 0; i < length; i++) {
				int n1 = num1.getPriorItem();
				int n2 = num2.getPriorItem();
				int digit = (n1 + n2 + carry) % 10;
				carry = (n1 + n2 + carry) / 10;
				sum.getNumber().insertFront((byte) digit);
			}

			SpecializedList remaining = num1.lengthIs() > num2.lengthIs() ? num1 : num2;

			for (int i = 0; i < remaining.lengthIs() - length; i++) {
				int rem = remaining.getPriorItem();
				int digit = (rem + carry) % 10;
				carry = (rem + carry) / 10;
				sum.getNumber().insertFront((byte) digit);
			}
			if (carry > 0) {
				sum.getNumber().insertFront((byte) carry);
			}

			answer = sum.removeZero(sum.toString());
		}

		else if (num1.lengthIs() == num2.lengthIs()) {

			num1.resetBackward();
			num2.resetBackward();

			int carry = 0;

			for (int i = 0; i < num1.lengthIs(); i++) {
				int n1 = num1.getPriorItem();
				int n2 = num2.getPriorItem();
				int digit = (n1 + n2 + carry) % 10;
				carry = (n1 + n2 + carry) / 10;
				sum.getNumber().insertFront((byte) digit);
			}

			if (carry > 0) {
				sum.getNumber().insertFront((byte) carry);
			}

			answer = sum.removeZero(sum.toString());

		}

		return answer;
	}

	@Override
	public LargeInt subtract(LargeInt input) {
		
		int comparing = compare(input);
		LargeInt difference = new LargeInt();
		LargeInt answer = null;
		SpecializedList num1 = this.node;
		SpecializedList num2 = input.getNumber();

		if (comparing == 1) {

			num1.resetBackward();
			num2.resetBackward();

			int carry = 0;

			if (this.isNegative == true && input.isNegative == true) {

				difference.isNegative = true;

			}
			if (this.isNegative == false && input.isNegative == true) {
				LargeInt result = this.addDifferent(input);
				result.isNegative = false;
				return result;

			}
			if (this.isNegative == true && input.isNegative == false) {

				LargeInt result = this.addDifferent(input);
				result.isNegative = true;
				return result;
			}
			if (this.isNegative == false && input.isNegative == false) {
				difference.isNegative = false;
			}

			int length = Math.min(num1.lengthIs(), num2.lengthIs());

			for (int i = 0; i < length; i++) {
				int n1 = num1.getPriorItem();
				int n2 = num2.getPriorItem();
				int digit = (n1 - carry - n2 + 10) % 10;
				carry = (n1 - carry - n2 < 0) ? 1 : 0;
				if (carry == 1) {
					digit = (digit + 10) % 10;
					carry = (digit == 0) ? 10 : carry;
				}
				difference.getNumber().insertFront((byte) digit);
			}

			SpecializedList remaining = num1.lengthIs() > num2.lengthIs() ? num1 : num2;
			for (int i = 0; i < remaining.lengthIs() - length; i++) {
				int rem = remaining.getPriorItem();
				int digit = (rem - carry) % 10;
				carry = (rem - carry < 0) ? 1 : 0;
				if (carry == 1) {
					digit = (digit + 10) % 10;
					carry = (digit == 0) ? 10 : carry;
				}
				difference.getNumber().insertFront((byte) digit);
			}
			answer = difference.removeZero(difference.toString());
		} else if (comparing == -1) {
			SpecializedList temp = num1;
			difference.isNegative = true;
			num2 = input.getNumber();
			num1 = num2;
			num2 = temp;
			if (this.isNegative == true && input.isNegative == true) {

				difference.isNegative = false;

			}
			if (this.isNegative == false && input.isNegative == false) {

				difference.isNegative = true;

			}
			if (this.isNegative == true && input.isNegative == false) {

				LargeInt result = this.addDifferent(input);
				result.isNegative = true;
				return result;

			}
			if (this.isNegative == false && input.isNegative == true) {

				LargeInt result = this.addDifferent(input);
				result.isNegative = false;
				return result;

			}

			num1.resetBackward();
			num2.resetBackward();

			int carry = 0;
			int length = Math.min(num1.lengthIs(), num2.lengthIs());

			for (int i = 0; i < length; i++) {
				int n1 = num1.getPriorItem();
				int n2 = num2.getPriorItem();
				int digit = (n1 - carry - n2 + 10) % 10;
				carry = (n1 - carry - n2 < 0) ? 1 : 0;
				if (carry == 1) {
					digit = (digit + 10) % 10;
					carry = (digit == 0) ? 10 : carry;
				}
				difference.getNumber().insertFront((byte) digit);
			}

			SpecializedList remaining = num1.lengthIs() > num2.lengthIs() ? num1 : num2;
			for (int i = 0; i < remaining.lengthIs() - length; i++) {
				int rem = remaining.getPriorItem();
				int digit = (rem - carry) % 10;
				carry = (rem - carry < 0) ? 1 : 0;
				if (carry == 1) {
					digit = (digit + 10) % 10;
					carry = (digit == 0) ? 10 : carry;
				}
				difference.getNumber().insertFront((byte) digit);
			}
			answer = difference.removeZero(difference.toString());

		}
		else if (num1.lengthIs() == num2.lengthIs()) {
			num1.resetBackward();
			num2.resetBackward();

			if(this.isNegative == input.isNegative) {
				difference.getNumber().insertFront((byte) 0);
				answer = difference.removeZero(difference.toString());
				return answer;
			}
			
			if(this.isNegative == false && input.isNegative == true) {
				LargeInt result = this.addDifferent(input);
				result.isNegative = false;
				return result;

			}
			if(this.isNegative == true && input.isNegative == false) {
				LargeInt result = this.addDifferent(input);
				result.isNegative = true;
				return result;
			}
		
		}
		
		

		

		return answer;
}

	@Override
	public void setNegative() {
		this.isNegative = !this.isNegative;
	}

	public String toString() {
		String output = "";
		if (isNegative == true) {
			output += "-";
		}
		node.resetForward();
		for (int i = 0; i < node.lengthIs(); i++) {

			output += node.getNextItem();
		}
		return output;
	}

	private LargeInt removeZero(String input) {
		int counter = 0;
		String noZero = "";

		if (input.charAt(0) == '-') {
			isNegative = true;
			input = input.substring(1);
		}

		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == '0') {
				counter++;

			} else
				break;
		}

		if (counter == input.length()) {
			return new LargeInt("0");
		}
		if (isNegative == true || this.isNegative == true) {
			noZero = "-" + input.substring(counter);
		} else {
			noZero = input.substring(counter);
		}
		return new LargeInt(noZero);

	}

	public int compare(LargeInt input) {
		SpecializedList num1 = this.node;
		SpecializedList num2 = input.getNumber();
		int length1 = num1.lengthIs();
		int length2 = num2.lengthIs();

		if (length1 > length2) {
			return 1;
		} else if (length2 > length1) {
			return -1;
		} else
			num1.resetForward();
		num2.resetForward();
		for (int i = 0; i < num1.lengthIs(); i++) {
			int n1 = num1.getNextItem();
			int n2 = num2.getNextItem();
			if (n1 > n2) {
				return 1;
			} else if (n2 > n1) {
				return -1;
			}
		}
		return 0;
	}

	public LargeInt subtractDifferent(LargeInt input) {
		LargeInt answer;
		int comparing = compare(input);
		LargeInt difference = new LargeInt();
		SpecializedList num1 = this.node;
		SpecializedList num2 = input.getNumber();
		if (comparing == 1) {

			num1.resetBackward();
			num2.resetBackward();

			int carry = 0;

			int length = Math.min(num1.lengthIs(), num2.lengthIs());

			for (int i = 0; i < length; i++) {
				int n1 = num1.getPriorItem();
				int n2 = num2.getPriorItem();
				int digit = (n1 - carry - n2 + 10) % 10;
				carry = (n1 - carry - n2 < 0) ? 1 : 0;
				if (carry == 1) {
					digit = (digit + 10) % 10;
					carry = (digit == 0) ? 10 : carry;
				}
				difference.getNumber().insertFront((byte) digit);
			}

			SpecializedList remaining = num1.lengthIs() > num2.lengthIs() ? num1 : num2;
			for (int i = 0; i < remaining.lengthIs() - length; i++) {
				int rem = remaining.getPriorItem();
				int digit = (rem - carry) % 10;
				carry = (rem - carry < 0) ? 1 : 0;
				if (carry == 1) {
					digit = (digit + 10) % 10;
					carry = (digit == 0) ? 10 : carry;
				}
				difference.getNumber().insertFront((byte) digit);
			}
			answer = difference.removeZero(difference.toString());
		} else if (comparing == -1) {
			SpecializedList temp = num1;
			difference.isNegative = true;
			num2 = input.getNumber();
			num1 = num2;
			num2 = temp;

			num1.resetBackward();
			num2.resetBackward();

			int carry = 0;
			int length = Math.min(num1.lengthIs(), num2.lengthIs());

			for (int i = 0; i < length; i++) {
				int n1 = num1.getPriorItem();
				int n2 = num2.getPriorItem();
				int digit = (n1 - carry - n2 + 10) % 10;
				carry = (n1 - carry - n2 < 0) ? 1 : 0;
				if (carry == 1) {
					digit = (digit + 10) % 10;
					carry = (digit == 0) ? 10 : carry;
				}
				difference.getNumber().insertFront((byte) digit);
			}

			SpecializedList remaining = num1.lengthIs() > num2.lengthIs() ? num1 : num2;
			for (int i = 0; i < remaining.lengthIs() - length; i++) {
				int rem = remaining.getPriorItem();
				int digit = (rem - carry) % 10;
				carry = (rem - carry < 0) ? 1 : 0;
				if (carry == 1) {
					digit = (digit + 10) % 10;
					carry = (digit == 0) ? 10 : carry;
				}
				difference.getNumber().insertFront((byte) digit);
			}
			answer = difference.removeZero(difference.toString());

		} else {

			difference.getNumber().insertFront((byte) 0);
			answer = difference.removeZero(difference.toString());

		}

		return answer;

	}

	public LargeInt addDifferent(LargeInt input) {
		LargeInt sum = new LargeInt();
		LargeInt answer = null;
		SpecializedList num1 = this.node;
		SpecializedList num2 = input.getNumber();
		


			int length = Math.min(num1.lengthIs(), num2.lengthIs());

			num1.resetBackward();
			num2.resetBackward();

			int carry = 0;

			for (int i = 0; i < length; i++) {
				int n1 = num1.getPriorItem();
				int n2 = num2.getPriorItem();
				int digit = (n1 + n2 + carry) % 10;
				carry = (n1 + n2 + carry) / 10;
				sum.getNumber().insertFront((byte) digit);
			}

			SpecializedList remaining = num1.lengthIs() > num2.lengthIs() ? num1 : num2;

			for (int i = 0; i < remaining.lengthIs() - length; i++) {
				int rem = remaining.getPriorItem();
				int digit = (rem + carry) % 10;
				carry = (rem + carry) / 10;
				sum.getNumber().insertFront((byte) digit);
			}
			if (carry > 0) {
				sum.getNumber().insertFront((byte) carry);
			}

			answer = sum.removeZero(sum.toString());

		return answer;
	}

}

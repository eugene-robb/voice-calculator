package calculator;

import java.util.*;

public class Calculate {

	private String number;
	private String operator;
	public LinkedList<String> linkedList;
	private boolean isDecimalPoint;
	private String decimalValue;

	public Calculate() {
		number = "";
		operator = "";
		linkedList = new LinkedList<String>();
		isDecimalPoint = false;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public boolean isDecimalPointPressed() {
		return isDecimalPoint;
	}

	public void setDecimalPointPressed(boolean isDecimalPoint) {
		this.isDecimalPoint = isDecimalPoint;
	}

	public String getDecimalValue() {
		return decimalValue;
	}

	public void clearAll() {
		this.operator = "";
		this.number = "";
		linkedList = new LinkedList<String>();
		this.isDecimalPoint = false;
	}

	public String buildNumber(String val) {
		number += val;
		return this.number;
	}

	public void buildExpression(String val) {
		this.operator = val;
		linkedList.add(number);
		linkedList.add(operator);
		this.number = "";
		this.isDecimalPoint = false;
	}

	public double performCalculation(){
		linkedList.add(number);

		//calculation will end if counter variable reaches linkedList.size()-1/2
		int counter = linkedList.size() / 2;
		while (counter > 0) {
			// multiplication
			for (int i = 1; i < linkedList.size() - 1; i += 2) {
				if (linkedList.get(i).equals("x")) {
					//convert to numbers
					double firstVal = Double.parseDouble(linkedList.get(i - 1));
					double secondVal = Double.parseDouble(linkedList.get(i + 1));
					//perform calculation 
					double result = firstVal * secondVal;

					//remove number and operator and replace with i+1
					linkedList.set(i + 1, Double.toString(result));
					linkedList.remove(i);
					linkedList.remove(i - 1);
					//decrement counter and set i to new location because size of list has changed
					counter--;
					i -= 2;
				}
			}

			// division
			for (int i = 1; i < linkedList.size() - 1; i += 2) {
				if (linkedList.get(i).equals("÷")) {
					double firstVal = Double.parseDouble(linkedList.get(i - 1));
					double secondVal = Double.parseDouble(linkedList.get(i + 1));
					double result = firstVal / secondVal;

					linkedList.set(i + 1, Double.toString(result));
					linkedList.remove(i);
					linkedList.remove(i - 1);
					counter--;
					i -= 2;
				}
			} 

			// subtraction
			for (int i = 1; i < linkedList.size() - 1; i += 2) {
				if (linkedList.get(i).equals("-")) {
					double firstVal = Double.parseDouble(linkedList.get(i - 1));
					double secondVal = Double.parseDouble(linkedList.get(i + 1));
					double result = firstVal - secondVal;

					linkedList.set(i + 1, Double.toString(result));
					linkedList.remove(i);
					linkedList.remove(i - 1);
					counter--;
					i -= 2;
				} 

			}

			// for addition
			for (int i = 1; i < linkedList.size() - 1; i += 2) {
				if (linkedList.get(i).equals("+")) {
					double firstVal = Double.parseDouble(linkedList.get(i - 1));
					double secondVal = Double.parseDouble(linkedList.get(i + 1));
					double result = firstVal + secondVal;

					linkedList.set(i + 1, Double.toString(result));
					linkedList.remove(i);
					linkedList.remove(i - 1);
					counter--;
					i -= 2;
				} 

			}
		}

		Double answer = Double.parseDouble(linkedList.get(0));
		decimalValue = Double.toString(answer);

		// clear variables 
		this.number = "";
		this.operator = "";
		linkedList = new LinkedList<String>();

		return answer;
	}

	@Override
	public String toString() {
		return "Calculate [number=" + number + ", operator=" + operator + ", linkedList=" + linkedList
				+ ", isDecimalPoint=" + isDecimalPoint + ", decimalValue=" + decimalValue + "]";
	}

}

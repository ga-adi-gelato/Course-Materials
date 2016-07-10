---
title: Whiteboard Practice
type: Morning exercise
duration: "1:00"
creator:
    name: Charlie Drews
    city: NYC
---

# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Whiteboard Practice

## Exercise

Team up in groups of 2 or 3 and take turns working on the following problems. Pretend you are in an interview setting:
- Keep talking!
- Ask questions (to the instructors, or to your teammates)
- Consider starting with an example, showing the expected outputs for sample inputs
- Consider writing pseudocode before Java to make your steps clear to both you and the interviewer

#### Requirements

Take turns working through these problems on the whiteboard. It's OK if you don't finish them all; make as much progress as you can.

1. `centeredAverage`: Return the "centered" average of an array of ints, which we'll say is the mean average of the values, except ignoring the largest and smallest values in the array. If there are multiple copies of the smallest value, ignore just one copy, and likewise for the largest value. Use int division to produce the final average. You may assume that the array is length 3 or more.

	One possible solution:

	```java
	public int centeredAverage(int[] nums) {
		int smallest = Integer.MAX_VALUE;
		int largest = Integer.MIN_VALUE;
		int sum = 0;

		for (int num : nums) {
			if (num < smallest) {
				smallest = num;
			}
			if (num > largest) {
				largest = num;
			}
			sum += num;
		}

		sum = sum - largest - smallest;
		return sum / (nums.length - 2);
	}
	```

2. `repeatSeparator`: Given two strings, word and a separator sep, return a big string made of count occurrences of the word, separated by the separator string. For example, `repeatSeparator("Word", "X", 3)` returns "WordXWordXWord".

	One possible solution:

	```java
	public String repeatSeparator(String word, String sep, int count) {
		if (count == 0) {
			return "";
		}
	  
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < count; i++) {
			sb.append(word + sep);
		}
		sb.append(word);

		return sb.toString();
	}
	```

3. `blackjack`: Given 2 int values greater than 0, return whichever value is nearest to 21 without going over. Return 0 if they both go over.

	One possible solution:

	```java
	public int blackjack(int a, int b) {
		if (a <= 21 && b <= 21) {
			return (Math.abs(21 - a) < Math.abs(21 - b)) ? a : b;
		} else if (a <= 21) {
			return a;
		} else if (b <=21) {
			return b;
		} else {
			return 0;
		}
	}
	```

#### Deliverable

No deliverable - just practice working through problems out loud on a whiteboard

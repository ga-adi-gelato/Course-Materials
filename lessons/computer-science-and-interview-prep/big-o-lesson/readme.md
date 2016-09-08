---
title: Big O
duration: "1:30"
creator:
    name: Josh Bartz
    city: Missoula

---

# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Big O

### LEARNING OBJECTIVES
*After this lesson, you will be able to:*
- Explain Big O Notation
- Calculate Big O for code snippets

### STUDENT PRE-WORK
*Before this lesson, you should already be able to:*
- Describe and use basic data structures: arrays and linked lists
- Describe and use basic sorting algorithms: bubble, insertion, and merge sort

### INSTRUCTOR PREP
*Before this lesson, instructors will need to:*
- Read through the lesson and adapt as needed to fit your teaching style and subject matter expertise
- Prepare tables of steps, subproblem size at each step, and work done at each step for common algorithms

---

### LESSON GUIDE

| TIMING  | TYPE  | TOPIC  |
|:-:|---|---|
| 5 min  | [Opening](#opening-5-mins)  | Discuss lesson objectives |
| 15 min  | [Introduction](#introduction-big-o-15-mins)  | Big O |
| 15 min  | [Guided Practice](#guided-practice-15-mins)  | Determing running times |
| 20 min  | [Guided Practice](#guided-practice-logarithmic-and-linearithimic-times-20-mins)  | Logarithmic and linearithimic times |
| 15 min  | [Introduction](#introduction-common-complexity-types-15-mins)  | Common complexity types |
| 15 min  | [Independent Practice](#independent-practice-calculating-algorithm-complexity-15-mins)  | Calculating algorithm complexity |
| 5 min  | [Conclusion](#conclusion-5-mins)  | Review / recap |

<a name="opening"></a>
## Opening (5 mins)

The lessons on sorting algorithms gave cases in which Bubble Sort, Insert Sort, and Merge Sort performed very quickly, since we were using extremely small data sets. But what if we needed to know how they perform at their worst?

> Check: How do you think we can compare searching and sorting algorithms?

Knowing the worst case for algorithms allows us to compare the efficiency of algorithms so that we can decide the appropriate algorithm to use for a given situation. To measure the efficiency of an algorithm, measured by runtime, we use Big O Notation.


<a name="introduction"></a>
## Introduction: Big O (15 mins)

To Express an algorithm in Big O Notation, we need to do 3 things:

1. Know the number of executed operations done in the worst-case scenario
2. Phrase the expression in terms of the amount of input
3. Ignore constant multiples or smaller things added on

In order to determine the number of operations, let's start with some basic rules.

#### Linear time: O(n) operations

Linear time operations increase in time proportionately with the amount of input. A `for` loop is a good example of a linear time operation.

```java
public int getSum(int[] array) {
    int total = 0;
    for(int i = 0; i < array.length; i++) {
        total += array[i];
    }
    return total;
}
```

> Check: With the person next to you, come up with another example of a linear time operation

#### Constant time: O(1) operations

A constant time operation is one that does not depend on the length of the input. Comparisons and mathematical operations can be done in constant time. For example, the following method executes in constant time:

```java
public int myMethod(int a) {
    if(a <= 10) {
        return a;
    } else {
        int b = a/.5 - 1;
        return a * b;
    }
}
```

> Check: With the person next to you, find another example of a O(1) operation. (Bring up finding a value in a linked list vs an array list)

#### Quadratic time: O(n<sup>2</sup>) operations

If a single `for` loop operates in O(n) time, then it makes sense that a nested `for` loop would operate in O(n<sup>2</sup>) time:

```java
for(int i = 0; i < input; i++) {
    for(int j = 0; j < input; j++) {
        System.out.println(i + "," + j);
    }
}
```

> Check: Why is this O(n<sup>2</sup>)? Consider a table of work done per step. How could we get O(n<sup>3</sup>)?

## Guided Practice (15 mins)

With a partner, take 2 minutes to determine the _worst case_ run time of the following code snippet:

```java
public void someFunction(LinkedList<Integer> myList) {
    int myInt = 7;
    int myProduct = 7 * 13;

    for(Integer i : myList) {
        if(i == myProduct) {
            return;
        }
    }
}
```
> Check: Review the answer as a class: O(n), because if `myProduct` never equals an element of `myList`, then it will iterate through the entire list of size _n_.

With a partner, take 2 minutes to determine the _worst case_ run time of the following code snippet:

```java
public static void main(String[] args) {
    firstFunction();
}

public static void firstFunction() {
    int[] someArray = {3, 9, 4, 7, 1, 9};

    for(int i = 0; i < someArray.length; i++) {
        if(secondFunction(someArray, someArray[i])) {
            break;
        }
    }
}

public static boolean secondFunction(int[] integerArray, int originalInt) {
    int subtractor = Math.round(Math.random() * 10);

    for(int i = 0; i < integerArray.length; i++) {
        if((originalInt - subtractor) == integerArray[i]) {
            return true;
        }
    }
    return false;
}
```

> Check: Review the answer as a class: O(n<sup>2</sup>). Basically, the worst possible scenario for completion of the above snippet is that the second function is called for each integer in the array created in firstFunction(). That is why Big O notation is also known as worst-case notation: it computes the worst possible outcome for an algorithm.


<a name="introduction"></a>
## Guided Practice: Logarithmic and Linearithimic times (20 mins)

> Reminder of [how logs work](https://www.mathsisfun.com/algebra/logarithms.html): log<sub>x</sub>(x<sup>y</sup>) = y. For example, log<sub>2</sub>(8) = log<sub>2</sub>(2<sup>3</sup>) = 3.

#### Logarithmic time: O(log(n)) operations

Say we are looking for a person's name in the phone book. You haven't checked any of the listings yet, so you start right in the middle. You don't happen to open right to the page, but you know that because a phone book is sorted, the person's name is farther along in the book. So you split the second half of the book in half again. You repeat this process until you find the person's name. You know this is known as __binary search__, which operates in O(log n) time.

Why _logarithmic_ time, you may ask? Basically, that is because it only takes log<sub>2</sub>(n) times of cutting the list in half before we are down to 1 or fewer choices left. That means that the maximum number of times the list will be cut in half before we find either the item or that the item is not in the list is log<sub>2</sub>(n) (in a list of size n).

> Instructor Note: With the students' help, raw a table of how many steps are required, the subproblem size at each step, and the work done at each step. Demonstrate how this adds up to log<sub>2</sub>(n) total work.


#### Linearithmic time: O(n * log(n)) operations (a.k.a. O(n log n) or O(nlogn))

Most algorithms that utilize a "divide and conquer" strategy are computed in some form of logarithmic time, including __Merge Sort__, that operates at an average and worst time of O(n log n). Consider what would happen if the phone book in the example above had its pages in a random order and now must be put into order.

> Instructor Note: With the students' help, [draw a table](https://www.khanacademy.org/computing/computer-science/algorithms/merge-sort/a/analysis-of-merge-sort) of how many steps are required, the subproblem size at each step, and the work done at each step. Demonstrate how this adds up to n * log<sub>2</sub>(n) total work.

## Introduction: Common Complexity Types (15 mins)

We've already talked about a few of these, but every row listed in this table is more complex (takes more time or space) than the rows above it.  That means, if we decide an algorithm takes polynomial time to do one set of operations and then moves on and needs a linear amount of work to finish up, we can just say it's a polynomial algorithm for the purposes of Big O notation.

| Notation | Name |
| :-----: | :------: |
| O(1) | constant |
| O(log(n)) | logarithmic |
| O(n) | linear |
| O(n * log(n)) | linearithmic |
| O(n<sup>2</sup>) | quadratic |
| O(n<sup>c</sup>), for c > 1 | polynomial |
| O(c<sup>n</sup>), for c > 1 | exponential |
| O(n!) | factorial |

> Instructor Note: Quickly review constant, logarithmic, linear, linearithmic, and quadratic.  Explain and demonstrate examples of polynomial, exponential (e.g. [recursive fibonacci](http://homepage.cs.uiowa.edu/~sriram/80/spring2009/notes/exponentialRunningTime.html)), and factorial (e.g. finding all permutations).

Let's look at a graphical representation of how the number of operations (time) grows with the number of input elements for various orders of complexity:   

![time complexity graph from daveperrett.com](http://www.daveperrett.com/images/articles/2010-12-07-comp-sci-101-big-o-notation/Time_Complexity.png)

> Instructor Note: Explain how the terms "asymptotic notation" and "asymptotic analysis" relate to this graph

<a name="guided-practice"></a>
## Independent Practice: Calculating algorithm complexity (15 mins)

Calculate the complexity of the following algorithms, one function at a time.  Be ready to explain your answer.

> Check: Have a student group share out.

```java
public static void main(String[] args) {
    int[] myNumArray = {3, 9, 12, 1, 7, 17, 5, 4, 12, 2, 13};
    int total = firstFunction(myNumArray);
    int otherTotal = secondFunction(myNumArray);

    //ignore the sort
    Arrays.sort(myNumArray);
    boolean myBool = thirdFunction(myNumArray, 5);
}

private static int firstFunction(int[] numArray) {
	int total = 0;

	for(int i = 0; i < numArray.length; i++) {
		total += numArray[i];
	}

	for(int i = 0; i < numArray.length; i++) {
		if(numArray[i] /2 < i) {
			total -= numArray[i] / 2;
		}
	}
	return total;
}

private static int secondFunction(int[] numArray) {
	int total = 0;

	for(int i = 0; i < numArray.length; i++) {
		total += numArray[i] / (i+1);
	}
	for(int i = 0; i < numArray.length; i++) {
		for(int j = 0; j < i; j++) {
			total += i-j;
		}
	}
	return total;
}

private static boolean thirdFunction(int[] numArray, int key) {
	int start = 0;
    int end = numArray.length - 1;
    while (start <= end) {
        int mid = (start + end) / 2;
        if (key == numArray[mid]) {
            return true;
        }
        if (key < numArray[mid]) {
            end = mid - 1;
        } else {
            start = mid + 1;
        }
    }
    return false;
}
```

> Check: Have a different student group share their answers and how they arrived at their answer.

> Answers:
>
> 1. 2n -> O(n)
> 2. n + n<sup>2</sup> -> O(n<sup>2</sup>)
> 3. log<sub>2</sub>(n) -> O(log(n))


<a name="conclusion"></a>
## Conclusion (5 mins)

Understanding logarithmic complexity is very important to writing efficient code. While we won't always have the luxury of using only the most efficient algorithms, knowing relative performances allows us to make better choices about what algorithms to use in certain situations. In order to determine what algorithms will have best-case or best-average performance, we first need to know the potential worst.

## Additional Resources

- [Introduction to Logarithms](https://www.mathsisfun.com/algebra/logarithms.html)
- [Analysis of Merge Sort](https://www.khanacademy.org/computing/computer-science/algorithms/merge-sort/a/analysis-of-merge-sort)
- [Best, Average, and Worst Cases](http://bigocheatsheet.com/)
- Please google these concepts; there are more great resources out there than we could possibly list here!

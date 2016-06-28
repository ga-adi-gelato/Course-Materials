---
title: Interfaces and Abstract Classes
duration: "1:30"
creator:
    name: Alan Caceres
    city: NYC
---

# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Classes

### LEARNING OBJECTIVES
*After this lesson, you will be able to:*
- Describe the difference between interfaces and abstract classes
- Describe why we would choose an interface over an abstract class and vice versa.
- Declare and extend an abstract class
- Declare an interface and implement it's required methods.

### STUDENT PRE-WORK
*Before this lesson, you should already be able to:*
* Recall basic knowledge of Classes
* Recall basic knowledge of method creation

### INSTRUCTOR PREP
*Before this lesson, instructors will need to:*
- Gather materials needed for class
- Complete Prep work required
- Prepare any specific instructions

---

## Opening: Interfaces and Abstract Classes; what are they? (5 minutes)

When we created our classes in the previous lesson we had to implement everything.
We had to set up the template of member variables, the constructors, and the methods.
What if we wanted to just set up the template and not have to do all the work right then and there?
What if we wanted to set it up in a way that allows us to implement multiple classes that do some
logic, just in a different way for each class?
Interfaces and Abstract Classes allow us to do that?

> Check: Ask them to recall using List<String> aList = new ArrayList<>();

## Introduction: What are Abstract Classes? (20 minutes)

Abstract classes are basically classes that do a bunch of work for us but also asks the developer
to do some work as well.

Let's take a look at an example:

>Instructor Note: As students to create this class

```Java
public class Animal{
  protected String mName = "";
  protected int mNumOfLegs = 4;

  public Animal(String name){
    mName = name;
  }

  public int getNumOfLegs(){
    return mNumOfLegs;
  }

  public void setNumOfLegs(int numOfLegs){
    mNumOfLegs = numOfLegs;
  }

}
```

Let's instantiate this in the main method.

>Instructor note: Ask students to instantiate the animal object and get the number of legs.

```Java
public class Main{

  public static void main(String[] args){

    Animal aCat = new Animal("cat");
    int numOfLegs = aCat.getNumOfLegs();

  }

}
```

What if we wanted to also find out the sound that the animal makes?
We could just add a method `setSound(String sound)`. A better way would be to make
the class an abstract class and allow other classes to handle what sound the animal makes.

```Java
public abstract class Animal{

  protected String mName;
  protected int mNumOfLegs;

  public Animal(String name){
    mName = name;
  }

  public int getNumOfLegs(){
    return mNumOfLegs;
  }

  public void setNumOfLegs(int numOfLegs){
    mNumOfLegs = numOfLegs;
  }

  public abstract void says();

}
```

If we extend this abstract class we will see that we need to implement those abstract methods.
This is the subclassing you learned earlier today, but this makes it slightly more robust.

```Java
public class Dog extends Animal {

    public Dog(){
      super("dog");
    }

    public Dog(String name){
      super(name);
    }

    @Override
    public void says(){
      System.out.println("woof");
    }

}
```

### Independent Practice(15 min)
>Check: Ask students to create a Cat class, Bird class, Mouse, and Cow class that extends the Animal class and implements the abstract method.

>Instructor Note: go over the implementation and instantiation. (5 min)

Let's go back to the main method and instantiate this Cat class.

```Java
public class Main{

  public static void main(String[] args){

    Animal theAnimal = new Cat();
    int numOfLegs = theAnimal.getNumOfLegs();
    theAnimal.says(); //This will print out meow
    theAnimal = new Dog();
    theAnimal.says(); //This can print out woof
  }

}
```

Let's create a few more classes that extend `Animal`.

>Check: Ask students to implement Frog, Elephant, Duck, Fish, Seal, and Fox classes that extend Animal

### What are Interfaces(20 min)

Interfaces are a way to make another programmer do all the work for you. This makes heavy use of a concept called polymorphism.

```Java
public interface Flyable{

 boolean canFly();

}
```

```Java
public interface Swimmable{

  boolean canSwim();

}
```

Polymorphism is a concept in computer programming that allows a program to ignore what class exactly is calling a method. It does not care that it is a Dog class or a Bird class, just that it implements certain methods.

Let's look at another example:

```Java
public class Main{

  public static void main(String[] args){

    List<Animal> animals = new ArrayList<>();

    animals.add(new Dog());
    animals.add(new Cat());
    animals.add(new Bird());
    animals.add(new Mouse());
    animals.add(new Cow());
    animals.add(new Frog());
    animals.add(new Elephant());
    animals.add(new Duck());
    animals.add(new Fish());
    animals.add(new Seal());
    animals.add(new Fox());

    for(int i = 0; i < animals.size(); i++){
      animals.get(i).says();
      animals.get(i).canSwim();
      animals.get(i).canFly();
    }

  }

}
```

>Note: I didn't have to explicitly say what animal it was in the for loop. As long as it
implements the methods for the interface, then I can ask if it can swim or fly.

### Independent practice (15 min)

>Check: Have students create 5 more interfaces to add to the Animal class

>Instructor Note: review the interfaces afterward

### Conclusion (5 min)

We created classes, and learned how to subclass other classes for our own purposes in the previous lesson.
In this lesson we learned how to architect our classes in a way that makes subclassing more robust.
We also learned when and why we would use an abstract class over an interface. These are important concepts for everyone to understand and be able to explain the differences between them. Questions regarding polymorphism are standard practice in most programming interviews.

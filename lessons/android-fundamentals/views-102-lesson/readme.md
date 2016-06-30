---
title: Views 102
type: Lesson
duration: "1:30"
creator:
    name: James Davis
    city: NYC

---

# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Views 102

### Objectives
*After this lesson, students will be able to:*

* Reference views in Java
* Change view properties in Java and XML
* Attach OnClickListeners to views

### Student Pre-work
* Review the Views 101 lesson
* Review the XML lesson

## Introduction (10 minutes)

In the last lesson, you were able to layout views and show them on the screen. Building on top of that, this lesson will show you how to change the visual properties of views. Afterwards, you will be able to make views react to when a user clicks on them.

By the end of this lesson, you will be able to create your first interactive app!

#### Basics - Views vs. Properties vs. Values

Think of a view as a component of the interface: buttons, text boxes, input fields.  All are views!

Every view has a number of properties specific to it. Some examples:

* In **TextView**, you are able to set the view's text, text color, font, etc.

	* In fact there are a number of other views that are "descendants" (or subclasses) of TextView and have the same properties you can set: Button, EditText
	* Notice how views that subclass TextView have all of its properties?

* In **ImageView**, you can set the view's image and how it is scaled within the view.
* In **ProgressBar**, you can set the current progress or if the view is indeterminate (where it spins indefinitely).

*All* views are a *subclass* (or "descendant") of **View.java**. "All views are views." So, how exactly does a Button have all of the properties of a TextView? All views have core properties that are provided by the View "class".  We've covered subclassing; this should be familiar

> Check: Why do all Button and EditText have the same properties that can be manipulated as a TextView?  Why do all views share similar properties that can be manipulated?

The commonly used properties for all view types are:

* width
* height
* padding
* margins
* background
* alpha (opacity)
* id
* visibility
* gravity

There are properties and attributes for each view. The Android website has a detailed reference to all of the popular views, properties, and how to change them. It's found here: [http://developer.android.com/develop/index.html](http://developer.android.com/develop/index.html). From there, you can use the search icon on the top right to search any view.

Also, you can just Google it! Usually Googling "Android *TypeOfView*" would have the #1 link lead directly to the view's reference page. Try it: Google "Android TextView".

Now, that you know about views having attributes/properties, you can probably guess that you can assign values to these properties to affect how they look and behave on the screen.

Think of the following comparison:

You sit down for a meal and the meal has different components: pizza, diet coke, and wings.  Depending on the component, there are particular properties you can ask to change:

- What toppings can you have on your pizza?
- Can or fountain for your diet coke?
- Flavor of the wings?

We'll come back to this example in a moment.


## Demo: Changing View Properties in XML (10 minutes)

You, hopefully, have already played around with some properties in XML, especially when it comes to TextViews, but let's go through the process anyway to solidify the concept.

First, to access all of Android's view properties, the layout must declare a namespace to use, usually the android namespace. This is usually added to the outermost layout automatically, but here's how it looks:

```xml
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
	  android:layout_width="match_parent"
    android:layout_height="match_parent">

    <!-- Your views and layouts go here -->

</LinearLayout>
```

The IDE would not allow you to build the app unless you have access to this namespace!

Now that you have access to the android namespace added, you can access all view properties using the prefix `android:`. For example, when you set the text attribute to a TextView, you would add `android:text="Some Text"`.

Here's how a TextView with large red bold text would look:

```xml
<TextView
	android:layout_width="wrap_content"
	android:layout_height="wrap_content"
	android:text="Hello!"
	android:textSize="40sp"
	android:textColor="#FF0000"
	android:textStyle="bold" />
```
<center>
<img src="screenshots/hello.png" height=300px/></center>



What's the deal with the "40sp" value for text size? This will be explained in detail later. But for now, know that they stand for "scaled pixels," and that **sp**, **dp**, and **px** are units of pixel measurement.

Going back to our meal example:

| Example | Comparison to Android |
| - | - |
| Meal | Activity |
| Pizza | TextView (View)|
| Pizza Topping | textColor (Property/Attribute) |
| Cheese and Chicken | #FF0000 (Value)|

Here's another example of a button with the id "button1", margins, that is the width of it's parent view, and has an opacity of 50%:

```xml
<Button
	android:layout_width="match_parent"
	android:layout_height="wrap_content"
	android:text="I'm a button!"
	android:layout_margin="16dp"
	android:alpha="0.5"/>
```

<center>
<img src="screenshots/button.png" height=300px/>
</center>

## Demo: Changing Views Part 1 (5 minutes)

Watch me as I create a layout and add a few views to it. In XML, the views are changed to look differently.

## Intro: Referencing views in Java (10 minutes)

Before we change view properties in Java, you have to learn how to reference the views you create.

Things to be aware of before you continue:

* Every view and layout has a java class that matches its XML counterpart.
	* ex., <TextView /> is defined by a Java, TextView.java.
* If you give a view or layout an id, it can then be used in Java for reference.
	* ex, **@+id/textView1** can be referenced in java as **R.id.textView1**.
	* **@+id/** is used in XML layout files, and **R.id** is used in Java.


To interact with views in your layouts, you have to get a reference to that view. Activities provide a helpful method, **findViewById()**.

```java
TextView textView = (TextView) findViewById(R.id.textView1);
```

`findViewById(R.id.textView1)`returns a view that has the id textView1. Notice how I said **"returns a view"** and not "returns a text view". TextViews, Buttons, LinearLayouts, etc., are all Views (and subclass View.java). So, findViewById() returns a instance of View.

**Views have to be cast** if you want access to that view's specific functionality.

* ex. TextViews allow you to set a view's text, so you have to cast the result of findViewById(): **(TextView) findViewById(R.id.textView1);**

Now that you have a reference to your specific views, you can change the views to your liking!

> Check: Describe how to reference a view declared in XML in your Activity.

## Demo: Changing view properties in Java (10 minutes)

Remember how you can set attributes to views in XML? Every attribute defined in XML can also be accessed and changed in Java. Usually, the attribute has a **get** and **set** method that matches the XML attribute. Example:

**XML**

```xml
<TextView
		android:id="@+id/textView1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Text"
        android:textColor="#000000" />
```

**Java**

```java
TextView textView = (TextView) findViewById(R.id.textView1);

textView.setText("New Text");
textView.setTextColor(Color.BLACK);
```

## Guided Practice: Changing Views Part 2 (10 minutes)

Let's use the layout created in the first demo, reference the views in Java, and change those view properties according the specifics provided. Finally we'll build the project to see their results.  I want you, though, to guide my progress and instruct me on what I should be doing to make this happen.  We'll code this together.

> Check: Were you able to successfully build the project according to the specifications provided by the instructor.

## Demo: Making views do "things" (20 minutes)

You can make an app that shows a static layout and nothing else. However, that would be boring. Apps do stuff; they show information, and they react to information provided by the user.

Usually, the cool stuff is done in Java.

#### Users Interacting with Views

Every good app has some form of interaction. Usually, that means that the user can click the views on the screen. Examples:

* Tapping the *+* icon in Gmail to compose a new email
* Clicking the *Like* button on a Facebook post
* Clicking someone's profile photo to see more information in LinkedIn

All views are clickable by default. However, clicking a view will not do anything; they don't know what to do. So, in Java, you have to tell them what to do.

To do this, you have to use an Interface called OnClickListener and assign it to a view. It's an interface that listens for when the view is clicked. It has only one method, **onClick()**; this is where you put the code you want to run when the view is clicked. Here's how it looks:

```java

    OnClickListener myOnClickListener = new View.OnClickListener() {
    	@Override
    	public void onClick(View v) {
    		// do stuff here
    	}
    });

    Button button = (Button) findViewById(R.id.button1);

    button.setOnClickListener(myOnClickListener);
```

With the above code, we created a new OnClickListener and set it to a button. Now, whenever that button is clicked, it will call the listener's onClick() method and run the code we write in `// do stuff here`.

--

Note: This is worth repeating. ***All*** views are clickable, not just buttons. You can click a TextView, a ProgressBar, etc. Buttons are special because they are visually clickable; i.e., a button looks pressed when a user touches it.

I bring this up because sometimes you want to click things that are not buttons. For instance, clicking a photo.

> Check: Describe how to make a button "clickable" with logic behind it.

#### Putting it all together

Let's assume we have a layout, *activity_blue.xml*, with two views: a TextView and a Button. They have the ids *textView* and *button*, respectively.

When we click the button, it sets the color of the TextView to blue.

Here's how the Activity would look. All of the code is defined in the activity's **onCreate()** method, which is talked about more in detail at a future lesson. Just know that onCreate is called when the activity starts, and that it loads your created layout with **setContentView()**.

> Instructor Note: Review this slowly, line by line. Elicit responses from students to ensure they are thinking about what the code is doing.

```java

    public class BlueTextActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_blue);

            // Create references to the views inside of activity_blue.xml

            Button blueButton = (Button) findViewById(R.id.button);

            // Create the onClickListener for the button

            OnClickListener buttonOnClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView textView = (TextView) findViewById(R.id.textView);
                    textView.setTextColor(Color.BLUE);
                }
            };

		    // Set your onClickListener to the button

            blueButton.setOnClickListener(buttonOnClickListener);
        }
	}
```


## Independent Practice (20 minutes)

> ***Note:*** _This can be a pair programming activity or done independently._

Create a Hello World app! I will share the code above to use as a reference.  

The app should do the following:

* Layout with two views: A TextView and a Button
	* The TextView should start off with no text, but have a text size of 30sp
	* The Button should have the text, "Say hello"
* When you click the button, the text in the TextView should say "Hello!"

> Check: Were you able to make the app say hello world!


## Conclusion (5 mins)
* How do you reference a view in Java?
* How does a OnClickListener work?
* How do you change view properties in XML? In Java?

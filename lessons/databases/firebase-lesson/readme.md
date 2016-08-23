---
title: Firebase
duration: "1:25"
creator:
    name: Drew Mahrt
    city: NYC
---

# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Firebase


### LEARNING OBJECTIVES
*After this lesson, you will be able to:*
- Explain the basics of Firebase
- Integrate Firebase into your apps

### STUDENT PRE-WORK
*Before this lesson, you should already be able to:*
- Describe the basics of databases

### INSTRUCTOR PREP
*Before this lesson, instructors will need to:*
- Read through the lesson
- Add additional instructor notes as needed
- Edit language or examples to fit your ideas and teaching style
- Open, read, run, and edit (optional) the starter and solution code to ensure it's working and that you agree with how the code was written

---

### LESSON GUIDE

| TIMING  | TYPE  | TOPIC  |
|:-:|---|---|
| 5 min  | [Opening](#opening-5-mins)  | Discuss lesson objectives |
| 10 min  | [Introduction](#introduction-what-is-firebase-10-mins)  | What is Firebase |
| 10 min  | [Demo](#demo-setting-up-firebase-10-mins)  | Setting Up Firebase |
| 10 min  | [Introduction](#introduction-storing-data-in-firebase-10-mins)  | Storing Data in Firebase |
| 5 min  | [Demo](#demo-retrieving-data-5-mins)  | Retrieving Data |
| 5 min  | [Demo](#demo-writing-data-5-mins)  | Writing Data |
| 5 min  | [Introduction](#introduction-child-events-5-mins)  | Child Events |
| 5 min  | [Demo](#demo-child-events-5-mins)  | Child Events |
| 10 min  | [Guided Practice](#guided-practice-binding-data-to-a-listview-10-mins)  | Binding data to a ListView |
| 5 min  | [Introduction](#introduction-firebaseui-5-mins)  | FirebaseUI |
| 5 min  | [Demo](#demo-firebaseui-5-mins)  | FirebaseUI |
| 10 min  | [Independent Practice](#independent-practice-topic-10-mins)  | Topic |
| 5 min  | [Conclusion](#conclusion-5-mins)  | Review / Recap |
<a name="opening"></a>
## Opening (5 mins)

Databases can be a crucial part of our apps, but we are normally limited by this data being isolated by each device. The obvious solution would be to host our database on the internet, and access it from the app. While this sounds like a great idea, this can result in long delays. Firebase provides us with a fast, easy to set up answer.

> Check: Have you heard of Firebase before.

***

<a name="introduction"></a>
## Introduction: What is Firebase (10 mins)

Firebase is a quick way to store your app's data on the cloud in a structured, but very flexible manner. While it holds your data in a database, it is fundamentally different from what we're used to in SQLite.

In Firebase, you don't set up Columns, Data types, keys, etc. ahead of time. You simply add and modify the data on-the-fly. Some of the biggest features of Firebase are how fast it is, and how quickly it can automatically sync the data on your apps. As soon as changes are made online, all apps connected to your Firebase database are notified of the changes, and can update their view accordingly. This is not just limited to Android, but reflects across all platforms (iOS, web, etc.).

[Let's watch a quick video.](https://www.youtube.com/watch?v=SLgHfH7KzXU)

> Check: Discuss with each other (2 mins) ideas where Firebase could be used.

***

<a name="demo"></a>
## Demo: Setting Up Firebase (10 mins)
Setting up Firebase takes a few steps. Let's go through this together.

1. Set up a new `Android Studio` project.

2. Create a new project in Firebase

  - Go to [Firebase](http://firebase.google.com)
  <img src="./screenshots/firebase1.png"/>

  - Let's click on `GET STARTED FOR FREE` to get started.  

  - We should now see this page called the Firebase Console.
  <img src="./screenshots/firebase2.png"/>

  - Click on `CREATE A NEW PROJECT`.

  - A dialog window should pop up that looks like this.
  <img src="./screenshots/firebase3.png"/>

  - Let's put in a name for our project so we can recognize it later.
  After doing that let's go ahead click `CREATE PROJECT`.

  - You should now see this screen.
  <img src="./screenshots/firebase4.png"/>

  - Let's click on `Database` in the left hand side navigation section.

  - You should be taken to the database page.
  <img src="./screenshots/firebase5.png"/>
  Right now it's empty; that's fine. We'll cover how to add data to our shiny new database.

  - Click on the name of your project. To be taken back to the home page of your project
  <img src="./screenshots/firebase6.png" />

3. Add Firebase to our Android app.

  - Let us now click on the `Add Firebase to your Android App`.  

  - You should see this screen pop up.
  <img src="./screenshots/firebase7.png"/>

  - Enter the package name of your project in the `Package name` input box. Then click `ADD APP`

  - You should now see this screen and automatically had a `google-services.json` file downloaded to your computer. Follow the instructions on this screen to add the `google-services.json` file to our project.
  <img src="./screenshots/firebase8.png"/>

  - Click `CONTINUE` and you will be taken to this screen. Follow the directions in this screen to configure your project
  to use Google Services.
  <img src="./screenshots/firebase9.png"/>

When your project successfully syncs and compiles;
congratulations on setting up Firebase in your project.

Now we need to add some library dependencies to our Android Project.

```groovy
dependencies {
...
  compile "com.google.firebase:firebase-core:9.2.0"
  compile "com.google.firebase:firebase-database:9.2.0"
}
```

> Check: Were you able to set it up correctly?

***

<a name="introduction"></a>
## Introduction: Storing Data in Firebase (10 mins)

As we mentioned before, data isn't stored in the same column structure we were used to in SQLite. Let's take a look at some sample data.

<img src="./screenshots/screenshot2.png"/>

> Check: What does this structure remind you of?

This is just like JSON data, with nested data structures of key-value pairs. In fact, we can even export it as a json file.

> Discuss advantages and disadvantages to storing all of the data like this, in JSON form. (3 mins)

This gives us great flexibility in terms of how we store the data, as well as quickly changing the details of the data, but we lose a lot of the structure and safety checks associated with a normal relational database.

Let's go ahead and add some stuff to our database. We will be retrieving it in our apps a little later.

Take 5 minutes and try to implement this data structure in Firebase.
```json
{
  "first_firebase_object" : {
    "first_item": "item one",
  },
  "second_firebase_object": {
    "first_item": "second first item",
  }
}
```
This is just one way to add data to our database. We'll go over how to do it from our apps later.

***

<a name="demo"></a>
## Demo: Retrieving Data (5 mins)

We are now going to grab the data we just manually put into our database.

First, let's set up our TextView.

```xml
<TextView
        android:id="@+id/textview_text"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hello World!"
        android:layout_centerInParent="true"/>
```

Now we need to set up our references to the views in our MainActivity.

```java
mCurrentText = (TextView) findViewById(R.id.textview_text);
```

The next thing we need to do is create a Firebase object. This object is where most of our interaction with Firebase will be funneled through.

```java
DatabaseReference mFirebaseRootRef;
```

```java
mFirebaseRootRef = FirebaseDatabase.getInstance().getReference();

DatabaseReference firebaseCurrentObjectRef = mFirebaseRootRef.child("name_of_a_firebase_object");
```

The `name_of_a_firebase_object` in the second line lets us select which node we want to access.


Just like with ClickListeners, Firebase has EventListeners that listen for changes in data on the database.

```java
firebaseCurrentObjectRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.child("item_one").getValue(String.class); //automatically parses into a String
                mCurrentText.setText(text);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
```

`onDataChange` is triggered whenever data is changed on Firebase, and `onCancelled` is triggered when there is an error with the connection.

Notice how we passed String.class to the getValue method. This is basically like casting the data being retrieved, but it works for **any Java object, even custom ones.**

Let's try it out!

> Go to your browser and change the data manually

That's all there is to reading data!

> Check: Was everyone able to complete the demo?

***

<a name="demo"></a>
## Demo: Writing Data (5 mins)

Now let's complete the app by adding the ability to write data.

> What do we need to add if we want to be able to dynamically put data into our database from our app?

Let's add an `EditText` and `Button` to allow us to update our database.

```xml
<EditText
    android:id="@+id/edit_text"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_centerHorizontal="true"
    android:layout_below="@id/current_text"/>

<Button
    android:id="@+id/submit_button"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_centerHorizontal="true"
    android:layout_below="@id/edit_text"
    android:text="Submit"/>
```

```java

EditText mNewText = (EditText) findViewById(R.id.edit_text);
Button mSubmitButton = (Button) findViewById(R.id.submit_button);

mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseCurrentTextRef.setValue(mNewText.getText().toString());
            }
        });java

```
> Check: What security problems could our current setup present?

***

<a name="introduction"></a>
## Introduction: Child Events (5 mins)

The ValueEvents that we just worked with are great for primitives and Objects, but if we want to work with things like lists of data, we want to use ChildEvents. ValueEvents passes back the entire object when a change is made, whereas ChildEvents can give us the individual items.

***

<a name="demo"></a>
## Demo: Child Events (5 mins)

Lets try adding a ChildEventListener to our code. It starts off just like our ValueEventListener, but the autocomplete looks very different.

```java
dbref.child("firebase_object").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String data = dataSnapshot.getValue(String.class); //didn't need to get the item_one child
                textView.setText(data);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
```

> Note: Remember that onChildAdded is called when pulling down existing data.

***

<a name="guided-practice"></a>
## Guided Practice: Binding data to a ListView (10 mins)

Let's take our knowledge of ChildEvents and apply it to a ListView.

> Prompt the students to help complete these steps.

1. add a ListView to the app, and create a reference to it in our MainActivity.
2. Create an ArrayList to hold our data
3. Create an ArrayAdapter
4. Add messages when onChildAdded is called, and call notifyDataSetChanged

```xml
<ListView
        android:id="@+id/list"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_below="@id/submit_button"/>
```

```java
public class MainActivity extends AppCompatActivity {
    TextView mCurrentText;
    EditText mNewText;
    Button mSubmitButton;
    ListView mListView;

    DatabaseReference mFirebaseRootRef;

    ArrayList<String> mMessages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCurrentText = (TextView) findViewById(R.id.current_text);
        mNewText = (EditText)findViewById(R.id.edit_text);
        mSubmitButton = (Button)findViewById(R.id.submit_button);
        mListView = (ListView)findViewById(R.id.list);

        mFirebaseRootRef = FirebaseDatabase.getInstance().getReference();

        final Firebase firebaseCurrentTextRef = mFirebaseRootRef.child("currentText");

        DatabaseReference firebaseMessageRef = mFirebaseRootRef.child("messages");

        mMessages = new ArrayList<>();

        firebaseCurrentTextRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                mCurrentText.setText("Latest Text: " + text);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });



        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseCurrentTextRef.setValue(mNewText.getText().toString());
            }
        });

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mMessages);
        mListView.setAdapter(adapter);

        firebaseMessageRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String message = dataSnapshot.getValue(String.class);
                mMessages.add(message);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
```

One last note: To add values to a list with auto-generated keys, you use the following code:

```java
firebaseMessageRef.push().setValue("test insert2");
```

> Check: Were you able to successfully solve the problem or complete the task?

***

<a name="introduction"></a>
## Introduction: FirebaseUI (5 mins)

ChildEvents work fine when adding data to a list, but certain UI operations such as changing and deleting can quickly become very complicated. Luckily, Firebase provides the FirebaseUI library to help manage UI elements connected to Firebase, perform easy authentication, and other useful things!

[Check out the documentation](https://github.com/firebase/FirebaseUI-Android)

> Check: Ask the students why change and remove would be hard to manage in the ListView example

***

<a name="demo"></a>
## Demo: FirebaseUI (5 mins)

First, we need to add FirebaseUI to our gradle file.

```
compile 'com.firebaseui:firebase-ui-database:0.5.1'
```

Since we want to change our ListView to work with FirebaseUI, we are going to use the  FirebaseListAdapter.

```java
FirebaseListAdapter<String> adapter = new FirebaseListAdapter<String>(this, String.class, android.R.layout.simple_list_item_1, firebaseMessageRef) {
            @Override
            protected void populateView(View view, String s, int i) {
                TextView textView  = (TextView)view.findViewById(android.R.id.text1);
                textView.setText(s);
            }
        };

        mListView.setAdapter(adapter);
```

> Check: What situations we would want to use FirebaseUI in.

***

<a name="ind-practice"></a>
## Independent Practice: Topic (10 mins)

Work with a partner to make a working version of Tic-Tac-Toe. You will create a layout with 9 EditTexts. The text you type into one of the EditTexts should sync immediately on the second person's device.

Hint: Try using addTextChangedListener

> Check: Were the you able to complete the activity?

***

<a name="conclusion"></a>
## Conclusion (5 mins)

Firebase is a very powerful tool for moving our databases to the cloud. The setup is extremely fast, and requires very little effort. Even though the structure is different than what we are used to with databases, our familiarity with JSON makes this transition much easier. Going forwards, consider adding Firebase to your apps to make syncing data much easier.

***

### ADDITIONAL RESOURCES
- [Firebase Quickstart](https://www.firebase.com/docs/android/quickstart.html)
- [Firebase UI](https://github.com/firebase/FirebaseUI-Android)

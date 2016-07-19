---
title: Fragments (part 2)
duration: "1:25"
creator:
    name: Drew Mahrt
    city: NYC
---
# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Fragments (part 2)

### LEARNING OBJECTIVES
*After this lesson, you will be able to:*
- Communicate between a fragment and its parent activity
- Communicate between fragments
- Describe and implement Master-Detail flow with Fragments
- Describe and implement FragmentPagerAdapter with ViewPagers

### STUDENT PRE-WORK
*Before this lesson, you should already be able to:*
- Describe and implement basic fragments

---
<a name="opening"></a>
## Opening (5 mins)

We've covered the very basics of fragments so far, but there are many other things you can do with them. Until this point, our fragments have been isolated, performing work in their own section of the activity. Today we will talk about fragments communicating with other parts of the app, as well as a common fragment design called the Master-Detail View. We will also quickly cover the FragmentPagerAdapter and how we can use it to make a paging view.

> Check: How do we create and add a fragment to an activity?

***

<a name="introduction"></a>
## Introduction: Communicating with an activity (5 mins)

Previously, we have viewed fragments as independent modules that are part of an Activity. While this is still true, Android provides the ability for a fragment to communicate back to its parent activity, and therefore to another fragment as well.

Since fragments are directly tied to a specific activity, they can easily access the activity to make method calls by using `getActivity()`. Likewise, an Activity can get a reference to a specific fragment using the `FragmentManager`, and call methods within it using an interface.

> Check: What types of situations would we need fragments to be able to communicate back and forth with the Activity?

***

<a name="demo"></a>
## Demo: Sharing Events with an Activity (30 mins)

> Instructor note: Encourage students to follow along if they're comfortable.

> Check: Can anyone predict what you must do when you'd like a fragment to talk to its hosting Activity.

When a fragment needs to talk back to its hosting Activity, you must perform the following steps:

1. Define an interface in the Fragment
2. Implement the interface in the parent Activity
3. Call the implemented method using a reference to the  class implementing the interface

From that callback, you can perform some action in the Activity, or pass on information to a different Fragment.

We need to create a new project where we will have two Fragments: A ListFragment and a normal Fragment. When we click on an item in the ListFragment, it will change text in the other Fragment.

Let's start by adding some data to show in the List. In your strings.xml, add the following

```xml
<string-array name="Planets">
        <item>Mercury</item>
        <item>Venus</item>
        <item>Earth</item>
        <item>Mars</item>
        <item>Jupiter</item>
        <item>Saturn</item>
        <item>Uranus</item>
        <item>Neptune</item>
    </string-array>
```

Now we can create the Java file for the Fragment.

MyListFragment.java
```java
public class MyListFragment extends Fragment {

  ListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

//Inflate your layout
      View v = inflater.inflate(R.layout.fragment_list, container, false);

//Get a reference to the XML created ListView      
      mListView = (ListView) v.findViewById(R.id.listview_fragment);

//Return the view containing the layout that was just inflated
          return v;
    }

//------onViewCreated--------------------------------------//

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//Instantiate a new ArrayAdapter using the string array we created in XML
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.Planets));

//Set adapter on ListView                
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                mListener.onPlanetSelected(
                        adapterView.getAdapter().getItem(position).toString());
            }
        });
    }
}
```

Finally, let's add the fragment to our activity and test it out.

First we need to set up the container for our fragment.
activity_main.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout      
  xmlns:android="http://schemas.android.com/apk/res/android"
  android:id="@+id/fragment_container"
  android:layout_width="match_parent"
  android:layout_height="match_parent"
  />

```

Next, we need to get the FragmentManager and begin a transaction to add the fragment
to the container.

```java
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment listFragment = MyListFragment.newInstance(null, this);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, listFragment).commit();
    }
```


It works so far!

Now it's time to add our second fragment, and try communicating with it.

The layout for this fragment will be simple, it will just have a TextView we can set.

fragment_detail.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical" android:layout_width="match_parent"
    android:layout_height="match_parent">
    <TextView
        android:id="@+id/text"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Select a planet!" />
</LinearLayout>
```

Now we need to create our DetailFragment.java

```java
public class DetailFragment extends Fragment {

TextView planetTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      View parentView = inflater.inflate(R.layout.fragment_detail,container,false);
        planetTextView = (TextView) v.findViewById(R.id.text);
        return parentView;
    }
}
```

> Check: Turn to the person next to you and explain the steps we went through, so far.

Now comes our final two steps. We need to set up the communication from the list fragment back to the activity, and from the activity to the detail fragment.

Remember, we need to set up an interface in the `MyListFragment` since that is where the `Activity` will be listening for the action. Then we need to allow the fragment to call on the activity.

In MyListFragment.java
```java
OnPlanetSelectedListener mListener;
...

public interface OnPlanetSelectedListener {
  public void onPlanetSelected(String selectedPlanet);
}

//@Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
//        try {
//            mListener = (onPlanetSelectedListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnPlanetSelectedListener");
//        }
//    }

public static MyListFragment newInstance(Bundle bundle, OnPlanetSelectedListener listener){
  Fragment fragment = new MyListFragment();
  fragment.setArgs(bundle);
  mListener = listener;
}


@Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.Planets));
        mListView.setAdapter(adapter);

//Set an OnItemClickListener on the ListView
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                mListener.onPlanetSelected(
                        adapterView.getAdapter().getItem(position).toString());
            }
        });
    }
```

`onAttach` is the recommended way to set up your OnPlanetSelectedListener but I found it is not very reliable in some cases. We'd much rather prefer to allow the Fragment to communicate to anyone having to listen to it.

Adding a static newInstance method allows us to provide the Fragment a reference to what ever class decides to implement our OnPlanetSelectedListener interface.

Now let's add a method in the detail fragment to accept the String we are going to put in the TextView.

```java

@Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            Bundle arguments = getArguments(); //This is similar to the getIntent() method in Activities
            String planet = arguments.getString("selected_planet");

            planetTextView.setText("Planet selected: "+ planet);

        }

```

Finally, we need to implement our callback interface in the `MainActivity` and then replace our MyListFragment with the DetailFragment.

```java
@Override
    public void onPlanetSelected(String selectedPlanet) {
      Bundle bundle = new Bundle();
      bundle.putString("selected_planet", selectedPlanet);
        Fragment detailFragment = DetailFragment.newInstance(bundle);
        FragmentManager supportManager =
        getSupportFragmentManager();
        FragmentTransaction transaction = supportManager.beginTransaction();

        transaction.replace(R.id.fragment_container, detailFragment).commit();

    }
```

> Check: Turn to the person next to you and explain the steps we went through to make the two fragments interact with each other.

***

<a name="demo"></a>
## Guided Practice: ViewPager Flow (25 mins)

The `ViewPager` flow can accomplish a paging layout. It allows you to put screens that are related to each other together in one screen, but not really.

Open the starter code project. The starter code provides us with a few classes to help with ViewPagers.

Let's walk through all of the parts of the starter code to understand how it works and implement a `DetailFragment`
that we can replace the List with when we click on an item in it when the user

***

<a name="conclusion"></a>
## Conclusion (5 mins)

Now that we have a more complete understanding of how fragments work, and how they interact with the activity as well as each other, we get a better idea of when they are appropriate to use. Setting up the interaction between a fragment and an activity might seem like a lot of work at first, but with practice it becomes easier.

- Describe the steps needed to make the two fragments interact with each other.


***

### ADDITIONAL RESOURCES
- [Fragments Developers Guide](http://developer.android.com/guide/components/fragments.html)
- [Adaptive UI Guide](http://developer.android.com/training/multiscreen/adaptui.html)

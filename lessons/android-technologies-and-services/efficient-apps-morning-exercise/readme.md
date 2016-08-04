---
title: Efficient Apps Morning Exercise
type: Morning exercise
duration: "1:00"
creator:
    name: Drew Mahrt
    city: NYC
---

# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Efficient Apps Morning Exercise

> ***Note:*** _This morning exercise is to be done in pairs._

## Exercise

This morning's exercise will be split into two parts. For the first 30 minutes, **in pairs**, choose at least three of your favorite Android apps to analyze and identify at least 5 places in each app where you believe threading is used to make the app more efficient and provide a smoother user experience. Be prepared to explain your choices.

For the second half, with one person "driving," adapt the starter code to make the provided networking code execute in a worker thread instead of the UI thread. **You don't need to modify the downloadPages method**.  

#### Requirements

- Choose at least 3 apps and identify at least 5 places where threading is used
- Convert the starter-code to use an AsyncTask

**Bonus:**
- Find more examples for part 1

#### Starter code

The [starter-code](starter-code) downloads HTML from the internet in the UI thread. Modify it to do execute in a worker thread. Again, **you don't need to modify the downloadPages method**.

#### Deliverable

An Android app that uses an AsyncTask.

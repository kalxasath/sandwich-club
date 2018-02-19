[![Scholarship Google](https://img.shields.io/badge/scholarship-Google-brightgreen.svg)](https://www.google.com) [![Online Classes udacity](https://img.shields.io/badge/online%20classes-Udacity-ff69b4.svg)](https://www.udacity.com) [![License Apache 2.0](https://img.shields.io/badge/license-Apache%202.0-green.svg)](https://github.com/fjoglar/android-dev-challenge/blob/master/LICENSE.txt) [![Platform Android](https://img.shields.io/badge/platform-Android-blue.svg)](https://www.android.com) [![Language Java](https://img.shields.io/badge/language-Java-orange.svg)](https://www.java.com)

# Android Developer Nanodegree Program Scholarship

![Android Developer Nanodegree Program Scholarship badge](https://raw.githubusercontent.com/kalxasath/Sandwich-Club/master/assets/android-dev-challenge-badge.png)


# Sandwich Club Project Starter Code

## Project Overview
In this project, you will complete the **Sandwich Club** app to
show the details of each sandwich once it is selected.

## Why this Project

Building a layout and populating its fields from data received as JSON
is a common task for Android Developers. Although JSON parsing is usually
done using libraries, writing the JSON parsing for  this project will
help you to better understand how it is processed.

## What Will I Learn?
Through this project, you will:
- Learn how to submit projects for review
- Practice JSON parsing to a model object
- Design an activity layout
- Populate all fields in the layout accordingly

## How Do I Complete this Project?
Download the [Sandwich Club app starter code.](https://github.com/udacity/sandwich-club-starter-code)

Design the layout for the detail activity so the different elements
display in a sensible way. Implement the JSON parsing in JsonUtils so it
produces a Sandwich Object that can be used to populate the UI that you designed.

## Feb 20, 2018 - Fix, Refactor
**Fixing Core Functionality**
- all the views are wrapped with the **ScrollView**

**Fixing Suggestions**
- In the class DetailActivity the names of the member variables have been refactored to meet the lowerCamelCase format, as mentioned in [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)

- In the **ScrollView** an id was added, so that Android will retain the scroll position on configuration changes.

- comments were added to the methods
  * populateUI(Sandwich sandwich)
  * parseSandwichJson(String json)

- in the class, parseSandwichJson changed the getString with optString for safety about throwing JSONException if there is no such key.
  * getString throws a JSONException if there is no such key.
  * optString returns an empty string if there is no such key. If the value is not a string and is not null, then it is converted to a string.

- [butterknife](http://jakewharton.github.io/butterknife/) was suggested but not added due to temporary [error](https://github.com/JakeWharton/butterknife/issues/1130)

## Feb 18, 2018 - Initial Commit

> Written with [StackEdit](https://stackedit.io/).

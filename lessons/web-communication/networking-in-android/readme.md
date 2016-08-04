---
title: Networking in Android
duration: "1:35"
creator:
    name: Yuliya Kaleda
    city: NYC
---

# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Networking in Android

### LEARNING OBJECTIVES
*After this lesson, you will be able to:*
- Use URLconnection to connect to an endpoint
- Perform GET and POST Requests
- Utilize networking in the background with AsyncTask
- Process data returned from a network connection

### STUDENT PRE-WORK
*Before this lesson, you should already be able to:*
- Work with JSON
- Start a background thread using AsyncTask

### INSTRUCTOR PREP
*Before this lesson, instructors will need to:*
- Read through the lesson
- Add additional instructor notes as needed
- Edit language or examples to fit your ideas and teaching style
- Open, read, run, and edit (optional) the starter and solution code to ensure it's working and that you agree with how the code was written


### LESSON GUIDE

| TIMING  | TYPE  | TOPIC  |
|:-:|---|---|
| 5 min  | [Opening](#opening-5-mins)  | Discuss lesson objectives |
| 10 min  | [Introduction](#introduction-check-the-network-connection-10-mins)  | Check the Network Connection |
| 10 min  | [Guided Practice](#guided-practice-check-the-network-connection-10-mins)  | Check the Network Connection |
| 15 min  | [Demo](#demo-connect-and-download-data-15-mins)  | Connect and Download Data |
| 5 min  | [Introduction](#introduction-perform-network-operations-on-a-separate-thread-5-mins)  | Perform Network Operations on a Separate Thread |
| 10 min  | [Guided Practice](#guided-practice-perform-network-operations-on-a-separate-thread-10-mins)  | Perform Network Operations on a Separate Thread |
| 15 min  | [Guided Practice](#guided-practice-transition-from-curl-to-android-15-mins)  | Transition from cURL to Android |
| 5 min  | [Introduction](#introduction-process-data-returned-from-a-network-connection-5-mins)  | Process data returned from a network connection |
| 10 min  | [Demo](#demo-process-data-returned-from-a-network-connection-10-mins)  | Process data returned from a network connection |
| 5 min  | [Independent Practice](#independent-practice-process-data-returned-from-a-network-connection-5-mins)  | Process data returned from a network connection |
| 5 min  | [Conclusion](#conclusion-5-mins)  | Review / Recap |
---
## Opening (5 mins)

> Check: Ask the students to identify the main use cases for networking in Android.

Networking in android is mainly about the ability to send and receive data from remote server. This data can be either a plain text, XML, JSON, image or a video stream. Android the HttpURLConnection to make network calls.

## Introduction: Check the Network Connection (10 mins)

Every application that deals with networking requires permissions to be declared in the Manifest:  

```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```

Before your app attempts to connect to the network, it should check to see whether a network connection is available using `getActiveNetworkInfo()` and `isConnected()`. Remember, the device may be out of range of a network, or the user may have disabled both Wi-Fi and mobile data access. Thus, to improve user experience before getting connected, you should always check the connection:

```java
ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
if (networkInfo != null && networkInfo.isConnected()) {
    // the connection is available
} else {
    // the connection is not available
}
```

## Guided Practice: Check the Network Connection (10 mins)

Together, let's create a new project. Let's add a ScrollView and TextView to the layout.

Add the code above and then, with a partner, write code that displays a toast to prompt the user about the connectivity state (whether the app is connected or not).

> Check:  Ask students to share out with 1 minute left.

## Demo: Connect and Download Data (15 mins)

To perform a GET method and download your data you can use `HttpURLConnection`.

After you call `connect()`, you can get an InputStream of the data by calling `getInputStream()`. An InputStream is a readable source of bytes. Once you get an InputStream, it's common to decode or convert it into a target data type.

```java
private String downloadUrl(String myUrl) throws IOException, JSONException {
    InputStream is = null;
    try {
      URL url = new URL(myUrl);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      conn.setDoInput(true);

      // Starts the query
      conn.connect();
      is = conn.getInputStream();

      // Converts the InputStream into a string
      String contentAsString = readIt(is);
      return contentAsString;

      // Makes sure that the InputStream is closed after the app is
      // finished using it.
    } finally {
      if (is != null) {
        is.close();
      }
    }
  }
```

In the above snippet, the `downloadUrl()` method takes the given URL and uses it to connect to the network via `HttpURLConnection`. Once a connection has been established, the app uses the method `getInputStream()` to retrieve the data as an InputStream.

The InputStream represents the text/data to be returned from the HTTP call. To see the data, we will have to convert the InputStream to a string:

```java
public String readIt(InputStream stream) throws IOException {
    StringBuilder sb = new StringBuilder();
    BufferedReader br = new BufferedReader(new InputStreamReader(stream));
    String read;

    while((read = br.readLine()) != null) {
      sb.append(read);
    }
    return sb.toString();
  }
```

> Check: To better understand what we'll be working with, take 5 minutes and work with the person next to you to get on the web and research/find the contents of a web response.  Be ready to share!

## Introduction: Perform Network Operations on a Separate Thread (5 mins)

Network operations can involve unpredictable delays. To prevent this from causing a poor user experience, always perform network operations on a separate thread from the UI. The `AsyncTask` class provides one of the simplest ways to fire off a new task from the UI thread. As you may remember from the threading lesson, opening the connection and performing network operations should be done in `doInBackgroud()` method, while updating the UI should be coded in  `onPostExecute()` method.

## Guided Practice: Perform Network Operations on a Separate Thread (10 mins)

Having the helper method `downloadUrl()`, with a partner, create a new AsyncTask, which should be called after you check if there is network connection and will return some API data. Display the data in the text view. Remember that updating the UI should be always done on the main thread (use  `onPostExecute()` method).

>Instructor note: The solution code is [available here](solution-code).

## Guided Practice: Transition from Postman to Android (15 mins)

Now that we've gone through the steps to actually retrieve raw data from a URL, it's important to understand how our previous lessons with threading and networking can be directly translated to Android. For instance, let's take a look at the example of `http://espn.com`.

Simply replace your variable that holds the URL with `http://espn.com`, and you should see the HTML for the page, just like we did in Postman.

Take a minute with your partner to figure out how we would change the following request to happen in our Android app.

#### POST Requests

POST Requests can be a little more complicated. Some extra code is needed. Remember that data in the POST request is not sent as parameters in the URL like in a GET request. The following code change will allow you to perform a POST request. There are multiple ways to accomplish this, so this is only one example:

```java
public String performPost() throws IOException, JSONException{
    DataOutputStream os = null;
    InputStream is = null;
    try {
      URL url = new URL("http://httpbin.org/post");
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      String urlParameters  = "param1=a&param2=b&param3=c";
      byte[] postData = urlParameters.getBytes( StandardCharsets.UTF_8 );
      int postDataLength = postData.length;
      conn.setRequestMethod( "POST" );
      conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
      conn.setRequestProperty( "charset", "utf-8");
      conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
      os = new DataOutputStream( conn.getOutputStream());
      os.write( postData );
      os.flush();

      is = conn.getInputStream();
      return readIt(is);
    }finally {
      if(is != null){
        is.close();
      }
      if(os != null){
        os.close();
      }
    }
  }
```

The parameters are turned into raw data, and then passed in with the request.

Now, take 3 minutes with your partner to try making the following POST Request happen in Android.

URL: http://httpbin.org/post
param1 = hello
param2 = 30


> Check: Ask the students to explain why we need different code for the GET and POST requests.

## Introduction: Process data returned from a network connection (5 mins)  

As we could see from our app we have received pure/raw data and displayed it in the text view. But it is not really useful for our users because the data has JSON syntax and is not processed in a user friendly way. Moreover, all data is not always necessary. There is usually just some piece of information that is relevant and interesting to users.

As we have learned from the JSON lesson, a common data structure used to represent the data is an array. Thus if we have an array as a root element, we should instantiate a JSONArray and pass in our pure JSON string:  


```java
JSONArray array = new JSONArray(contentAsString);  
```  

To instantiate a JSONObject we would write the following:  

```java
JSONObject object = new JSONObject();
```  

To get an attribute of an object we need to use ```getType()``` method and pass in its exact name from JSON data:  

```java
int any = repo.getInt("number");
```

> Check: Why do we need to parse the JSON object?

## Demo: Process data returned from a network connection (10 mins)  

> Instructor Note: In the app DownloadData, uncomment the code (that is marked with a comment to uncomment it) then, explain to the students how processing data returned from a network connection works. Change the return value from `contentAsString` to `processedJson` that will be displayed in the text view.

In our app we have used the GitHub API that returns information about repos that a user has on GitHub. Let's process JSON in the way the user will see just the names of the repos.

Our raw JSON data looks like this:

```
[
  {
    "id": 31634107,
    "name": "12-days-of-Christmas",
    "full_name": "Yuliya-Kaleda/12-days-of-Christmas",
    "owner": {
      "login": "Yuliya-Kaleda",
      "id": 10750398,
      "avatar_url": "https://avatars.githubusercontent.com/u/10750398?v=3",
      "gravatar_id": "",
      "url": "https://api.github.com/users/Yuliya-Kaleda",
      "html_url": "https://github.com/Yuliya-Kaleda",
      "followers_url": "https://api.github.com/users/Yuliya-Kaleda/followers",
      "following_url": "https://api.github.com/users/Yuliya-Kaleda/following{/other_user}",
      "gists_url": "https://api.github.com/users/Yuliya-Kaleda/gists{/gist_id}",
      "starred_url": "https://api.github.com/users/Yuliya-Kaleda/starred{/owner}{/repo}",
      "subscriptions_url": "https://api.github.com/users/Yuliya-Kaleda/subscriptions",
      "organizations_url": "https://api.github.com/users/Yuliya-Kaleda/orgs",
      "repos_url": "https://api.github.com/users/Yuliya-Kaleda/repos",
      "events_url": "https://api.github.com/users/Yuliya-Kaleda/events{/privacy}",
      "received_events_url": "https://api.github.com/users/Yuliya-Kaleda/received_events",
      "type": "User",
      "site_admin": false
    },
    "private": false,
    "html_url": "https://github.com/Yuliya-Kaleda/12-days-of-Christmas",
    "description": "",
    "fork": false,
    "url": "https://api.github.com/repos/Yuliya-Kaleda/12-days-of-Christmas",
    "forks_url": "https://api.github.com/repos/Yuliya-Kaleda/12-days-of-Christmas/forks",
    "keys_url": "https://api.github.com/repos/Yuliya-Kaleda/12-days-of-Christmas/keys{/key_id}",
    "collaborators_url": "https://api.github.com/repos/Yuliya-Kaleda/12-days-of-Christmas/collaborators{/collaborator}",
    "teams_url": "https://api.github.com/repos/Yuliya-Kaleda/12-days-of-Christmas/teams",
    "hooks_url": "https://api.github.com/repos/Yuliya-Kaleda/12-days-of-Christmas/hooks",
    "issue_events_url": "https://api.github.com/repos/Yuliya-Kaleda/12-days-of-Christmas/issues/events{/number}",
    "events_url": "https://api.github.com/repos/Yuliya-Kaleda/12-days-of-Christmas/events",
    "assignees_url": "https://api.github.com/repos/Yuliya-Kaleda/12-days-of-Christmas/assignees{/user}",
    "branches_url": "https://api.github.com/repos/Yuliya-Kaleda/12-days-of-Christmas/branches{/branch}",
    "tags_url": "https://api.github.com/repos/Yuliya-Kaleda/12-days-of-Christmas/tags",
    "blobs_url": "https://api.github.com/repos/Yuliya-Kaleda/12-days-of-Christmas/git/blobs{/sha}",
    "git_tags_url": "https://api.github.com/repos/Yuliya-Kaleda/12-days-of-Christmas/git/tags{/sha}",
    "git_refs_url": "https://api.github.com/repos/Yuliya-Kaleda/12-days-of-Christmas/git/refs{/sha}",
    "trees_url": "https://api.github.com/repos/Yuliya-Kaleda/12-days-of-Christmas/git/trees{/sha}",
    "statuses_url": "https://api.github.com/repos/Yuliya-Kaleda/12-days-of-Christmas/statuses/{sha}",
    "languages_url": "https://api.github.com/repos/Yuliya-Kaleda/12-days-of-Christmas/languages",
    "stargazers_url": "https://api.github.com/repos/Yuliya-Kaleda/12-days-of-Christmas/stargazers",
    "contributors_url": "https://api.github.com/repos/Yuliya-Kaleda/12-days-of-Christmas/contributors",
    "subscribers_url": "https://api.github.com/repos/Yuliya-Kaleda/12-days-of-Christmas/subscribers",
    "subscription_url": "https://api.github.com/repos/Yuliya-Kaleda/12-days-of-Christmas/subscription",
    "commits_url": "https://api.github.com/repos/Yuliya-Kaleda/12-days-of-Christmas/commits{/sha}",
    "git_commits_url": "https://api.github.com/repos/Yuliya-Kaleda/12-days-of-Christmas/git/commits{/sha}",
    "comments_url": "https://api.github.com/repos/Yuliya-Kaleda/12-days-of-Christmas/comments{/number}",
    "issue_comment_url": "https://api.github.com/repos/Yuliya-Kaleda/12-days-of-Christmas/issues/comments{/number}",
    "contents_url": "https://api.github.com/repos/Yuliya-Kaleda/12-days-of-Christmas/contents/{+path}",
    "compare_url": "https://api.github.com/repos/Yuliya-Kaleda/12-days-of-Christmas/compare/{base}...{head}",
    "merges_url": "https://api.github.com/repos/Yuliya-Kaleda/12-days-of-Christmas/merges",
    "archive_url": "https://api.github.com/repos/Yuliya-Kaleda/12-days-of-Christmas/{archive_format}{/ref}",
    "downloads_url": "https://api.github.com/repos/Yuliya-Kaleda/12-days-of-Christmas/downloads",
    "issues_url": "https://api.github.com/repos/Yuliya-Kaleda/12-days-of-Christmas/issues{/number}",
    "pulls_url": "https://api.github.com/repos/Yuliya-Kaleda/12-days-of-Christmas/pulls{/number}",
    "milestones_url": "https://api.github.com/repos/Yuliya-Kaleda/12-days-of-Christmas/milestones{/number}",
    "notifications_url": "https://api.github.com/repos/Yuliya-Kaleda/12-days-of-Christmas/notifications{?since,all,participating}",
    "labels_url": "https://api.github.com/repos/Yuliya-Kaleda/12-days-of-Christmas/labels{/name}",
    "releases_url": "https://api.github.com/repos/Yuliya-Kaleda/12-days-of-Christmas/releases{/id}",
    "created_at": "2015-03-04T02:38:58Z",
    "updated_at": "2015-03-04T02:47:51Z",
    "pushed_at": "2015-03-04T02:47:51Z",
    "git_url": "git://github.com/Yuliya-Kaleda/12-days-of-Christmas.git",
    "ssh_url": "git@github.com:Yuliya-Kaleda/12-days-of-Christmas.git",
    "clone_url": "https://github.com/Yuliya-Kaleda/12-days-of-Christmas.git",
    "svn_url": "https://github.com/Yuliya-Kaleda/12-days-of-Christmas",
    "homepage": null,
    "size": 104,
    "stargazers_count": 0,
    "watchers_count": 0,
    "language": "Java",
    "has_issues": true,
    "has_downloads": true,
    "has_wiki": true,
    "has_pages": false,
    "forks_count": 0,
    "mirror_url": null,
    "open_issues_count": 0,
    "forks": 0,
    "open_issues": 0,
    "watchers": 0,
    "default_branch": "master"
  },
```

This is a function that processes JSON data and returns a list of repo names:  

```java
private String parseJson(String contentAsString) throws JSONException {
    String repoList = "";
    JSONArray array = new JSONArray(contentAsString);
    for (int i = 0; i < array.length(); i++)
    {
      JSONObject repo = array.getJSONObject(i);
      String repoName = repo.getString("name");
      repoList += repoName + " \n";
    }
    return repoList;
  }
```

> Check: Take 1 minute and work with the person next to you to discuss what the code above is accomplishing.

As we can see our root element in JSON is an array that has some repo objects. Thus we instantiate `JSONArray` and pass in our pure JSON data as an argument. Then we iterate through JSON array and get each object repo `JSONObject repo`. We retrieve the name of the repo by calling `getString("name")` on each object.

## Independent Practice: Process data returned from a network connection (5 mins)  

Using the same DownloadData app change the code in the method  `parseJson()` to show  owners name for each repoinstead of returning its  `name `.


## Conclusion (5 mins)

- What is the main use case of network connection in Android?
- What permissions should be added to the Manifest file to establish network connection?
- What thread should network operations be performed?

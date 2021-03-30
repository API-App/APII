Unit 8: Group Milestone - README Example
===

# APII

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)

## Overview
### Description
This app will help developers to find APIs and explore project ideas.

### App Evaluation
- **Category:** Productivity 
- **Mobile:** This app provides a streamlined mobile version of the [public API API](https://api.publicapis.org/).
- **Story:** Developers will use this app to find inspiration for projects, and discover intersting APIs.
- **Market:** This API is targeted at programers who intend to work on personnal projects.
- **Habit:** This app would be used infrequently to help find new project ideas or useful APIs.
- **Scope:** Provide a streamlined interface for finding APIs to facilitate productive development. Potentially providing a platform for people to discuss APIs.

## Product Spec
### 1. User Stories (Required and Optional)

**Required Must-have Stories**

* Show list of APIs
  * Sorted alphabetically and by category
* Sort APIs by catagory
* Detail view for APIs
* Search bar
  * Search in APIs
  * Search in catagories


**Optional Nice-to-have Stories**

* Sharing APIs
* Applying filters for the API
  * HTTPS filter
  * OAuth filter
  * API key filter
  * CORS filter
* Share button  

### 2. Screen Archetypes

* Login 
* Register - User signs up or logs into their account
   * Upon Download/Reopening of the application, the user is prompted to log in to gain access to their profile information to be properly matched with another person. 
   * ...
* Messaging Screen - Chat for users to communicate (direct 1-on-1)
   * Upon selecting music choice users matched and message screen opens
* Profile Screen 
   * Allows user to upload a photo and fill in information that is interesting to them and others
* Song Selection Screen.
   * Allows user to be able to choose their desired song, artist, genre of preference and begin listening and interacting with others.
* Settings Screen
   * Lets people change language, and app notification settings.

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Music selection
* Profile
* Settings

Optional:
* Music/Encounter Queue
* Discover (Top Choices)

**Flow Navigation** (Screen to Screen)
* Forced Log-in -> Account creation if no log in is available
* Music Selection (Or Queue if Optional) -> Jumps to Chat
* Profile -> Text field to be modified. 
* Settings -> Toggle settings

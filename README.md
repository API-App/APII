Unit 8: Group Milestone - APII
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
- **Story:** Developers will use this app to find inspiration for projects, and discover interesting APIs.
- **Market:** This API is targeted at programmers who intend to work on personal projects.
- **Habit:** This app would be used infrequently to help find new project ideas or useful APIs.
- **Scope:** Provide a streamlined interface for finding APIs to facilitate productive development. Potentially providing a platform for people to discuss APIs.

## Product Spec
### 1. User Stories (Required and Optional)

**Required Must-have Stories**

* Show list of APIs
  * Sorted alphabetically and by category
* Sort APIs by category
* Detail view for APIs
* Search bar
  * Search in APIs
  * Search in categories
* Help page


**Optional Nice-to-have Stories**

* Get Random API 
* Applying filters for the API
  * HTTPS filter
  * OAuth filter
  * API key filter
  * CORS filter
* Share button  

### 2. Screen Archetypes

* Stream Screens
  * Allows users to view a list containing the APIs (API Stream).
  * Allows users to view a list containing the categories (Category Screen).
* Search Screen
  * Let's users search for specific APIs.
* Detail Screen
  * Lists all information about an API.
* Help Screen
  * Provides information about the app.

### 3. Navigation

**Menu Navigation** (Menu Item to Screen)

* All
* Category
* Random (optional)
* Help


**Flow Navigation** (Screen to Screen)
* Open App -> API Stream (All)
* Category Stream -> API Stream (Category)
* Streams (API & Category) -> Filter & Search 
* API Stream -> Detail View
* Detail View -> Share  

## Wireframes
<img src="https://github.com/API-App/APII/blob/main/wireframe/APII_Wireframe.jpg" width=800><br>

### [BONUS] Digital Wireframes & Mockups
<img src="https://github.com/API-App/APII/blob/main/wireframe/Menu.png" height=200>
<img src="https://github.com/API-App/APII/blob/main/wireframe/API%20Stream%20(All).png" height=200>
<img src="https://github.com/API-App/APII/blob/main/wireframe/Category%20Stream.png" height=200>
<img src="https://github.com/API-App/APII/blob/main/wireframe/API%20Stream%20(Category).png" height=200>
<img src="https://github.com/API-App/APII/blob/main/wireframe/Help%20Page.png" height=200>
<img src="https://github.com/API-App/APII/blob/main/wireframe/Detail%20View.png" height=200>

### [BONUS] Interactive Prototype
<img src="https://github.com/API-App/APII/blob/main/wireframe/APII_Wireframe_Animation.gif" width=200>


## Schema 
### Data Models
#### API:

   | Property      | Type     | Description              |
   | ------------- | -------- | -------------------------|
   | title         | String   | name of API              |
   | description   | String   | description of API       |
   | auth          | String   | type of authentification |
   | https         | Boolean  | does API support https?  |
   | cors          | String   | does API support cors?   |
   | link          | String   | link to API website      |
   | category      | String   | category of API          |
   
### Networking
#### Existing API Endpoints
##### Public API for Public APIs 
- Base URL - [https://api.publicapis.org/](https://api.publicapis.org/)

   HTTP Verb | Endpoint      | Description
   ----------|---------------|------------
    `GET`    | /entries      | returns all APIs
    `GET`    | /random       | returns a random API
    `GET`    | /categories   | returns list of all categories
    `GET`    | /health       | checks for status of API server
    
## Sprint 1
### Completed Stories
- [x] Add API Model - Create API Model with necessary functions
- [x] Changed theme colors and added basic navigation

### Video Walkthrough
Here's a walkthrough of implemented user stories:

<img src="https://github.com/API-App/APII/blob/main/gifs/sprint1.gif" width=250><br>

GIF created with [Gif Maker](https://play.google.com/store/apps/details?id=com.media.zatashima.studio&hl=en_US&gl=US).

## Sprint 2
### Completed Stories
- [x] API Recycler View
- [x] Detail View
- [x] Random API button

### Video Walkthrough
Here's a walkthrough of implemented user stories:

<img src="https://github.com/API-App/APII/blob/main/gifs/APII_ApiStream_and_DetailView.gif" width=200>

## Sprint 3
### Completed Stories
- [x] Seaching
   - [x] Add search icon to the top in the appropriate menus
   - [x] Add search logic for categories and API stream
- [x] Hooked up the DetailView to the API stream
- [X] Set up the Category Recycler View
- [x] Refactored API Stream to accept parameters for next sprint
- [x] Sort categories

### Video Walkthrough
Here's a walkthrough of implemented user stories:

<img src="https://github.com/API-App/APII/blob/main/gifs/sprint3.gif" width=200>

GIF created with [Gif Maker](https://play.google.com/store/apps/details?id=com.media.zatashima.studio&hl=en_US&gl=US).

## Final Sprint
### Completed Stories
- [x] Sharing Apis
- [x] App icon made
- [x] App theme changes 

### Video Walkthrough
Here's a walkthrough of implemented user stories:

<img src="https://github.com/API-App/APII/blob/main/APII_Final_Sprint.gif" width=200>

# Portfolio #
Android apps I worked on

## Inventory App ##

#### *Created an app to trock the inventory of a store* ####

<img src="https://github.com/HansG26/Portfolio/blob/master/Screenshots/InventoryApp3.png" width="250"/> <img src="https://github.com/HansG26/Portfolio/blob/master/Screenshots/InventoryApp1.png" width="250"/> <img src="https://github.com/HansG26/Portfolio/blob/master/Screenshots/InventoryApp2.png" width="250"/>

### Specifications ###

* App contains a list of saved products and a button to add a new product
* Each list item contains a sale button that reduces the quantity of that product by one
* Detail layout for each item displays the remaining information stored in the database
* App has buttons to delete a specific item or all items at once
* 'Order more' button is present for existing products. Launches mail client with given information already filled in
* User can select an image from internal storage and link it to a product
* App contains all necessary validations and error checks

### What I learned ###

* Saving information inside a database
* Integrating Android’s file storage systems into a database
* Presenting information from databases to users
* Creating intents to other apps using information the user stored

## Scoop ##

#### *Created an app to show recent news stories using the Guardian API* ####

<img src="https://github.com/HansG26/Portfolio/blob/master/Screenshots/Scoop1.png" width="250"/> <img src="https://github.com/HansG26/Portfolio/blob/master/Screenshots/Scoop2.png" width="250"/> <img src="https://github.com/HansG26/Portfolio/blob/master/Screenshots/Scoop3.png" width="250"/>

### Specifications ###

* User can choose to view all topics or just a specific one
* App remembers the preference of the user by making use of SharedPreferences
* App queries the Guardian api to fetch news stories related to the topic chosen by the user
* Clicking on a story opens it in the user’s browser

### What I learned ###

* Connecting to an API
* Parsing the response of an API
* Working with SharedPreferences
* Handling errors
* Updating information regularly
* Doing network operations

## Book Listing App ##

#### *Created an app to list books on a given search term using the Google Books API* ####

<img src="https://github.com/HansG26/Portfolio/blob/master/Screenshots/BookListingApp1.png" width="250"/> <img src="https://github.com/HansG26/Portfolio/blob/master/Screenshots/BookListingApp2.png" width="250"/> <img src="https://github.com/HansG26/Portfolio/blob/master/Screenshots/BookListingApp3.png" width="250"/>

### Specifications ###

* User can enter a word or phrase to serve as a search query
* Fetches book data related to the search query via an HTTP request from the Google Books API
* Uses classes such as HttpUriRequest and HttpURLConnection
* Checks whether device is connected to the internet and responds properly
* Network call occurs off the UI thread using an AsyncTaskLoader

### What I learned ###

* Fetching data from an API
* Using an AsyncTask
* Parsing a JSON response

## Antwerp Tour Guide ##

#### *Created an app to guide a user around Antwerp* ####


<img src="https://github.com/HansG26/Portfolio/blob/master/Screenshots/AntwerpTourGuide1.png" width="250"/> <img src="https://github.com/HansG26/Portfolio/blob/master/Screenshots/AntwerpTourGuide2.png" width="250"/> <img src="https://github.com/HansG26/Portfolio/blob/master/Screenshots/AntwerpTourGuide3.png" width="250"/>


### Specifications ###
* User can navigate between lists using a central screen, a NavDrawer, or a ViewPager
* Each list item contains information about a museum, restaurant, nightlife or varia
* Contains a custom object for storing location information
* Uses a custom ArrayAdapter to populate the layout with views based on instances of the custom class

### What I learned ###
* Selecting a data structure to store lists of information
* Building layouts to display those lists of data
* Navigating between those lists using a ViewPager
* Creating my own custom class from scratch

## GeoTrivia ##

#### *Designed and implemented a quiz app about geography* ####

<img src="https://github.com/HansG26/Portfolio/blob/master/Screenshots/GeoTrivia1.png?raw=true" width="250"/> <img src="https://github.com/HansG26/Portfolio/blob/master/Screenshots/GeoTrivia2.png?raw=true" width="250"/> <img src="https://github.com/HansG26/Portfolio/blob/master/Screenshots/GeoTrivia3.png?raw=true" width="250"/>

### Specifications ###

* Calculates the number of correct answers and does not include incorrect answers in the count
* Includes a button for the user to submit their answers and receive a score
* Grading button displays a toast which displays the results of the quiz

### What I learned ###

* Taking an app layout from drawing to XML code
* Creating, positioning, and styling views
* Creating interactivity through Java code
* Commenting code
* Using Picasso library for handling images

# Assignment - zalora (CATastrophic)

To develop a  simple app that shows cat images in a gallery mode. [API] (https://api.thecatapi.com/v1/images/search?limit=30&page=1&mime_types=png&&order=Desc/) is given, where we can find endless cat images.


## Programming Language, Design Pattern

### KOTLIN,MVVM

## Dependencies 

### Hilt, Coroutine, Android Jetpack Paging 3.0, Room 

- Hilt is been used for providing di support throughout the Application

- Coroutine is been used to communicating between modules

- Paging 3.0 from android jetpack is been used for loading data smoothly in a gridview of 3 column smoothly.

- Room Database is been used to implement Support offline-first architecture for low-speed connections optimisation and offline capabilities by storing data.


## Primary Overview
- Application consist of 2 activities (CatListActivity, CatViewFull Screen) where CatListActivity responsible for showing list of cat from api and CatViewFull responsible for showing the image fullscreen where pinch to zoom feature will also be found

- For Smooth scrolling, handling footer and loading from api by small chunk, paging 3.0 is been used where "CatImagesRepository" is responsible for collecting data from local/network storage and transferring "pageList" to "CatListAdapter".

- To implement Support offline-first , Room data base is been used where CatMediator is been used to get the stream of list from database where the data is already loaded to a PagingList


# Package Contains

## data
::::::datasource,db,entities,offlinesupport,repo,rest:::::::

- datasource -> primary repo to communicate with local+network storage

- db -> database and query classes

- entities -> data models for api and database

- offline support -> Mediator class to sync between local storage and network storage (loading data to database where there is new data, providing data for list from local storage) 

- repo -> main Repo

- RestService -> provides api end point

## DI

::::::: Application Module, Context Module :::::::

- ApplicationModule provides dependencies which required throughout 

-  ContextModule provides context

# UI 

::::: CatList, CatVIew :::::

## CatDetail

- CatViewFullScreen and viewmodel is activity responsible for showing Cat image Full screen and zoom on pinch


## CatList

:::: adapter, view, viewmodel ::::

- adapter -> adapter for recycleview, viewholder for grid, network viewholder

- view -> catlist activity to show the recycle gridview

-> viewholder -> callback listener for onClick image and methods for fetching data for the list


# Utils

:::: APPUtils,Cons,GridDecoration,MyApplication:::::

-  AppUtils -> Supportive utilities as example getting screen width, number of columns

- Cons -> Constant variables needed throughout the app

- GridDecoration -> Supportive class to decorate each grid of the recycle list view

- MyApplication -> Applicatin Class, also providing appInstance

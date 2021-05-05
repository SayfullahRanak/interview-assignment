# Assignment - 7 Peaks Software (Cars)

To develop a  simple app that shows list of cars. [API] (https://www.apphusetreach.no/application/119267) 
Request
GET /article/get_articles_list


## Programming Language, Design Pattern

### KOTLIN,MVVM

## Dependencies 

### Hilt, Coroutine, Room , mockito, Junit, Espresso 

- Hilt is been used for providing di support throughout the Application

- Coroutine is been used to communicating between modules

- Room database is been used for caching data in local for offline support.

## Primary Overview
- Application consist of 1 activity "CarListActivity" where CarListActivity responsible for showing list of car from api.

- To implement Support offline-first , Room data base is been used where everytime after data loaded from api save, data is been saved to local database. Before saving the application clears the last chached data, as a result only the latest data is saved in local


# Package Contains (Main)

# data 
- models for api and database

# db 
- database and query classes

# repo 
- main Repo

# RestService 
- provides api end point

## DI

::::::: Application Module, Context Module :::::::

- ApplicationModule provides dependencies which required throughout 

-  ContextModule provides context

# UI 

::::: CarList, Splash :::::

## CarList
::::: Adapter, view, viewmodel :::::

# Adapter
- contains adapter for recyclelistview (CarListAdapter), and viewholder(CarViewHolder) to contain the view of each entities of the listview

- adapter -> adapter for recycleview, viewholder for grid, network viewholder

# view 
- carlistactivity to show the list in recyclelistview

- viewmodel -> viewmodel for api call and other data logic

# Utils

:::: cons,MyApplication,ProjectUtils:::::

- Cons -> Constant variables needed throughout the app

- MyApplication -> Applicatin Class, also providing appInstance

- ProjectUtils -> supportive tools for projects, ex : (timeFormatChange) formate date time according to requirement “20 December 2017, 21:43”, “09:43 PM” vs “21:43”.

# Package Contains (Test)

::::: testUtils, ui, utils ::::::

#testUtils
::::: LiveDataUtil, MockInterceptor, RestServiceTest, TestCons:::::::

- LiveDataUtil ->getOrAwaitValue observe live data from test class
- MockInterceptor -> MockInterceptor to mock the api call
- RestServiceTest -> Mock rest service and retrofit builder
- TestCons -> dummy contents.

#UI

- CarRemoteViewModelTest
##Test cases
- fetchCarList_populatinglist_when_api_response_success
- fetchCarList_empty_when_api_response_empty
- fetchCarList_Error_when_response_code_not_200
- fetchCarList_database_contains_old_list_when_no_internet
- fetchCarList_networks_when_no_network
...............................................

- CarListActivityTest
#TestCase
- activityVisible

#Utils
- ProjectUtilsTest
#TestCase
- whenFormatIsValid

# Flix
Flix is an app that allows users to browse movies from the [The Movie Database API](http://docs.themoviedb.apiary.io/#).

## Flix Part 1

### User Stories
A user story is a way to cpature requirements for an app from an end-user perspective. It is a common parctice in app development and helps to simplify the way requirements are specified. 

#### REQUIRED (10pts)
- [X] (10pts) User can view a list of movies (title, poster image, and overview) currently playing in theaters from the Movie Database API.

#### BONUS
- [X] (2pts) Views should be responsive for both landscape/portrait mode.
   - [X] (1pt) In portrait mode, the poster image, title, and movie overview is shown.
   - [X] (1pt) In landscape mode, the rotated alternate layout should use the backdrop image instead and show the title and movie overview to the right of it.

- [ ] (2pts) Display a nice default [placeholder graphic](https://guides.codepath.org/android/Displaying-Images-with-the-Glide-Library#advanced-usage) for each image during loading
- [X] (2pts) Improved the user interface by experimenting with styling and coloring.
- [ ] (2pts) For popular movies (i.e. a movie voted for more than 5 stars), the full backdrop image is displayed. Otherwise, a poster image, the movie title, and overview is listed. Use Heterogenous RecyclerViews and use different ViewHolder layout files for popular movies and less popular ones.

### App Walkthough GIF
## Portrait Mode
<img src="https://i.imgur.com/GcEifCW.gif" width=200><br>
## Landscape Mode
<img src="https://i.imgur.com/7d1jyfm.gif" width=200><br>

### Notes
- Struggled in implementing a transparent logo utilizing adaptive icons.

### Open-source libraries used

- [Android Async HTTP](https://github.com/codepath/CPAsyncHttpClient) - Simple asynchronous HTTP requests with JSON parsing
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Androids


[Database Movie API (RESTful)] (https://developers.themoviedb.org/3/movies/get-now-playing "Movie DB Now-Playing) 
End point we target: GET /movie/now_playing
Get a list of movies in theatres. This is a release type query that looks for all movies that have a release type of 2 or 3 within the specified date range. You can optionally specify a region parameter which will narrow the search to only look for theatrical release dates within the specified country.


Keywords:
API - Application Programming Interface, a collection of resources or tools used to build software applications.
GET Requests - Used to request data from a specified resource.

## Built With 
* [Android Studio Canary](https://developer.android.com/studio/preview) - Preview release for the latest features and improvements in Android Studio 11.

## Android Studio Projects
* All android studio projects are created in android studio canary using an [AMD Machine](https://github.com/ggiande/AMD-Chip-on-Android-Studio).

## Authors
* **Giancarlo Garcia**

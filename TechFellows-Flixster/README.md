# Flix
Flix is an app that allows users to browse movies from the [The Movie Database API](http://docs.themoviedb.apiary.io/#).

### User Stories
A user story is a way to capture requirements for an app from an end-user perspective. User stories are common practice in app development and help to simplify the way requirements are specified. 

## Flix Part 2

#### REQUIRED (10pts)

- [X] (8pts) Expose details of movie (ratings using RatingBar, popularity, and synopsis) in a separate activity.
- [X] (2pts) Allow video posts to be played in full-screen using the YouTubePlayerView.

#### BONUS

- [ ] Trailers for popular movies are played automatically when the movie is selected (1 point).
- [ ] When clicking on a popular movie (i.e. a movie voted for more than 5 stars) the video should be played immediately.
- [ ] Less popular videos rely on the detailed page should show an image preview that can initiate playing a YouTube video.
- [ ] Add a play icon overlay to popular movies to indicate that the movie can be played (1 point).
- [ ] Apply the popular ButterKnife annotation library to reduce view boilerplate. (1 point)
- [ ] Add a rounded corners for the images using the Glide transformations. (1 point)

### App Walkthough GIF
<img src="https://i.imgur.com/POelGIX.gif" width=250><br>

### Notes

Hiding API Keys from being posted to the online repository was a struggle.

## Open-source libraries used
- [Android Async HTTP](https://github.com/codepath/CPAsyncHttpClient) - Simple asynchronous HTTP requests with JSON parsing
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Android

## Flix Part 1

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
<img src="https://i.imgur.com/m7k9hZd.gif" width=200><br>
## Landscape Mode
<img src="https://i.imgur.com/NkCkCy7.gif" height=200><br>

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
* All android studio projects are created in android studio canary using an [AMD Machine](https://github.com/ggiande/Codepath-Android-Studio-Mobile-Development/tree/master/AMD-Chip-on-Android-Studio).

## Authors
* **Giancarlo Garcia**

# Instagram Client Demo

This is an Android demo application for fetching the popular photos from Instagram using the [Instagram API](http://instagram.com/developer).

Time spent: 12 hours spent in total

Completed user stories:

* [x] User can scroll through current popular photos from Instagram
* [x] For each photo displayed, user can see the following details: Graphic, Caption, Username
* [x] For each photo displayed, user can see the following details: (Optional) relative timestamp, like count, user profile image
* [x] Advanced: Add pull-to-refresh for popular stream with SwipeRefreshLayout
* [x] Advanced: Show latest comment for each photo (bonus: show last 2 comments)
* [x] Advanced: Display each photo with the same style and proportions as the real Instagram (see screens below) Note: I know my screens are not perfect, but hopefully they are good enough for me to check this one.
* [x] Advanced: Display each user profile image using a RoundedImageView
* [x] Advanced: Display a nice default placeholder graphic for each image during loading (read more about Picasso)
* [x] Advanced: Improve the user interface through styling and coloring

TODO:

* [x] Bonus: Allow user to view all comments for an image within a separate screen or a dialog fragment

Random Implementation Notes:

I leveraged pretty much all the tips during implementation. I wish I could have got these to work:

1. 16258 likes should be displayed as 16,258 likes. I ran out of time trying things between formatters and plurals.
2. Make the profile placeholder image rounded as well.
3. Make the top panel with the white background (profile picture, username and timestemp) snap on the top while scrolling (like the iOS Instagram app).
4. Trying to display a particular unicode character crashes the app: http://unicode-table.com/en/search/?q=clock This one works: http://unicode-table.com/en/23F0/ but this one doesn't: http://unicode-table.com/en/1F552/
5. Genymotion emulator runs out of memory but the stock emulator works fine. I'm not sure if it points to some problems in the app design or if it's just a Genymotion issue.


Walkthrough of all user stories:

![Video Walkthrough](InstagramClientDemo.gif)

GIF created with [LiceCap](http://www.cockos.com/licecap/).

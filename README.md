cs56-memory-card-game
=====================

This is a memory card game in which the player tries to match pairs of cards
based on their pictures.

The faster the player can match the cards, the higher their score will be.
Each time they make an incorrect match, they lose points.

The first level begins with a small grid, and each level that follows gets
increasing more difficult (the grid goes larger and larger).

At any point during the game, the player can click the reset button if they
would like to start over again.

At the end of the game, if the user has reached a new high score, they get
to enter their name into the leaderboard.



Instructions
============

Launch the game on terminal with
```
ant run
```

After launching the game, you can choose one of three options:

### 1. Start the game
Allows the user to begin playing.

### 2. Instructions
Gives the user detailed instructions on how to play the game.

### 3. High Score
Lets the user view the current leaderboard, with the names and scores of the best players.

Developer Notes
===============

### HighScoreBoard.java
Creates the highscore board and the information that goes on the board

### MemoryGameComponent.java
A Swing component for playing the Memory Card Game

### MemoryGameLevel.java
Has the constructor to fully initialize the level, such as target time, grid size, and delay time before two cards flip back over

### MemoryGrid.java
makes the grid (arraylist of MemoryCards) for the game

### MemoryCard.java
makes objects for cards

### MemoryGameGui.java
GUI for the game, and has the main class

### MemoryGameTest.java
tests the MemoryGame class

project history
===============
```
 N/A
```

Yun Suk Chang worked on this in S13.  There were other authors before him.
Xiaohe he and Shaoyi Zhang worked on this in W16.

Several others have worked on it since then.

# W16 Final Remarks:
We don't think this is a good project for students at any level of proficiency with Java.
The main reason is that this project is very well-built in terms of game logic and design.
For Java newbees, you won't be able to learn much about GUI, since the main structure is well-built.
For people who know Java from kindergartens, you might find this project boring.
We worried a lot about the "shrink" of points when we were working on this project.
Many issues we fixed are actually bugs instead of fundamental changes.
To fix a bug in a large project written by other undergraduate students is hard.
It might take hours to pinpoint a bug, but only write one line to fix the bug.

However, if you are brave and choose to work on this project:
We recomend you to start early and work on the issues created by Professor Conrad several years ago.
You will learn a lot from those issues. We didn't work on those good issues because we are distract by those "shrinking" issues.

# F16 Final Remarks:

We highly recommend that the next people who take on this project start by refactoring the code.
Although the code's structure isn't absolutely terrible, it could be improved drastically.
We think that it might be better if the overall structure of the project followed the
Model View Controller design pattern. This would make the project much easier to change in the future.

One of the largest problems with the current structure, if following MVC, is that MemoryGameGui.java is acting as
both the View and Controller. We believe it might be better if each menu display was separated into its own class, with
inheritance used as needed. We also believe it could be better if there was a Model for the current state of the game,
and a Model for all of the data that the game uses. And of course, a whole new Controller class must be created.

If there are a lot of classes that pertain to different parts of MVC, you can separate them into their own folders
(e.g. /Model and /View)

The timer is one of the more tricky things to work with in this project, and thus we believe that it should be made
into its own class entirely.

Refactoring would allow you to more easily fix issues #14, #15, and #16.


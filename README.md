## Horse Betting Simulation

# Team Members

Caleb Hatlevig, Gabe Guerrero, Wesley Stone

# Project Overview 

Our Horse Betting Simulation is Java-based and designed to combine interactive gameplay with a structered object-oriented program architecture. The goal of our project is to simulate a live horse race where a player can place bets, encounter obstacles and power-ups, and watch dynamically animated horses compete in real-time.

# Technical Guide

The program runs on Java 17/21. To main class is contained in the MainGame.java file. Run this file to execute the program.

# Acknowledgements

The horse PNG is from https://www.freeiconspng.com/img/22544

# Known Issues

There are a few limitations of the design that may hinder gameplay.
- There is currently no way to come back from $0. If you lose all your money, you lose and have to relaunch the program.
- If two horses finish at the same time, they cannot tie. Rather the program will designate the horse in the higher lane as the winner.

# Societal Impact

While a simple game, it does require some abilities by the user and does grapple with gambling.

The interface is entirely visual and requires mouse and keyboard inputs for use. People with conditions prohibiting these actions will be unable to use our program.

There are also people who struggle with gambling. This program does make light of gambling and does not in its design recognize those dangers.

# Race System

The racing logic is handled within the race package and includes multiple interconnected classes:

- Horse.Java
    Defines the horse objects in the race. Each horse has movement logic and properties that affect speed or race behavior

- Lane.java
    Represents each horse's racing lane and ensures spatial oranization during gameplay

- Obstacle.java and Powerup.java
    These classes include race modifiers. Obstacles make the horse slow down, while power-ups can temporarily increase speed or provide advantages. 

- Interactable.java
    Shared abstract class that Obstacle and Powerup use, ensuring consistent behavior for objects the horse can interact with during the race. 

- RaceManager.java
    Controls the flow of the race -- initilizing horses, updating movement, applying interactions, and determining the winner.  

- Utils.java
    Controls helper functions to manage repetive or generailzed tasks such as randomization, timing, or formatting. 

- MainGame.java
    The main class for running the simulation. It brings all components together and manages the game loop, rendering and overall control. 

# Gambling System

The gambling package adds all betting components, allowing users to place wagers before the race begins. 

- Gambler.java
    Functionally data storage for the user's bets, chosen horse, and money.

- GamblingManager.java
    Able to determine the odds of the horses winning and affect the gambler depending on provided outcomes.


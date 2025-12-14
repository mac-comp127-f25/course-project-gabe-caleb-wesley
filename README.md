# Project Title Goes Here

Info about your project goes here

# Team Members

Caleb Hatlevig, Gabe Guerrero, Wesley Stone

# Project Overview 

Our Horse Betting Simulation is Java-based and designed to combine interactive gameplay with a structered object-oriented program architecture. The goal of our project is to simulate a live horse race where a player can place bets, encounter obstacles and power-ups, and watch dynamically animated horses compete in real-time.

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
    The main class for running the simulation. It brings all components togetehr and manages the game loop, rendering and overall control. 

# Gambling System

The gambling package adds all betting components, allowing users to place wagers before the race begins. 

- Gambler.java

- GamblingInterface.java

# Gameflow Summary


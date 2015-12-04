package com.mitchjokelibrary;

import java.util.ArrayList;
import java.util.Random;


public class MitchJokeFactory {

    private MitchJokeFactory(){

    }

    static final ArrayList<String> jokeList = new ArrayList<String>(){{
            add("My fake plants died because I did not pretend to water them.");
    add("I was at this casino minding my own business, and this guy came up to me and said, \"You're gonna have to move, you're blocking a fire exit.\" As though if there was a fire, I wasn't gonna run. If you're flammible and have legs, you are never blocking a fire exit.");
    add("I don't have a girlfriend. But I do know a woman who'd be mad at me for saying that.");
    add("My friend asked me if I wanted a frozen banana. I said \"No, but I want a regular banana later, so... yeah.\"");
    add("Rice is great if you're really hungry and want to eat two thousand of something.");
    add("I had a stick of CareFree gum, but it didn't work. I felt pretty good while I was blowing that bubble, but as soon as the gum lost its flavor, I was back to pondering my mortality.");
    add("The depressing thing about tennis is that no matter how good I get, I'll never be as good as a wall.");
    add("When someone hands you a flyer, it's like they're saying \"here you throw this away.\"");
    add("I would imagine that if you could understand Morse code, a tap dancer would drive you crazy.");
    add("I can whistle with my fingers, especially if I have a whistle.");
    add("This shirt is dry clean only. Which means... it's dirty.");
    add("I'm gonna fix that last joke by taking out all the words and adding new ones.");
    add("I like Kit-Kats, unless I'm with four or more people.");
    add("I haven't slept for ten days, because that would be too long.");
    add("I'm against picketing, but I don't know how to show it.");
    add("I don't have any children, but if I had a baby, I would have to name it so I'd buy a baby naming book. Or I would invite somebody over who had a cast on.");
    add("When I was a boy, I laid in my twin-sized bed and wondered where my brother was.");
    add("A burrito is a sleeping bag for ground beef.");
    add("We're gonna have to sweeten some of these jokes. You know what sweeten means, right? That’s a showbiz term for \"add sugar to\".");
    add("I want to hang a map of the world in my house, and then I'm gonna put pins into all the locations that I've traveled to. But first I'm gonna have to travel to the top two corners of the map, so it won't fall down.");

}};

    public static String getJoke(){


        Random random = new Random();
        int randomInt = random.nextInt(20);

        return jokeList.get(randomInt);

    }
}

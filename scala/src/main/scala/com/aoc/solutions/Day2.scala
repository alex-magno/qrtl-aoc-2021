package com.aoc
package solutions

import com.aoc.utils.IO

object Day2:
    val inputPath = "../challenges/day2/data/input.txt"

    var depth = 0
    var horizontal = 0

    /**
     * Navigate receives a list of instructions updates
     * depth and horiontal position.
     * @param Input List with navigation instructions
     */
    def navigate(input: List[String]): Unit =
        val instructions = input.map{_.split(" ")}
        
        for (instruction <- instructions)
            instruction match
                case Array(direction, distance) =>
                    if (direction == "up") decreaseDepth(distance.toString)
                    else if (direction == "down") increaseDepth(distance.toString)
                    else if (direction == "forward") increaseHorizontal(distance.toString)
                case _ =>

    def increaseDepth(distance: String): Unit = depth += distance.toInt
    def decreaseDepth(distance: String): Unit = depth -= distance.toInt
    def increaseHorizontal(distance: String): Unit = horizontal += distance.toInt

    def run(): Unit =
        val instructions = IO.fileToList(inputPath)

        navigate(instructions)

        val finalPosition = depth * horizontal
        println(s"Day 2 challenge result: $finalPosition")

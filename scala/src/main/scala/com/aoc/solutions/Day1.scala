package com.aoc
package solutions

import com.aoc.utils.IO

object Day1:
    val inputPath = "../challenges/day1/data/input.txt"

    /**
     * Checks all measures to count number of times a measure increased.
     * @param Input List with all measures
     * @return Number of times a measure was higher than the previous one
    */
    def countIncreases(input: List[String]): Int =
        var increaseCounter = 0

        input.sliding(2).foreach {
            case List(a, b) =>
                if (b.toInt > a.toInt)
                    increaseCounter += 1
            case _ =>
        }
        increaseCounter

    def run(): Unit =
        val raw = IO.fileToList(inputPath)

        val result = countIncreases(raw)
        println(s"Day 1 challenge result: $result")

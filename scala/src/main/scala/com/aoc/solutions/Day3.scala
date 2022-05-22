package com.aoc
package solutions

import com.aoc.utils.IO

object Day3:
    val inputPath = "../challenges/day3/data/input.txt"  

    def run(): Unit =
        val diagnostic = IO.fileToList(inputPath)

        /**
         * Transforms each diagnostic colum in its own list, for every bit.
         * 
         * Example: ("0010", "0110", "1100") => 
             List(List(0, 0, 1), List(0, 1, 1), List(1, 1, 0), List(0, 0, 0))
        */
        val transposedDiagnostic = diagnostic.map{_.toList}.transpose

        // gammaRate is the most common element in every sub-List
        val gammaRate = transposedDiagnostic.map{bit => 
            bit.groupBy(identity).maxBy(_._2.size)._1
        }

        // epsilonRate is the inverse of gammaRate
        val epsilonRate = gammaRate.map{
            case x if x == '0' => '1' 
            case x if x == '1' => '0' 
            case _ =>
        }

        // result = gammaRate * epsilonRate
        val result = Integer.parseInt(gammaRate.mkString, 2) * Integer.parseInt(epsilonRate.mkString, 2)

        println(s"Day 3 challenge result: $result")

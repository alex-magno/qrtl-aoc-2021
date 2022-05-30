package com.aoc
package solutions

import com.aoc.utils.IO
import com.aoc.utils.Transformer

object Day5:
    val inputPath = "../challenges/day5/data/input.txt"

    def parseVentLines(raw: List[String]): List[(Array[Int], Array[Int])] =
        raw.map{_.split(" -> ")}.map{
            case Array(lower, upper) =>
                val lowerInt = lower.nn.split(',').map{x => Transformer.toInt(x)}
                val upperInt = upper.nn.split(',').map{x => Transformer.toInt(x)}
                (lowerInt, upperInt)
            case _ => throw new Exception("Invalid input")
        }
    
     // Only consider valid inputs & horizontal and vertical lines
    def filterVentLines(vent: List[(Array[Int], Array[Int])]): List[(Array[Int], Array[Int])] =
        vent.filter {
            case (lower: Array[Int], upper: Array[Int]) =>
            val isValidInput = !(lower.contains(-1) | upper.contains(-1))
            val isHorizontalOrVertical = (lower(0) == upper(0)) | (lower(1) == upper(1))
            isHorizontalOrVertical & isValidInput
        }
    
    def initialize2dArray(size: Int): Array[Array[Int]] =
        Array.fill(size + 1, size + 1)(0)
    
    def updateDiagram(diagram: Array[Array[Int]], lower: Array[Int], upper: Array[Int]): Unit =

        def getLowerBound(step: Int, index: Int, lower: Array[Int]): Int =
            if (index == 0) then step else lower(0)

        def getUpperBound(step: Int, index: Int, upper: Array[Int]): Int =
            if (index == 0) then upper(index + 1) else step

        for (i <- 0 to 1)
            if (lower(i) > upper(i))
                for (step <- upper(i) to lower(i))
                    val lowerBound = getLowerBound(step, i, lower)
                    val upperBound = getUpperBound(step, i, upper)
                    
                    diagram(lowerBound)(upperBound) += 1
            else if (lower(i) < upper(i))
                for (step <- lower(i) to upper(i))
                    val lowerBound = getLowerBound(step, i, lower)
                    val upperBound = getUpperBound(step, i, upper)
                    
                    diagram(lowerBound)(upperBound) += 1

    def run(): Unit =
        val ventLinesRaw = IO.fileToList(inputPath)
        
        val ventLinesParsed = parseVentLines(ventLinesRaw)
        val ventLinesFiltered = filterVentLines(ventLinesParsed)

        val maxNumber = ventLinesFiltered.flatMap(t => List(t._1, t._2)).flatMap(_.toList).max
        val diagram = initialize2dArray(maxNumber)

        ventLinesFiltered.foreach{
            case (lower: Array[Int], upper: Array[Int]) =>
                updateDiagram(diagram, lower, upper)
        }

        val result = diagram.flatten.count(_ > 1)

        println(s"Day 5 challenge result: $result")

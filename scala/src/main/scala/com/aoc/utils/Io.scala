package com.aoc
package utils

import scala.io.Source

object IO:
    /**
     * Reads text file as List of String
     * Closes the buffer after reading and returns object
    */
    def fileToList(path: String): List[String] =
        val bufferedSource = Source.fromFile(path)
        val element = bufferedSource.getLines.toList
        
        bufferedSource.close()

        element
    
    def fileToString(path: String): String =
        val bufferedSource = Source.fromFile(path)
        val element = bufferedSource.getLines.mkString("\n")
        
        bufferedSource.close()

        element

object Transformer:
    /**
     * String to Int with exception handling.
     * Returns -1 if the string is not a number.
    */
    def toInt(str: String): Int =
        try {str.toInt} catch {case _: NumberFormatException => -1}

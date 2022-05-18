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

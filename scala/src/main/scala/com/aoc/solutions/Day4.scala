package com.aoc
package solutions

import scala.collection.mutable
import util.control.Breaks.breakable, util.control.Breaks.break

import com.aoc.utils.IO

object Day4:
    val inputPath = "../challenges/day4/data/input.txt"
    type BoardRow = mutable.ArraySeq[(String, Boolean)]

    def getParsedBoard(rawBoards: Array[String]): Array[Array[BoardRow]] =
        val parsedBingoBoards = mutable.ArrayBuffer[Array[BoardRow]]()
        val tmpBingoBoard = mutable.ArrayBuffer[BoardRow]()

        rawBoards.tail.foreach{ x =>
            if x == "" 
            then
                if !tmpBingoBoard.isEmpty then
                    // commit and clear tmp board
                    parsedBingoBoards += tmpBingoBoard.toArray
                    tmpBingoBoard.clear()
            else
                // parse board line and appends markedStatus (default: false)
                val boardLine = x.split(' ').toList.map(_.trim).filter(_ != "")
                val boardLineWithStatus = mutable.ArraySeq(boardLine.map{ x => (x.toString, false)}*)
                
                // append parsed line to tmp board
                tmpBingoBoard += boardLineWithStatus
        }

        parsedBingoBoards.toArray
    
    def updateMarkedStatus(row: BoardRow, bingoNumber: String): Unit =
        row.mapInPlace {
            case (rowNumber, markedStatus) => 
                if rowNumber == bingoNumber 
                then (rowNumber, true) 
                else (rowNumber, markedStatus)
        }
    
    def isWinnerBoard(board: Array[BoardRow]): Boolean =
        var isWinner = false

        // need to cast into Array[Array[(String, Boolean)]] since transpose method
        // does not support mutable.ArraySeq
        val boardArray = board.map { _.toArray }
        
        // Check in board rows
        breakable {
            boardArray.foreach { row =>
                isWinner = row.forall { case (_, markedStatus) => {markedStatus == true}}
                if isWinner then break
            }
        }

        // Check in board columns if not winner yet
        if !isWinner then
            breakable {
                boardArray.transpose.foreach { col =>
                    isWinner = col.forall { case (_, markedStatus) => {markedStatus == true}}
                    if isWinner then break
                }
            }

        isWinner

    def calculateResult(winnerBoard: Array[BoardRow], lastNumber: Int): Int =
        val resultArray = winnerBoard.flatten.map{ 
            case (boardNumber, markedStatus) if markedStatus == false => boardNumber.toInt 
            case _ => 0
        } 

        resultArray.sum * lastNumber

    def run(): Unit =
        val bingoRaw = IO.fileToString(inputPath)

        var winnerBoard = Array[BoardRow]()
        var lastNumberCalled = 0

        val bingoArray = bingoRaw.split('\n')
        val bingoNumbers = bingoArray.head.split(',').toList

        val parsedBingoBoards = getParsedBoard(bingoArray)

        // Main logic to update markedStatus and check winner
        breakable {
            bingoNumbers.foreach { number =>
                parsedBingoBoards.foreach{ board =>
                    // For each board row, updates markedStatus for the given bingoNumber
                    board.foreach{ row => updateMarkedStatus(row, number) }
                    if isWinnerBoard(board) then
                        winnerBoard = board
                        lastNumberCalled = number.toInt
                        break
                }
            }
        }

        val result = calculateResult(winnerBoard, lastNumberCalled)
        println(s"Day 4 challenge result: $result")

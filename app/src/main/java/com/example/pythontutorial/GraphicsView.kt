package com.example.pythontutorial

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.CountDownTimer
import android.view.MotionEvent
import android.view.View
import kotlin.math.abs
import kotlin.random.Random

class GraphicsView(context: Context?) : View(context), View.OnTouchListener {
    val cell = 60f
    lateinit var pSnake: Paint
    lateinit var pFood: Paint
    lateinit var pText: Paint

    val snake = ArrayList<Pair<Int, Int>>()   // (col, row)
    var dirX = 1
    var dirY = 0
    var foodX = 5
    var foodY = 5
    var score = 0
    var gameOver = false
    var running = false
    lateinit var timer: CountDownTimer

    init {
        pSnake = Paint().apply { color = Color.rgb(0, 160, 0); style = Paint.Style.FILL }
        pFood = Paint().apply { color = Color.RED; style = Paint.Style.FILL }
        pText = Paint().apply {
            color = Color.BLACK
            textSize = 60f
            textAlign = Paint.Align.CENTER
        }
        resetGame()

        timer = object : CountDownTimer(Long.MAX_VALUE, 200L) {
            override fun onTick(millisUntilFinished: Long) {
                if (!gameOver) step()
                invalidate()
            }
            override fun onFinish() {}
        }
        setOnTouchListener(this)
    }

    fun cols() = (width / cell).toInt()
    fun rows() = (height / cell).toInt()

    fun resetGame() {
        snake.clear()
        snake.add(Pair(3, 3))
        snake.add(Pair(2, 3))
        snake.add(Pair(1, 3))
        dirX = 1; dirY = 0
        score = 0
        gameOver = false
        foodX = 5; foodY = 5
    }

    fun placeFood() {
        do {
            foodX = Random.nextInt(0, cols())
            foodY = Random.nextInt(0, rows())
        } while (snake.contains(Pair(foodX, foodY)))
    }

    fun step() {
        val head = snake[0]
        val newHead = Pair(head.first + dirX, head.second + dirY)

        // ชนขอบ หรือ ชนตัวเอง
        if (newHead.first < 0 || newHead.second < 0 ||
            newHead.first >= cols() || newHead.second >= rows() ||
            snake.contains(newHead)) {
            gameOver = true
            return
        }

        snake.add(0, newHead)
        if (newHead.first == foodX && newHead.second == foodY) {
            score++
            placeFood()          // กินแล้ว ไม่ตัดหาง
        } else {
            snake.removeAt(snake.size - 1)
        }
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        if (event!!.action == MotionEvent.ACTION_DOWN) {
            if (!running) {
                timer.start()
                running = true
            }
            if (gameOver) {
                resetGame()
                return true
            }
            // เลี้ยวตามตำแหน่งที่แตะเทียบกับหัวงู
            val hx = snake[0].first * cell + cell / 2
            val hy = snake[0].second * cell + cell / 2
            val dx = event.x - hx
            val dy = event.y - hy
            if (abs(dx) > abs(dy)) {
                if (dx > 0 && dirX != -1) { dirX = 1; dirY = 0 }
                else if (dx < 0 && dirX != 1) { dirX = -1; dirY = 0 }
            } else {
                if (dy > 0 && dirY != -1) { dirX = 0; dirY = 1 }
                else if (dy < 0 && dirY != 1) { dirX = 0; dirY = -1 }
            }
        }
        invalidate()
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawColor(Color.WHITE)

        // อาหาร
        canvas.drawCircle(foodX * cell + cell / 2, foodY * cell + cell / 2, cell / 2 - 5, pFood)

        // ตัวงู
        for (s in snake) {
            canvas.drawRect(s.first * cell, s.second * cell,
                s.first * cell + cell - 4, s.second * cell + cell - 4, pSnake)
        }

        canvas.drawText("Score: $score", width / 2f, 80f, pText)
        if (!running) canvas.drawText("Touch to start", width / 2f, height / 2f, pText)
        if (gameOver) {
            canvas.drawText("GAME OVER", width / 2f, height / 2f, pText)
            canvas.drawText("Touch to restart", width / 2f, height / 2f + 80f, pText)
        }
    }
}
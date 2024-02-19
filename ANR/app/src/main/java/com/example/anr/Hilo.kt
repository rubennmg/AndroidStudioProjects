package com.example.anr

class Hilo: Thread() {
    override fun run() {
        super.run()
        Thread.sleep(20000)
    }
}
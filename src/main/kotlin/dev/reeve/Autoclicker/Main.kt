package dev.reeve.Autoclicker

import org.jnativehook.GlobalScreen
import org.jnativehook.keyboard.NativeKeyEvent
import org.jnativehook.keyboard.NativeKeyListener
import java.awt.Robot
import java.awt.event.InputEvent
import java.lang.Thread.sleep
import java.util.logging.Level
import java.util.logging.Logger
import kotlin.math.max
import kotlin.math.min


fun main() {
	Main()
}

class Main : NativeKeyListener {
	init {
		val logger = Logger.getLogger(GlobalScreen::class.java.getPackage().name)
		logger.level = Level.WARNING
		logger.useParentHandlers = false

		GlobalScreen.registerNativeHook()
		ClickerThread()

		GlobalScreen.addNativeKeyListener(this)
		while (enabled) {
			sleep(1000)
		}
	}

	companion object {
		var enabled = false
		var running = true
	}

	override fun nativeKeyTyped(key: NativeKeyEvent?) {
	}

	override fun nativeKeyPressed(key: NativeKeyEvent?) {

	}

	override fun nativeKeyReleased(key: NativeKeyEvent?) {
		key?.let {
			if (key.keyCode == NativeKeyEvent.VC_B) {
				enabled = !enabled
			}
		}
	}
}

class ClickerThread {
	private val robot = Robot()
	private val random = java.util.Random()

	init {
		task()
	}

	private fun task() {
		Thread {
			while (Main.running) {
				random.setSeed(random.nextLong())
				if (Main.enabled) {
					robot.mousePress(InputEvent.BUTTON1_DOWN_MASK)
					robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK)
				}
				val min = 150
				val max = 200
				val div = 15
				val mean = 175



				val sleep = min(max.toDouble(), max(min.toDouble(), random.nextGaussian() * div + mean))
				sleep(Math.round(1000 / sleep))
			}
		}.start()
	}
}
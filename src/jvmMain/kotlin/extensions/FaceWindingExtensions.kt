package extensions

import graphics.FaceWinding
import org.lwjgl.opengl.GL46C.*


val FaceWinding.native: Int
    get() = when (this) {
        FaceWinding.CounterClockWise -> GL_CCW
        FaceWinding.ClockWise -> GL_CW
    }
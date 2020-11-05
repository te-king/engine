package extensions

import graphics.FaceWinding
import org.khronos.webgl.WebGL2RenderingContext.Companion.CCW
import org.khronos.webgl.WebGL2RenderingContext.Companion.CW


val FaceWinding.native: Int
    get() = when (this) {
        FaceWinding.CounterClockWise -> CCW
        FaceWinding.ClockWise -> CW
    }
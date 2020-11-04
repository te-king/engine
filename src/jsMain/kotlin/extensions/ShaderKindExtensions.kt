package extensions

import graphics.FragmentShader
import graphics.ShaderKind
import graphics.VertexShader
import org.khronos.webgl.WebGL2RenderingContext.Companion.FRAGMENT_SHADER
import org.khronos.webgl.WebGL2RenderingContext.Companion.VERTEX_SHADER


val ShaderKind.native
    get() = when (this) {
        VertexShader -> VERTEX_SHADER
        FragmentShader -> FRAGMENT_SHADER
    }
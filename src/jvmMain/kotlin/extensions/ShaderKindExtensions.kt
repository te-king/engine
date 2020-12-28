package extensions

import graphics.FragmentShader
import graphics.ShaderKind
import graphics.VertexShader

import org.lwjgl.opengl.GL46C.*


val ShaderKind.native
    get() = when (this) {
        VertexShader -> GL_VERTEX_SHADER
        FragmentShader -> GL_FRAGMENT_SHADER
    }

val ShaderKind.stage
    get() = when (this) {
        VertexShader -> GL_VERTEX_SHADER_BIT
        FragmentShader -> GL_FRAGMENT_SHADER_BIT
    }
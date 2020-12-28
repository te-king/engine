package world.controllers

import graphics.DrawCommandBuffer
import graphics.Material
import graphics.MeshBufferObject
import math.Color
import world.Scene

actual class StandardShader actual constructor(scene: Scene) : Controller(scene) {

    actual inner class Material : graphics.Material {

        actual var diffuseColor: Color
            get() = TODO("Not yet implemented")
            set(value) {}

        override fun draw(commandBuffer: DrawCommandBuffer, meshes: Sequence<MeshBufferObject>) {
            TODO("Not yet implemented")
        }

    }

}
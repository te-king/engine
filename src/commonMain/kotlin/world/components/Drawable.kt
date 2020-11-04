package world.components

import graphics.DrawCommandBuffer
import graphics.Material
import graphics.MeshBufferObject
import world.Node
import world.controllers.DeferredPipeline

class Drawable(node: Node) : Component(node), DeferredPipeline.Renderable {

    private val transform by component<Transform>()

    val pairs = mutableListOf<Pair<MeshBufferObject, Material?>>()


    override fun draw(commandBuffer: DrawCommandBuffer) {

        transform.worldMatrix
        commandBuffer.bindDataBuffer(2, transform.buffer)

        for ((material, pairs) in pairs.groupBy { it.second })
            material?.draw(commandBuffer, pairs.asSequence().map { it.first })

    }

}
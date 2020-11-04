package world.components

import extensions.sizeOf
import graphics.*
import math.*
import world.Node
import world.controllers.GraphicsContext
import kotlin.properties.Delegates


class Transform(node: Node) : Component(node) {

    private val graphicsContext by controller<GraphicsContext>()

    val buffer = graphicsContext.device.createBuffer(sizeOf(Float4x4::class, Float4x4::class), DataBuffer, DynamicBuffer) ?: error("Failed to create Transform buffer")


    private val childrenSet = mutableSetOf<Transform>()
    val children get() = childrenSet.asSequence()

    var parent: Transform? = null
        set(value) {
            field?.childrenSet?.remove(this)
            field = value
            field?.childrenSet?.add(this)
            invalidateLocal()
        }


    var translation by Delegates.observable(Float3.zero) { _, _, _ -> invalidateLocal() }
    var rotation by Delegates.observable(Quaternion.identity) { _, _, _ -> invalidateLocal() }
    var scale by Delegates.observable(Float3.one) { _, _, _ -> invalidateLocal() }

    val right get() = worldMatrix.matrix.column(0).xyz.normalized
    val left get() = -right

    val up get() = worldMatrix.matrix.column(1).xyz.normalized
    val down get() = -up

    val backwards get() = worldMatrix.matrix.column(2).xyz.normalized
    val forwards get() = -backwards


    // Local Matrix
    private var localInvalid = true

    var localMatrix = TransformationMatrix.identity
        get() {
            if (localInvalid) validateLocal()
            return field
        }
        private set(value) {
            buffer { writeData(0, value.matrix) }
            field = value
        }

    private fun invalidateLocal() {
        localInvalid = true
        invalidateWorld()
    }

    private fun validateLocal() {
        localMatrix = TransformationMatrix.translation(translation) * TransformationMatrix.rotation(rotation) * TransformationMatrix.scaling(scale)
        localInvalid = false
    }


    // World Matrix
    private var worldInvalid = true

    var worldMatrix = TransformationMatrix.identity
        get() {
            if (worldInvalid) validateWorld()
            return field
        }
        private set(value) {
            buffer { writeData(sizeOf(Float4x4::class), value.matrix) }
            field = value
        }

    private fun invalidateWorld() {
        worldInvalid = true
        for (it in childrenSet) it.invalidateWorld()
    }

    private fun validateWorld() {
        worldMatrix = (parent?.worldMatrix ?: TransformationMatrix.identity) * localMatrix
        worldInvalid = false
    }


    override fun toString() = "[ Transform translation: $translation, rotation: $rotation, scale: $scale ]"

}
package world.components

import extensions.*
import graphics.*
import math.*
import world.Node
import world.controllers.GraphicsContext
import kotlin.properties.Delegates


/**
 * Represents a 3d transform including translation, rotation, and scale.
 */
class Transform(node: Node) : Component(node) {

    private val graphicsContext by controller(::GraphicsContext)

    private val buffer = graphicsContext.device.createBuffer(sizeOf(Float4x4::class, Float4x4::class), DataKind, DynamicStorage) ?: error("Failed to create Transform buffer")


    /**
     * A sequence of all the transforms that have their parent set to this transform
     */
    val children get() = childrenSet.asSequence()

    private val childrenSet = mutableSetOf<Transform>()


    /**
     * The hierarchical parent of this transform if one exists, otherwise null. Setting this property also mutates *children*.
     */
    var parent: Transform? = null
        set(value) {
            field?.childrenSet?.remove(this)
            field = value
            field?.childrenSet?.add(this)
            invalidateLocal()
        }


    /**
     * The translation contained within the transform as a 3d position vector.
     * This value is used to generate a 4x4 matrix.
     */
    var translation by Delegates.observable(Float3.zero) { _, _, _ -> invalidateLocal() }

    /**
     * The rotation contained within the transform as a quaternion
     * This value is used to generate a 4x4 matrix.
     */
    var rotation by Delegates.observable(Quaternion.identity) { _, _, _ -> invalidateLocal() }

    /**
     * The scale contained within the transform as a 3d vector
     * This value is used to generate a 4x4 matrix.
     */
    var scale by Delegates.observable(Float3.one) { _, _, _ -> invalidateLocal() }


    /**
     * The right direction relative to this transform
     */
    val right get() = worldMatrix.matrix.column(0).xyz.normalized

    /**
     * The left direction relative to this transform
     */
    val left get() = -right

    /**
     * The up direction relative to this transform
     */
    val up get() = worldMatrix.matrix.column(1).xyz.normalized

    /**
     * The down direction relative to this transform
     */
    val down get() = -up

    /**
     * The backwards direction relative to this transform
     */
    val backwards get() = worldMatrix.matrix.column(2).xyz.normalized

    /**
     * The forwards direction relative to this transform
     */
    val forwards get() = -backwards


    /**
     * The matrix generated from translation, rotation, and scale stored in the transform.
     */
    var localMatrix = TransformationMatrix.identity
        get() {
            if (localInvalid) validateLocal()
            return field
        }
        private set(value) {
            buffer { writeData(0, value.matrix) }
            field = value
        }

    private var localInvalid = true

    private fun invalidateLocal() {
        localInvalid = true
        invalidateWorld()
    }

    private fun validateLocal() {
        localMatrix = TransformationMatrix.translation(translation) * TransformationMatrix.rotation(rotation) * TransformationMatrix.scaling(scale)
        localInvalid = false
    }


    /**
     * the matrix calculated with the local matrix after taking into account any parent transforms
     */
    var worldMatrix = TransformationMatrix.identity
        get() {
            if (worldInvalid) validateWorld()
            return field
        }
        private set(value) {
            buffer { writeData(sizeOf(Float4x4::class), value.matrix) }
            field = value
        }

    private var worldInvalid = true

    private fun invalidateWorld() {
        worldInvalid = true
        for (it in childrenSet) it.invalidateWorld()
    }

    private fun validateWorld() {
        worldMatrix = (parent?.worldMatrix ?: TransformationMatrix.identity) * localMatrix
        worldInvalid = false
    }


    /**
     * Attaches this transforms buffer to a given command buffer
     */
    fun attach(commandBuffer: DrawCommandBuffer) {
        worldMatrix
        commandBuffer.bindObjectTransformBuffer(buffer)
    }

}
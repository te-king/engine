@file:Suppress("UNUSED_PARAMETER", "NOTHING_TO_INLINE")

package extensions

import math.*
import kotlin.math.*
import kotlin.reflect.KClass


// Vector Functions
fun lerp(v0: Float2, v1: Float2, amount: Float) = v0 + (v1 - v0) * amount
fun lerp(v0: Float3, v1: Float3, amount: Float) = v0 + (v1 - v0) * amount
fun lerp(v0: Float4, v1: Float4, amount: Float) = v0 + (v1 - v0) * amount

fun distance(v0: Float2, v1: Float2) = (v1 - v0).length
fun distance(v0: Float3, v1: Float3) = (v1 - v0).length
fun distance(v0: Float4, v1: Float4) = (v1 - v0).length

fun dot(v0: Float2, v1: Float2) = v0.x * v1.x + v0.y * v1.y
fun dot(v0: Float3, v1: Float3) = v0.x * v1.x + v0.y * v1.y + v0.z * v1.z
fun dot(v0: Float4, v1: Float4) = v0.x * v1.x + v0.y * v1.y + v0.z * v1.z + v0.w * v1.w

fun cross(v0: Float3, v1: Float3) =
    Float3(v0.y * v1.z - v0.z * v1.y, v0.z * v1.x - v0.x * v1.z, v0.x * v1.y - v0.y * v1.x)


fun slerp(first: Quaternion, second: Quaternion, amount: Float): Quaternion {

    val d = dot(first.vector, second.vector).absoluteValue

    val scale0: Float
    val scale1: Float

    if (d < 0.9f) {
        val angle = acos(d)
        val invSinTheta = 1f / sin(angle)
        scale0 = sin((1f - amount) * angle) * if (d < 0f) -invSinTheta else invSinTheta
        scale1 = sin(amount * angle) * invSinTheta
    } else {
        scale0 = 1f - amount
        scale1 = amount
    }

    return Quaternion(
        scale0 * first.x + scale1 * second.x,
        scale0 * first.y + scale1 * second.y,
        scale0 * first.z + scale1 * second.z,
        scale0 * first.w + scale1 * second.w
    )
}

fun sizeOf(vararg types: KClass<*>): Long =
    types.map {
        when (it) {
            Byte::class -> Byte.SIZE_BYTES.toLong()
            Short::class -> Short.SIZE_BYTES.toLong()
            Int::class -> Int.SIZE_BYTES.toLong()
            Long::class -> Long.SIZE_BYTES.toLong()
            Int2::class -> sizeOf(Int::class) * 2
            Int3::class -> sizeOf(Int::class) * 3
            Int4::class -> sizeOf(Int::class) * 4
            Float::class -> Float.SIZE_BYTES.toLong()
            Double::class -> Double.SIZE_BYTES.toLong()
            Float2::class -> sizeOf(Float::class) * 2
            Float3::class -> sizeOf(Float::class) * 3
            Float4::class -> sizeOf(Float::class) * 4
            Float4x4::class -> sizeOf(Float::class) * 16
            Color::class -> sizeOf(Float4::class)
            Quaternion::class -> sizeOf(Float4::class)
            ProjectionMatrix::class -> sizeOf(Float4x4::class)
            TransformationMatrix::class -> sizeOf(Float4x4::class)
            else -> 0
        }
    }.sum()
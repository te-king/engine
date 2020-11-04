package graphics

import kotlin.reflect.KClass


enum class VertexKind(val type: KClass<*>) {
    Float(Float::class),
    Float2(Float2::class),
    Float3(Float3::class),
    Float4(Float4::class),
    Int(Int::class),
    Int2(Int2::class),
    Int3(Int3::class),
    Int4(Int4::class)
}
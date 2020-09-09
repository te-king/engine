package graphics

import extensions.sizeOf
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


abstract class GpuObject {

    private var totalSize = 0L


    protected fun int() =
        object: ReadWriteProperty<GpuObject, Int> {

            val offset = totalSize

            init {
                totalSize += sizeOf(Int::class)
            }

            override fun getValue(thisRef: GpuObject, property: KProperty<*>): Int {
                TODO("Not yet implemented")
            }

            override fun setValue(thisRef: GpuObject, property: KProperty<*>, value: Int) {
                TODO("Not yet implemented")
            }

        }

    protected fun float() =
        object: ReadWriteProperty<GpuObject, Float> {

            val offset = totalSize

            init {
                totalSize += sizeOf(Float::class)
            }

            override fun getValue(thisRef: GpuObject, property: KProperty<*>): Float {
                TODO("Not yet implemented")
            }

            override fun setValue(thisRef: GpuObject, property: KProperty<*>, value: Float) {
                TODO("Not yet implemented")
            }

        }

}
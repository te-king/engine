package graphics

import extensions.toFloat4Array


class Submesh(val mesh: Mesh, val index: Int) {

    val vertices get() = mesh.indiciesList[index].map { index -> mesh.verticesList.map { it[index] }.toFloat4Array() }


    operator fun get(index: Int) = Triangle(this, index)

}
package graphics


class Triangle(val submesh: Submesh, val index: Int) {

    val first get() = submesh.vertices.map { it.elementAt(index * 3) }

    val second get() = submesh.vertices.map { it.elementAt(index * 3 + 1) }

    val third get() = submesh.vertices.map { it.elementAt(index * 3 + 2) }

}
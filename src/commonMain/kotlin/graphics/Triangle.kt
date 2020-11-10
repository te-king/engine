package graphics


class Triangle(val submesh: Submesh, val index: Int) {

    val pointZero get() = submesh.vertices.map { it[index * 3] }

    val pointOne get() = submesh.vertices.map { it[index * 3 + 1] }

    val pointTwo get() = submesh.vertices.map { it[index * 3 + 2] }

}
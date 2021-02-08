package graphics


class Submesh(val mesh: Mesh, val index: Int) {

    val vertices get() = mesh.indicies.elementAt(index).map { index -> mesh.vertices.map { it.elementAt(index) } }


    operator fun get(index: Int) = Triangle(this, index)

}
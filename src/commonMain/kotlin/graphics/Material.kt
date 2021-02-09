package graphics


interface Material {

    fun draw(commandBuffer: DrawCommandBuffer, meshes: Sequence<MeshBufferObject>)

    fun draw(commandBuffer: DrawCommandBuffer, mesh: MeshBufferObject) = draw(commandBuffer, sequenceOf(mesh))

}
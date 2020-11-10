package world.controllers

import extensions.bindObjectMaterialBuffer
import extensions.sizeOf
import extensions.writeData
import graphics.*
import math.Color
import world.Scene


actual class StandardShader actual constructor(scene: Scene) : Controller(scene) {

    private val graphicsContext by controller(::GraphicsContext)
    private val vertexSource = """
        
        #version 300 es
        precision mediump float;
        
        /*********
         * Data blocks
         *********/
        layout(std140) uniform _cameraTransformBlock
        {
            mat4 localTransform;
            mat4 worldTransform;
        } cameraTransformBlock;
        
        layout(std140) uniform _cameraDataBlock
        {
            mat4 projection;
        } cameraDataBlock;
        
        layout(std140) uniform _objectTransformBlock
        {
            mat4 localTransform;
            mat4 worldTransform;
        } objectTransformBlock;
        
        layout(std140) uniform _objectMaterialBlock
        {
            vec4 diffuseColor;
        } objectMaterialBlock;
        
        /*********
         * Inputs and Outputs
         *********/
        layout(location = 0) in vec4 input_vertexPosition; 
        
        /*********
         * Logic
         *********/
        void main()
        {
            gl_Position = cameraDataBlock.projection * inverse(cameraTransformBlock.worldTransform) * objectTransformBlock.worldTransform * input_vertexPosition;
        }
        
    """.trimIndent().trim()
    private val vertexShader = graphicsContext.device.createShader(vertexSource, VertexShader) ?: error("failed to compile vertex stage for StandardShader")

    private val fragmentSource = """
        
        #version 300 es
        precision mediump float;
        
        /*********
         * Data blocks
         *********/
        layout(std140) uniform _cameraTransformBlock
        {
            mat4 localTransform;
            mat4 worldTransform;
        } cameraTransformBlock;
        
        layout(std140) uniform _cameraDataBlock
        {
            mat4 projection;
        } cameraDataBlock;
        
        layout(std140) uniform _objectTransformBlock
        {
            mat4 localTransform;
            mat4 worldTransform;
        } objectTransformBlock;
        
        layout(std140) uniform _objectMaterialBlock
        {
            vec4 diffuseColor;
        } objectMaterialBlock;
        
        /*********
         * Inputs and Outputs
         *********/
        out vec4 output_fragmentColor;    
        
        /*********
         * Logic
         *********/
        void main()
        {
            output_fragmentColor = objectMaterialBlock.diffuseColor;
        }
        
    """.trimIndent().trim()
    private val fragmentShader = graphicsContext.device.createShader(fragmentSource, FragmentShader) ?: error("failed to compile fragment stage for StandardShader")

    private val pipeline = graphicsContext.device.createPipeline(vertexShader, fragmentShader) ?: error("failed to link pipeline")


    actual inner class Material() : graphics.Material {

        constructor(diffuseColor: Color): this() {
            this.diffuseColor = diffuseColor
        }


        private val buffer = graphicsContext.device.createBuffer(sizeOf(Color::class), DataBuffer, DynamicBuffer) ?: error("failed to create material buffer")


        actual var diffuseColor = Color.white
            set(value) {
                buffer { writeData(0, value.rgba) }
                field = value
            }


        override fun draw(commandBuffer: DrawCommandBuffer, meshes: Sequence<MeshBufferObject>) {

            commandBuffer.bindPipeline(pipeline)
            commandBuffer.bindObjectMaterialBuffer(buffer)

            for (mesh in meshes) {

                for ((index, buffer) in mesh.vertexBuffers)
                    commandBuffer.bindVertexBuffer(index, buffer.buffer, buffer.type, buffer.offset, buffer.stride)

                for (indexBuffer in mesh.indexBuffers) {
                    commandBuffer.bindIndexBuffer(indexBuffer.buffer)
                    commandBuffer.drawIndexedPrimitives(indexBuffer.primitiveType, indexBuffer.indexCount)
                }

                for ((index, buffer) in mesh.vertexBuffers)
                    commandBuffer.bindVertexBuffer(index, null, buffer.type, buffer.offset, buffer.stride)

            }

        }

    }

}
package world.controllers

import extensions.sizeOf
import graphics.*
import math.Color
import world.Scene


actual class StandardShader actual constructor(scene: Scene) : Controller(scene) {

    private val graphicsContext by controller<GraphicsContext>()
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
        
        //layout(std140) uniform _objectMaterialBlock
        //{
        //
        //} objectMaterialBlock;
        
        /*********
         * Inputs and Outputs
         *********/
        layout(location = 0) in vec4 input_vertexPosition; 
        
        /*********
         * Logic
         *********/
        void main()
        {
            gl_Position = inverse(cameraTransformBlock.worldTransform) * objectTransformBlock.worldTransform * input_vertexPosition;
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
        
        //layout(std140) uniform _objectMaterialBlock
        //{
        //
        //} objectMaterialBlock;
        
        /*********
         * Inputs and Outputs
         *********/
        out vec4 output_fragmentColor;    
        
        /*********
         * Logic
         *********/
        void main()
        {
            output_fragmentColor = vec4(1, 0, 1, 1);
        }
        
    """.trimIndent().trim()
    private val fragmentShader = graphicsContext.device.createShader(fragmentSource, FragmentShader) ?: error("failed to compile fragment stage for StandardShader")

    private val pipeline = graphicsContext.device.createPipeline(vertexShader, fragmentShader) ?: error("failed to link pipeline")


    actual inner class Material : graphics.Material {

        private val buffer = graphicsContext.device.createBuffer(sizeOf(Color::class, Long::class, Long::class), DataBuffer, DynamicBuffer) ?: error("failed to create material buffer")


        actual var diffuseColor = Color.black
            set(value) {
                buffer { writeData(0, value.argb) }
                field = value
            }


        override fun draw(commandBuffer: DrawCommandBuffer, meshes: Sequence<MeshBufferObject>) {

            commandBuffer.bindDataBuffer(3, buffer)
            commandBuffer.bindPipeline(pipeline)

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
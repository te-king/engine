package extensions

import world.Node
import world.Scene
import world.components.*
import world.controllers.*
import kotlin.reflect.KClass


val componentMap = mapOf<KClass<out Component>, (Node) -> Component>(
    Body::class to ::Body,
    Camera::class to ::Camera,
    CameraControls::class to ::CameraControls,
    ContactEffect::class to ::ContactEffect,
    Drawable::class to ::Drawable,
    Transform::class to ::Transform
)

val controllerMap = mapOf<KClass<out Controller>, (Scene) -> Controller>(
    GraphicsContext::class to ::GraphicsContext,
    Input::class to ::Input,
    Physics::class to ::Physics,
    Renderer::class to ::Renderer,
    StandardShader::class to ::StandardShader
)


fun <T : Component> KClass<T>.createInstance(node: Node): T = componentMap[this]?.invoke(node) as T? ?: error("$this is not registered")

fun <T : Controller> KClass<T>.createInstance(scene: Scene): T = controllerMap[this]?.invoke(scene) as T? ?: error("$this is not registered")
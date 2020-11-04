package extensions

import world.Node
import world.Scene
import world.components.Camera
import world.components.Component
import world.components.Drawable
import world.components.Transform
import world.controllers.Controller
import world.controllers.DeferredPipeline
import world.controllers.GraphicsContext
import world.controllers.StandardShader
import kotlin.reflect.KClass


val componentMap = mutableMapOf<KClass<out Component>, (Node) -> Component>(
    Camera::class to { Camera(it) },
    Drawable::class to { Drawable(it) },
    Transform::class to { Transform(it) }
)

val controllerMap = mutableMapOf<KClass<out Controller>, (Scene) -> Controller>(
    DeferredPipeline::class to { DeferredPipeline(it) },
    GraphicsContext::class to { GraphicsContext(it) },
    StandardShader::class to { StandardShader(it) }
)


fun <T : Component> KClass<T>.createInstance(node: Node): T = componentMap[this]?.invoke(node) as T? ?: error("$this is not registered")

fun <T : Controller> KClass<T>.createInstance(scene: Scene): T = controllerMap[this]?.invoke(scene) as T? ?: error("$this is not registered")
package extensions

import world.Node
import world.Scene
import world.components.*
import world.controllers.*
import kotlin.reflect.KClass


val componentMap = mutableMapOf<KClass<out Component>, (Node) -> Component>(
    Camera::class to { Camera(it) },
    CameraControls::class to { CameraControls(it) },
    Drawable::class to { Drawable(it) },
    Transform::class to { Transform(it) }
)

val controllerMap = mutableMapOf<KClass<out Controller>, (Scene) -> Controller>(
    GraphicsContext::class to { GraphicsContext(it) },
    Input::class to { Input(it) },
    Renderer::class to { Renderer(it) },
    StandardShader::class to { StandardShader(it) }
)


fun <T : Component> KClass<T>.createInstance(node: Node): T = componentMap[this]?.invoke(node) as T? ?: error("$this is not registered")

fun <T : Controller> KClass<T>.createInstance(scene: Scene): T = controllerMap[this]?.invoke(scene) as T? ?: error("$this is not registered")
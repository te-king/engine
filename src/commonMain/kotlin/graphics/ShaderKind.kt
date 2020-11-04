package graphics


sealed class ShaderKind
object VertexShader : ShaderKind()
object FragmentShader : ShaderKind()
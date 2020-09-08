package world


class Scene {

    private val nodeList = mutableListOf<Node>()


    fun node(builder: Node.() -> Unit) =
        object : Node() {

            init {
                nodeList.add(this)
            }

            override val scene
                get() = this@Scene

            override fun delete() {
                nodeList.remove(this)
            }

        }

}



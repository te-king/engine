package world.controllers

import math.Color
import world.Scene


expect class StandardShader(scene: Scene) : Controller {

    inner class Material : graphics.Material {

        var diffuseColor: Color

    }

}
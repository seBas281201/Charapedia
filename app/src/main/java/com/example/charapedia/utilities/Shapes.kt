package com.example.charapedia.utilities

import androidx.compose.foundation.shape.GenericShape

val curvedBottomShape: GenericShape = GenericShape { size, _ ->

    moveTo(0f, 0f)
    lineTo(0f, size.height * 0.75f)

    quadraticTo(
        size.width / 2f,
        size.height,
        size.width,
        size.height * 0.70f
    )

    lineTo(size.width, 0f)
    close()
}
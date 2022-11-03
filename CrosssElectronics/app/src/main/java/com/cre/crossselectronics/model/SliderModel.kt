package com.cre.crossselectronics.model

data class SliderModel(
    var sliderId:String,
    var sliderImage:Int,
) {
    constructor():this(
        "",
        0
    )
}

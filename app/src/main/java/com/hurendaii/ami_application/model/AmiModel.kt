package com.hurendaii.ami_application.model

data class AmiModel(
    val name: String,
    val hunger: Int = 5,
    val sleep: Int = 5,
    val boredom: Int = 5
)

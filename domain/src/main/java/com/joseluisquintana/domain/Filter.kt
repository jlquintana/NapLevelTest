package com.joseluisquintana.domain

data class Filter(
        val male: Boolean,
        val female: Boolean,
        val developer: Boolean,
        val metalworker: Boolean,
        val brewer: Boolean,
        val gemcutter: Boolean,
        val medic: Boolean
)

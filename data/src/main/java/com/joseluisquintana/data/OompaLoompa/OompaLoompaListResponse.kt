package com.joseluisquintana.data.OompaLoompa

data class OompaLoompaListResponse (
    val current: Int,
    val total: Int,
    val results: List<OompaLoompa>
)

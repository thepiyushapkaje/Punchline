package com.example.thepunchline.data

data class Jokes(
    val category: String,
    val delivery: String,
    val error: Boolean,
    val flags: Flags,
    val id: Int,
    val joke: String,
    val lang: String,
    val safe: Boolean,
    val setup: String,
    val type: String
)
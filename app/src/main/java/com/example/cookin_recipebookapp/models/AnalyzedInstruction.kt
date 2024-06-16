package com.example.cookin_recipebookapp.models

data class AnalyzedInstruction(
    val name: String,
    val steps: List<Step>
)
package com.wipro.excercise.data

data class Response(val title: String, val rows: List<DataItem>)

data class DataItem(val title: String, val description: String, val imageHref: String)
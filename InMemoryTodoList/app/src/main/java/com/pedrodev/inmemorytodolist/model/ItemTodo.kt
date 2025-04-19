package com.pedrodev.inmemorytodolist.model

import java.time.LocalDate

class ItemTodo(
    var id: Int,
    var title: String,
    var text: String,
    var createdAt: LocalDate,
    var isDone: Boolean
)
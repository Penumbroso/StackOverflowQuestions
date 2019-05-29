package io.schiar.tcc.model

/**
 * Representa uma questão no StackOverflow.
 * @property creation_date data de criação do post em milissegundos desde 01/01/1970.
 * @property question_id id da questão.
 * @property title titulo do post.
 */
data class Question(
    val creation_date: Long,
    val question_id: Int,
    val title: String
)
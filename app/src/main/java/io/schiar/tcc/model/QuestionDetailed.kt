package io.schiar.tcc.model

/**
 * Representa uma questão em detalhes do StackOverflow.
 * @property owner: o usuario que criou a pergunta.
 * @property last_activity_date: a data da ultima atividade neste post.
 * @property creation_date data de criação do post em milissegundos desde 01/01/1970.
 * @property post_id: id da questão.
 * @property title titulo do post.
 * @property link: link para o post no StackOverflow.
 * @property body: o post em HTML.
 */
data class QuestionDetailed(
    val owner: Owner,
    val last_activity_date: Int,
    val creation_date: Long,
    val post_id : Int,
    val title: String,
    val link : String,
    val body: String

)
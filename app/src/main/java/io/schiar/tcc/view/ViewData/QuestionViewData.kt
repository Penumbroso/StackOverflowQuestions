package io.schiar.tcc.view.ViewData

/**
 * Representa uma questão na lista de questões mostrada ao usuario.
 * @property title titulo de um Post do StackOverflow.
 * @property creation_date data em que o Post foi criado.
 */
data class QuestionViewData(
        val title: String,
        val creation_date: String
)
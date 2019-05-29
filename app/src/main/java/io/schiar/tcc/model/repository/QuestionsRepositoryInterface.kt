package io.schiar.tcc.model.repository

import io.schiar.tcc.model.Question
import io.schiar.tcc.model.QuestionDetailed

interface QuestionsRepositoryInterface {
    /**
     * Busca a lista de componentes arquiteturais a ser exibida na View.
     * @param callback usado para receber a lista de componentes arquiteturais buscada.
     */
    fun fetchList(callback: (List<Question>) -> Unit )
    fun fetchDetails(id: Int, callback: (QuestionDetailed) -> Unit )
}
package io.schiar.tcc.viewmodel

import androidx.core.text.HtmlCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.schiar.tcc.model.Question
import io.schiar.tcc.model.QuestionDetailed
import io.schiar.tcc.model.repository.QuestionsRepository
import io.schiar.tcc.model.repository.QuestionsRepositoryInterface
import io.schiar.tcc.utilities.BitmapLoaderFactory
import io.schiar.tcc.utilities.BitmapLoaderFactoryInterface
import io.schiar.tcc.utilities.DateFormatter
import io.schiar.tcc.view.ViewData.QuestionViewData
import io.schiar.tcc.view.ViewData.QuestionDetailedViewData

/**
 * Recebe mensagens da visão solicitando dados da questões.
 * Formata esses dados e os disponibiliza para a visão através dos objetos LiveData.
 * @property questionRepository fornecedor de objetos de modelo para o ViewModel
 * @property currentQuestions lista de questões.
 * @property questions lista de questões simples com titulo e data de criação. Utiliza-se o wrapper LiveData para as mudanças na lista serem
 * atualizadas pela View.
 * @property selectedQuestion detalhes de um questão selecionada. Utiliza-se o wrapper LiveData para as mudanças no carro selecionado
 * serem atualizadas pela View.
 */
class QuestionViewModel(private val questionRepository: QuestionsRepositoryInterface = QuestionsRepository.instance,
                        private val bitmapLoaderFactory: BitmapLoaderFactoryInterface = BitmapLoaderFactory) : ViewModel() {
    private var currentQuestions: List<Question> = emptyList()

    val questions: MutableLiveData<List<QuestionViewData>> by lazy {
        MutableLiveData<List<QuestionViewData>>()
    }

    val selectedQuestion: MutableLiveData<QuestionDetailedViewData> by lazy {
        MutableLiveData<QuestionDetailedViewData>()
    }

    /**
     * Busca as questões e atualiza o LiveData de [questions].
     */
    fun fetch() {
        questionRepository.fetchList { questions ->
            this.currentQuestions = questions
            val questionPreviews = questions.map { question ->
                val date = DateFormatter().dateString(question.creation_date)
                return@map QuestionViewData(question.title, date)
            }
            this.questions.postValue(questionPreviews)
        }
    }

    /**
     * Busca detalhes de uma questão e atualiza o LiveData [selectedQuestion].
     * @param index índice de uma questão na lista [currentQuestions].
     */
    fun fetch(index: Int) {
        val selectedQuestion = currentQuestions[index]
        questionRepository.fetchDetails(selectedQuestion.question_id, ::updateSelectedQuestion)
    }

    /**
     * Constrói o objeto a ter seus atributos exibidos na View a partir do objeto de modelo, e atualiza o LiveData de
     * [selectedQuestion].
     * @param question o objeto de modelo de uma questão detalhada.
     */
    private fun updateSelectedQuestion(question: QuestionDetailed) {
        val body = HtmlCompat.fromHtml(question.body, HtmlCompat.FROM_HTML_MODE_LEGACY)
        val date = DateFormatter().dateString(question.creation_date)

        val questionDetailedViewData = QuestionDetailedViewData(
                question.title,
                question.owner.display_name,
                bitmapLoaderFactory.bitmapLoader(question.owner.profile_image),
                question.link,
                body.toString(),
                date
        )
        this.selectedQuestion.postValue(questionDetailedViewData)
    }
}
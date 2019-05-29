package io.schiar.tcc.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import io.schiar.tcc.R
import io.schiar.tcc.view.ViewData.QuestionViewData
import io.schiar.tcc.viewmodel.QuestionViewModel
import kotlinx.android.synthetic.main.fragment_questions.view.*

/**
 * Fragmento que mostra uma lista de 15 questões do StackOverflow.
 * @property viewModel ViewModel necessário para mostrar os dados necessários do modelo na View.
 */
class QuestionsFragment : Fragment(), OnClickPreviewListener {

    private lateinit var viewModel: QuestionViewModel

    /**
     * Carrega do XML a View que representa o fragmento.
     * @param inflater usado para carregar o XML do fragmento.
     * @param container o componente pai do fragmento.
     * @param savedInstanceState dados do estado anterior do fragmento.
     * @return view correspondente ao fragmento.
     */
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_questions, container, false)
    }

    /**
     * É carregado o [QuestionViewModel] para passar ao databinding do XML, assim o XML tem acesso aos atributos e métodos.
     * do ViewModel. Busca-se a lista de carros do ViewModel.
     * @param savedInstanceState dados do estado anterior do fragmento.
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val questionViewModelClass = QuestionViewModel::class.java
        viewModel = ViewModelProviders.of(requireActivity()).get(questionViewModelClass)
        viewModel.questions.observe(this, Observer { onPreviewsListChange(it) })
        viewModel.fetch()
    }

    /**
     * Observador das mudanças da lista de questões, definição do Adapter responsável para configuração da lista.
     * @param viewData lista simples de questões.
     */
    private fun onPreviewsListChange(viewData: List<QuestionViewData>) {
        val view = view ?: return
        view.questions_list.adapter = PreviewQuestionAdapter(viewData, requireContext(), this)
    }

    /**
     * Ao clicar em uma questão, navegue até o framento de detalhes.
     * @param index índice do questão.
     * @param view view que representa um item da lista de questões.
     */
    override fun onPreviewClick(index: Int, view: View) {
        viewModel.fetch(index)
        val navId = R.id.fragment_questions_to_fragment_question_detail
        Navigation.findNavController(view).navigate(navId)
    }

}

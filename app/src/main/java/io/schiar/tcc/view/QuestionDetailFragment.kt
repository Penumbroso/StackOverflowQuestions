package io.schiar.tcc.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import io.schiar.tcc.R
import io.schiar.tcc.databinding.FragmentQuestionDetailBinding
import io.schiar.tcc.viewmodel.QuestionViewModel

/**
 * Fragmento que mostra as questões detalhadas.
 * @property viewModel ViewModel necessário para mostrar os dados necessários do modelo na View
 */
class QuestionDetailFragment : Fragment() {

    private lateinit var viewModel: QuestionViewModel

    /**
     * Carrega [QuestionViewModel] para passar ao databinding do XML, assim o XML tem acesso aos
     * atributos e métodos do ViewModel.
     * @param inflater usado para carregar o XML do fragmento.
     * @param container o componente pai do fragmento.
     * @param savedInstanceState dados do estado anterior do fragmento.
     * @return view correspondente ao fragmento.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val questionViewClass = QuestionViewModel::class.java
        viewModel = ViewModelProviders.of(requireActivity()).get(questionViewClass)
        val binding = FragmentQuestionDetailBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@QuestionDetailFragment
            viewModel = this@QuestionDetailFragment.viewModel
            executePendingBindings()
        }
        return binding.root
    }

    /**
     * Avisa a atividade que há opções de menu na barra superior.
     *  @param savedInstanceState dados do estado anterior do fragmento.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    /**
     * Carrega o componente de menu que irá ser mostrado na barra superior.
     * @param menu componente de menu.
     * @param inflater carregador do XML.
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
    }

    /**
     * Ao clicar na opção de menu "go to site" ele abre o navegador padrão do usuario e a pagina equivalenda a questão.
     * @param item o item de menu selecionado.
     * @return true se foi selecionado com sucesso, false caso contrário.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return if (id == R.id.link) {
            val url = viewModel.selectedQuestion.value?.link;
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse(url)
            startActivity(openURL)
            true
        } else false
    }
}

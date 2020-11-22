package ua.turskyi.gdgfinder.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import ua.turskyi.gdgfinder.R
import ua.turskyi.gdgfinder.databinding.AddGdgFragmentBinding

class AddGdgFragment : Fragment() {

    private val viewModel: AddGdgViewModel by lazy {
        ViewModelProvider(this).get(AddGdgViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = AddGdgFragmentBinding.inflate(inflater)

        /* Allows Data Binding to Observe LiveData with the lifecycle of this Fragment */
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        viewModel.showSnackBarEvent.observe(viewLifecycleOwner, {
            if (it == true) { // Observed state is true.
                Snackbar.make(
                    binding.root,
                    getString(R.string.application_submitted),
                    Snackbar.LENGTH_SHORT
                ).show()
                viewModel.doneShowingSnackbar()

                binding.button.contentDescription = getString(R.string.submitted)
                binding.button.text = getString(R.string.done)
            }
        })

        setHasOptionsMenu(true)
        return binding.root
    }

}

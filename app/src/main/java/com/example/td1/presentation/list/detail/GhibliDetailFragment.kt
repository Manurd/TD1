package com.example.td1.presentation.list.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.td1.R
import com.example.td1.presentation.list.Ghibli
import com.example.td1.presentation.list.GhibliAdapter
import com.example.td1.presentation.list.api.GhibliApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class GhibliDetailFragment : Fragment() {

    private lateinit var textViewName: TextView




    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ghibli_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewName = view.findViewById(R.id.ghibli_detail_name)
       /* textViewName1 = view.findViewById(R.id.ghibli_detail_name1)
        textViewName2 = view.findViewById(R.id.ghibli_detail_name2)
        textViewName3 = view.findViewById(R.id.ghibli_detail_name3)
        textViewName4 = view.findViewById(R.id.ghibli_detail_name4)
        textViewName5 = view.findViewById(R.id.ghibli_detail_name5)*/

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.navigateToGhibliListFragment)
        }

        val id = arguments?.getInt("ghibliId") ?:-1

        val retrofit =  Retrofit.Builder()
                .baseUrl("https://ghibliapi.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        val ghibliApi: GhibliApi = retrofit.create(GhibliApi::class.java)

        ghibliApi.getGhibliList().enqueue(object: Callback<List<Ghibli>> {

            override fun onResponse(call: Call<List<Ghibli>>, response: Response<List<Ghibli>>){
                if (response.isSuccessful && response.body() != null){

                    //val ghiblipresponse :  List<Ghibli> = response.body()!!
                   // adapter.updateList(ghiblipresponse)

                    textViewName.text = response.body()!![id].title//toString()//title
                   /* textViewName1.text = response.body()!![id].original_title
                    textViewName2.text = response.body()!![id].description
                    textViewName3.text = response.body()!![id].director
                    textViewName4.text = response.body()!![id].producer
                    textViewName5.text = response.body()!![id].release_date*/

                   // val ghibli  = dataSet[position]
                   // viewHolder.textView.text = ghibli.original_title
                }

            }

            override fun onFailure(call: Call<List<Ghibli>>, t: Throwable) {
                //TODO("Not yet implemented")
            }


        })




    }
}
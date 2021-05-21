package com.example.td1.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.td1.R
import com.example.td1.presentation.list.api.GhibliApi
import com.example.td1.presentation.list.GhibliApplication
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class GhibliListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private val adapter = GhibliAdapter(listOf(), ::onClickedGhibli)



    private val layoutManager = LinearLayoutManager(context)
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ghibli_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.ghibli_recyclerview)
        recyclerView.layoutManager = this@GhibliListFragment.layoutManager
        recyclerView.adapter = this@GhibliListFragment.adapter




        //Partie cache////////////////////////////////////////////////////////////////////////////////
        var cache: Cache = Cache(File(GhibliApplication.context?.cacheDir, "responses"), 10 * 1024 * 1024)// 10 MiB

        val okhttpClient: OkHttpClient = OkHttpClient().newBuilder()
                .cache(cache)
                .build()
        // /////////////////////////////////////////////////////////////////////////////////////
         val retrofit =  Retrofit.Builder()
                .baseUrl("https://ghibliapi.herokuapp.com/")
                 .addConverterFactory(GsonConverterFactory.create())
                 .client(okhttpClient)
                .build();

        val ghibliApi: GhibliApi = retrofit.create(GhibliApi::class.java)

        ghibliApi.getGhibliList().enqueue(object: Callback<List<Ghibli>>{

            override fun onResponse(call: Call<List<Ghibli>>, response: Response<List<Ghibli>>){
                if (response.isSuccessful && response.body() != null){
                   val ghiblipresponse :  List<Ghibli> = response.body()!!
                    adapter.updateList(ghiblipresponse)
                }

            }

            override fun onFailure(call: Call<List<Ghibli>>, t: Throwable) {
                //TODO("Not yet implemented")
            }


        })








   // adapter.updateList(ghibliList)
    }

    private fun onClickedGhibli(id: Int) {
        findNavController().navigate(R.id.navigateToGhibliDetailFragment, bundleOf(
                "ghibliId" to id
        ))
    }
}

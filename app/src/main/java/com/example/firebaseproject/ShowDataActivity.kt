package com.example.firebaseproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseproject.Adaptar.ShowAdaptar
import com.example.firebaseproject.DataModel.ItemData
import com.example.firebaseproject.databinding.ActivityShowDataBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
class ShowDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShowDataBinding
    private lateinit var adapter: ShowAdaptar
    private val newsList = mutableListOf<ItemData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        adapter = ShowAdaptar(newsList)
        recyclerView.adapter = adapter

        // Call function to populate data
        showData()
    }

    private fun showData() {
        val databaseReference = FirebaseDatabase.getInstance().getReference("news_app")

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                newsList.clear()
                for (dataSnapshot in snapshot.children) {
                    val newsItem = dataSnapshot.getValue(ItemData::class.java)
                    newsItem?.let {
                        newsList.add(it)
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }
}

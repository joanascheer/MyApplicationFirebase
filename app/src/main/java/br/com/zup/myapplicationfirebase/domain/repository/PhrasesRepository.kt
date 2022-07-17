package br.com.zup.myapplicationfirebase.domain.repository

import br.com.zup.myapplicationfirebase.utils.FRASE_PATH
import br.com.zup.myapplicationfirebase.utils.MY_APP_PATH
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.ktx.Firebase

class PhrasesRepository {
    private val authentication: FirebaseAuth = Firebase.auth
    private val database = FirebaseDatabase.getInstance()
    private val reference = database.getReference("$MY_APP_PATH/${authentication.currentUser?.uid}/$FRASE_PATH")

    fun databaseReference() = reference

    fun getPhrasesList(): Query {
        return reference.orderByValue()
    }
}
package br.com.zup.myapplicationfirebase.domain.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthRepository {
    private val auth: FirebaseAuth = Firebase.auth

    fun loginUser(email: String, password: String): Task<AuthResult> {
        return auth.signInWithEmailAndPassword(email, password)
    }

    fun logout(){
        auth.signOut()
    }

    fun registerUser(email: String, password: String): Task<AuthResult> {
        return auth.createUserWithEmailAndPassword(email, password)
    }

    fun updateUserName(name: String): Task<Void>? {
        val profile = UserProfileChangeRequest.Builder()
            .setDisplayName(name).build()
        return auth.currentUser?.updateProfile(profile)
    }

    fun getCurrentUser() = auth.currentUser

    fun getName(): String? = auth.currentUser?.displayName

    fun getEmail(): String? = auth.currentUser?.email
}
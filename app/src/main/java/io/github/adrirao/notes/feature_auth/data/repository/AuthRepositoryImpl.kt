package io.github.adrirao.notes.feature_auth.data.repository

import android.util.Log
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import io.github.adrirao.notes.core.util.Resource
import io.github.adrirao.notes.core.util.SimpleResource
import io.github.adrirao.notes.core.util.UiText
import io.github.adrirao.notes.feature_auth.domain.repository.AuthRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

import io.github.adrirao.notes.R
import kotlinx.coroutines.tasks.await
import okhttp3.internal.wait

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository() {

    override val verifiedAccount: Flow<Boolean> = flow {
        while (true) {
            val verified = emailVerified().data!!
            emit(verified)
            delay(2000)
        }
    }

    override suspend fun register(email: String, password: String): SimpleResource {
        return try {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            Resource.Success(Unit)
        } catch (e: FirebaseException) {
            Log.e("register()", e.message.toString())
            Resource.Error(UiText.StringResource(R.string.error_couldnt_reach_firebase))
        } catch (e: Exception) {
            Resource.Error(UiText.StringResource(R.string.oops_something_went_wrong))
        }
    }

    override suspend fun login(email: String, password: String): SimpleResource {
        return try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Resource.Success(Unit)
        } catch (e: FirebaseException) {
            Resource.Error(UiText.StringResource(R.string.error_couldnt_reach_firebase))
        } catch (e: Exception) {
            Resource.Error(UiText.StringResource(R.string.oops_something_went_wrong))
        }
    }

    override suspend fun authenticate(): Resource<Boolean> {
        return try {
            Resource.Success(firebaseAuth.currentUser != null)
        } catch (e: FirebaseException) {
            Resource.Error(UiText.StringResource(R.string.error_couldnt_reach_firebase))
        } catch (e: Exception) {
            Resource.Error(UiText.StringResource(R.string.oops_something_went_wrong))
        }
    }

    override suspend fun emailVerified(): Resource<Boolean> {
        return try {
            firebaseAuth.currentUser?.reload()?.await()
            Resource.Success(firebaseAuth.currentUser?.isEmailVerified ?: false)
        } catch (e: FirebaseException) {
            Resource.Error(UiText.StringResource(R.string.error_couldnt_reach_firebase))
        } catch (e: Exception) {
            Resource.Error(UiText.StringResource(R.string.oops_something_went_wrong))
        }
    }

    override suspend fun sendEmailVerification(): SimpleResource {
        return try {
            firebaseAuth.currentUser?.sendEmailVerification()?.await()
            Resource.Success(Unit)
        } catch (e: FirebaseException) {
            Resource.Error(UiText.StringResource(R.string.error_couldnt_reach_firebase))
        } catch (e: Exception) {
            Resource.Error(UiText.StringResource(R.string.oops_something_went_wrong))
        }
    }
}
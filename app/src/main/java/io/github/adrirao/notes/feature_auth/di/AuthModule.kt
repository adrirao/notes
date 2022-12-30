package io.github.adrirao.notes.feature_auth.di

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.adrirao.notes.feature_auth.data.repository.AuthRepositoryImpl
import io.github.adrirao.notes.feature_auth.domain.repository.AuthRepository
import io.github.adrirao.notes.feature_auth.domain.usecase.FieldUseCases
import io.github.adrirao.notes.feature_auth.domain.usecase.ValidateEmail
import io.github.adrirao.notes.feature_auth.domain.usecase.ValidateEmails
import io.github.adrirao.notes.feature_auth.domain.usecase.ValidatePassword
import io.github.adrirao.notes.feature_auth.domain.usecase.ValidatePasswords
import io.github.adrirao.notes.feature_auth.domain.usecase.remote.AuthUseCases
import io.github.adrirao.notes.feature_auth.domain.usecase.remote.AuthenticateAccount
import io.github.adrirao.notes.feature_auth.domain.usecase.remote.LoginAccount
import io.github.adrirao.notes.feature_auth.domain.usecase.remote.RegisterAccount
import io.github.adrirao.notes.feature_auth.domain.usecase.remote.SendVerificationEmail
import io.github.adrirao.notes.feature_auth.domain.usecase.remote.VerifyEmail
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideRepository(firebaseAuth: FirebaseAuth): AuthRepository =
        AuthRepositoryImpl(firebaseAuth)

    @Provides
    @Singleton
    fun provideAuthUseCases(authRepository: AuthRepository) = AuthUseCases(
        registerAccount = RegisterAccount(authRepository),
        loginAccount = LoginAccount(authRepository),
        verifyEmail = VerifyEmail(authRepository),
        authenticateAccount = AuthenticateAccount(authRepository),
        sendVerificationEmail = SendVerificationEmail(authRepository)
    )

    @Provides
    @Singleton
    fun provideInputFieldUseCases() = FieldUseCases(
        validateEmail = ValidateEmail(),
        validateEmails = ValidateEmails(),
        validatePassword = ValidatePassword(),
        validatePasswords = ValidatePasswords()
    )
}
package io.github.adrirao.notes.feature_auth.domain.usecase

class ValidateEmails {
    operator fun invoke(email:String,reEmail:String):Boolean{
        return email == reEmail
    }
}
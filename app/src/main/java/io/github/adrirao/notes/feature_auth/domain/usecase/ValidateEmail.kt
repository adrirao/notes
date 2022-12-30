package io.github.adrirao.notes.feature_auth.domain.usecase

import android.util.Patterns

class ValidateEmail {
    operator fun invoke(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
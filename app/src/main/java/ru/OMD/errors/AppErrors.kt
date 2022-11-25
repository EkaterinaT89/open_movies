package ru.OMD.errors

import ru.OMD.R
import java.io.IOException
import java.lang.RuntimeException
import java.sql.SQLException

sealed class AppErrors(val code: Int, val info: String): RuntimeException(info) {
    companion object {
        fun from(e: Throwable): AppErrors = when (e) {
            is AppErrors -> e
            is SQLException -> DbError
            is IOException -> NetWorkException
            else -> UnknownException
        }
    }
}

class ApiException(code: Int, message: String) : AppErrors(code, message)

object NetWorkException: AppErrors(-1, (R.string.no_network).toString())
object UnknownException: AppErrors(-1, (R.string.unknown_exception).toString())
object DbError : AppErrors(-1, "error_db")
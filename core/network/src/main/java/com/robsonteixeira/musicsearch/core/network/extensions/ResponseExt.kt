package com.robsonteixeira.musicsearch.core.network.extensions

import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

fun <P, R> Response<P>.handleResponseAsResult(mapper: (P) -> R): Result<R> = if (isSuccessful) {
    body()?.let { response ->
        Result.success(mapper(response))
    } ?: Result.failure(Exception("Server returned a malformed response"))
} else {
    getErrorFromResponseAsResult()
}


@Suppress("SwallowedException")
fun <T, R> Response<T>.getErrorFromResponseAsResult(): Result<R> {
    return errorBody()?.let {
        try {
            Result.failure(Exception(JSONObject(it.string()).getString("message")))
        } catch (e: JSONException) {
            Result.failure(Exception("Parsing server error response failed"))
        }
    } ?: Result.failure(Exception("Server returned invalid error response."))
}

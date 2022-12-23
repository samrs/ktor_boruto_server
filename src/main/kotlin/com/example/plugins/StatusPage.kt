package com.example.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import javax.naming.AuthenticationException

fun Application.configureStatusPages() {
    install(StatusPages) {
        status(HttpStatusCode.NotFound) { call, status ->
            call.respond(
                message = "404: Page not Found.",
                status = status
            )
        }
        exception<Throwable> { call, cause ->
            if(cause is AuthenticationException) {
                call.respondText(text = "403: $cause" , status = HttpStatusCode.Forbidden)
            } else {
                call.respondText(text = "500: $cause" , status = HttpStatusCode.InternalServerError)
            }
        }
    }
}
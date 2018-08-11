package gremapp.ftsc.data.ext

import gremapp.ftsc.data.err.ServerError

interface Req

open class Resp(val error: ServerError) {
    constructor() : this(ServerError.OK)
}

data class AuthReq(val login: String? = null,
                   val pass: String? = null): Req

data class AuthResp (val token: String? = null): Resp()
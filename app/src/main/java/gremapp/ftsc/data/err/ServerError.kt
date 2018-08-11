package gremapp.ftsc.data.err

enum class ServerError(val code: Int, val msg: String?) {
    OK(0, ""),
    INVALID_TOKEN(101, "Токен недействителен"),

    PARSE_RESP_ERR(201, "Ошибка при парсинге ответа от сервера")
}
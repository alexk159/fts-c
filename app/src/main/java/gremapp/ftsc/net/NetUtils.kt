package gremapp.ftsc.net

import org.apache.http.NameValuePair
import com.fasterxml.jackson.databind.ObjectMapper
import gremapp.ftsc.data.err.ServerError
import gremapp.ftsc.data.ext.Resp
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.HttpClients


object NetUtils {
    val om: ObjectMapper = ObjectMapper()

    inline fun <reified T> post(url: String, params: List<NameValuePair>): T {
        val client = HttpClients.createDefault()
        val httpPost = HttpPost(url)

        httpPost.entity = UrlEncodedFormEntity(params)

        val response = client.execute(httpPost).entity.toString()
        client.close()

        val obj = try {
            om.readValue(response, T::class.java)
        } catch (e: Exception) {
            return Resp(ServerError.PARSE_RESP_ERR) as T
        }

        return obj
    }

    inline fun <reified T> get(url: String, params: List<NameValuePair>): T {
        val client = HttpClients.createDefault()

        val urlWithParams = "$url?"
        params.take(params.size - 1).forEach {
            url + it.name + "=" + it.value + "&"
        }
        url + params.last().name + "=" + params.last().value

        val httpGet = HttpGet(url)

        val response = client.execute(httpGet).entity.toString()
        client.close()

        val obj = try {
            om.readValue(response, T::class.java)
        } catch (e: Exception) {
            return Resp(ServerError.PARSE_RESP_ERR) as T
        }

        return obj
    }
}
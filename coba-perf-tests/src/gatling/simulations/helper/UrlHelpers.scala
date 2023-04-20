package helper

import java.io.File
import java.net.URI

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder
import com.typesafe.config._


object UrlHelpers {
  val envFile = System.getProperty("env");
  val res: URI = getClass().getResource("/env/" + envFile).toURI
  val myConfigFile = new File(res)
  val fileConfig = ConfigFactory.parseFile(myConfigFile)
  val conf = ConfigFactory.load(fileConfig)

  //  def gatewayHttpProtocol(): HttpProtocolBuilder = {
  //    http
  //      .baseUrl(conf.getString("gatewayHost"))
  ////      .proxy(Proxy(conf.getString("proxyHost"), conf.getInt("proxyPort")).httpsPort(conf.getInt("proxyPort")))
  ////      .headers(CustomerConstants.HEADERS)
  //  }

  def endpointBuilder(endpoint: String, port: String, path: String): String = {
    conf.getString(endpoint) + ":" + conf.getString(port) + conf.getString(path)
  }

}
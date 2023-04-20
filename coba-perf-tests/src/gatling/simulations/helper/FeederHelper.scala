package helper

import java.net.URI
import java.util.UUID.randomUUID
import io.gatling.core.Predef.{configuration, csv, jsonFile, _}

import scala.io.Source

object FeederHelpers {

  val env: String = System.getProperty("env")
  val users: Int = Integer.valueOf(System.getProperty("users"))
  val templateFolder: String = System.getProperty("user.dir") + "/src/gatling/resources/common/templates/"

  val changeBusinessAddressTemplate: String = templateFolder + "changeBusinessAddressTemplate.json"
  val changeBusinessAddressFeeder = csv(templateFolder + "changeBusinessAddressFeeder.csv").circular //this will feed the file to performance test
  val addressSearchFeeder = jsonFile(templateFolder + "addressSearchFeeder.json").circular //this will feed the file to performance test
  //  val auditAddressFeeder = jsonFile(templateFolder + "auditAddressFeeder.json").circular //this will feed the file to performance test
  val auditAddressFeeder = jsonFile(templateFolder + "auditAddressFeeder.json").circular //this will feed the file to performance test
  val validateAddressSuccessBody: String = templateFolder + "validateAddressSucessBodyFeeder.json" //this will feed the file to performance test

  def getFileContent(filePath: String): String = {
    val res: URI = getClass().getResource(filePath).toURI
    val fileContent = Source.fromFile(res).mkString
    fileContent
  }

  //  val feeder = Iterator.continually(Map(
  //    "requestBody" -> {
  //      getFileContent(changeBusinessAddressTemplate)
  //    }
  //  ))
}


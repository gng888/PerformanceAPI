package scenarios

import constants.APIConstants
import helper.{FeederHelpers, UrlHelpers}
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object ValidateAddress {
  val validateAddress = exec(
    http("Validate Address")
      .post(UrlHelpers.endpointBuilder("api.validate.address.endpoint", "api.validate.address.port", "api.validate.address.path"))
      .headers(APIConstants.HEADERS)
      .header("x-lbg-txn-correlation-id", "1234567899")
      .header("x-lbg-org", "LBG")
      .header("x-lbg-brand", "LYDS")
      .header("x-lbg-assertion-token", "#{token}")
      .header("x-lbg-access-token", "#{accessToken}")
      .header("x-lbg-business-customer-id", "1234567894")
      .body(RawFileBody(FeederHelpers.validateAddressSuccessBody)) //"requestBody" from  feeder
      .check(status.is(200))
      .check(jsonPath("$.Data").notNull)
  )
}

object SearchAddress {
  val getPostcode = exec(
    http("Get Postcode Search")
      .get(UrlHelpers.endpointBuilder("api.search.address.endpoint", "api.search.address.port", "api.search.address.path"))
      .headers(APIConstants.HEADERS)
      .header("x-lbg-txn-correlation-id", "1234567891")
      .header("x-lbg-org", "LBG")
      .header("x-lbg-brand", "BOS")
      .header("x-lbg-assertion-token", "#{token}")
      .header("x-lbg-access-token", "#{accessToken}")
      .queryParam("buildingNumber", "#{buildingNumber}")
      .queryParam("postCode", "#{postCode}")
      .check(status.is(200))
      .check(jsonPath("$.Data").notNull)
  )
}

object GetAddress {
  val getBusinessAddress = exec(
    http("Get Business Address")
      .get(UrlHelpers.endpointBuilder("api.address.endpoint", "api.address.port", "api.address.path"))
      .headers(APIConstants.HEADERS)
      .header("x-lbg-txn-correlation-id", "1234567899")
      .header("x-lbg-org", "LBG")
      .header("x-lbg-brand", "LYDS")
      .header("x-lbg-assertion-token", "#{token}")
      .header("x-lbg-access-token", "#{accessToken}")
      .check(status.is(200))
      .check(jsonPath("$.Data").notNull)
  )

  val getIndividualAddress = exec(
    http("Get Individual Address")
      .get(UrlHelpers.endpointBuilder("api.individual.address.endpoint", "api.individual.address.port", "api.individual.address.path"))
      .headers(APIConstants.HEADERS)
      .header("x-lbg-txn-correlation-id", "1234567899")
      .header("x-lbg-org", "LBG")
      .header("x-lbg-brand", "LYDS")
      .header("x-lbg-assertion-token", "#{token}")
      .header("x-lbg-access-token", "#{accessToken}")
      .check(status.is(200))
      .check(jsonPath("$.Data").notNull)
  )
}

object GetStatus {
  val getBusinessPegaStatus = exec(
    http("Get PEGA Business Status")
      .get(UrlHelpers.endpointBuilder("api.business.case.status.endpoint", "api.business.case.status.port", "api.business.case.status.path"))
      .headers(APIConstants.HEADERS)
      .header("x-lbg-txn-correlation-id", "1234567899")
      .header("x-lbg-org", "LBG")
      .header("x-lbg-brand", "LYDS")
      .header("x-lbg-assertion-token", "#{token}")
      .header("x-lbg-access-token", "#{accessToken}")
      .check(status.is(200))
      .check(jsonPath("$.Data").notNull)
  )

  val getIndividualPegaStatus = exec(
    http("Get PEGA Individual Status")
      .get(UrlHelpers.endpointBuilder("api.individual.case.status.endpoint", "api.individual.case.status.port", "api.individual.case.status.path"))
      .headers(APIConstants.HEADERS)
      .header("x-lbg-txn-correlation-id", "1234567899")
      .header("x-lbg-org", "LBG")
      .header("x-lbg-brand", "LYDS")
      .header("x-lbg-assertion-token", "#{token}")
      .header("x-lbg-access-token", "#{accessToken}")
      .check(status.is(200))
      .check(jsonPath("$.Data").notNull)
  )
}

object GetEntitlements {
  val getEntitlements = exec(
    http("Get Entitlements")
      .get(UrlHelpers.endpointBuilder("api.entitlement.endpoint", "api.entitlement.port", "api.entitlement.path"))
      .headers(APIConstants.HEADERS)
      .header("x-lbg-txn-correlation-id", "1234567899")
      .header("x-lbg-org", "LBG")
      .header("x-lbg-brand", "LYDS")
      .header("x-lbg-assertion-token", "#{token}")
      .header("x-lbg-access-token", "#{accessToken}")
      .check(status.is(200))
      .check(jsonPath("$.Data").notNull)
  )
}

object GetSegments {
  val getSegments = exec(
    http("Get Segments")
      .get(UrlHelpers.endpointBuilder("api.segment.endpoint", "api.segment.port", "api.segment.path"))
      .headers(APIConstants.HEADERS)
      .header("x-lbg-txn-correlation-id", "1234567899")
      .header("x-lbg-org", "LBG")
      .header("x-lbg-brand", "LYDS")
      .header("x-lbg-assertion-token", "#{token}")
      .header("x-lbg-access-token", "#{accessToken}")
      .check(status.is(200))
      .check(jsonPath("$.Data").notNull)
  )
}

object ChangeBusinessAddress {
  val changeBusinessAddress = exec(
    http("Change of Business Address")
      .post(UrlHelpers.endpointBuilder("api.change.of.business.address.endpoint", "api.change.of.business.address.port", "api.change.of.business.address.path"))
      .headers(APIConstants.HEADERS)
      .header("x-lbg-txn-correlation-id", "correlationId")
      .header("x-lbg-org", "LBG")
      .header("x-lbg-brand", "LYDS")
      .header("x-lbg-session-id", "123")
      .header("x-lbg-sub-channel", "Desktop")
      .header("x-lbg-assertion-token", "#{token}")
      .header("x-lbg-access-token", "#{accessToken}")
      .body(ElFileBody(FeederHelpers.changeBusinessAddressTemplate)).asJson
      .check(status.is(200))
      .check(jsonPath("$.Data").notNull)
  )
}

object Audit {
  val storeAudit = exec(
    http("Store Audit")
      .post(UrlHelpers.endpointBuilder("api.audit.address.endpoint", "api.audit.address.port", "api.audit.address.path"))
      .headers(APIConstants.HEADERS)
      .header("x-lbg-txn-correlation-id", "1234567899")
      .header("x-lbg-org", "LBG")
      .header("x-lbg-brand", "LYDS")
      .header("x-lbg-assertion-token", "#{token}")
      .header("x-lbg-access-token", "#{accessToken}")
      .body(StringBody(
        """{
          |  "Data": {
          |    "AuditEvent": {
          |      "EventType": "VALIDATE_ADDRESS_SUCCESS",
          |      "EventTimestamp": "2023-01-22T23:32:11.482592Z",
          |      "EventMessage": "#{eventMessage}|1234567444||COBA|MAILING|DTP|Validate_Add|4|Ravenings Parade|Goodmayes Road|Near Fish&Chips|GBR|IG3 9NR|null|5|Wavecourt|Maxwell Road|Near F&S|GBR|RM7 9NR||||"
          |    }
          |  }
          |}""".stripMargin))
      .check(status.is(200))
      .check(jsonPath("$.Data").notNull)
  )
}
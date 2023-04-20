package tests

import constants.APIConstants
import helper.FeederHelpers.users
import helper.{FeederHelpers, UrlHelpers}
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder
import scenarios.{Audit, ChangeBusinessAddress, GetAddress, GetEntitlements, GetSegments, GetStatus, SearchAddress, ValidateAddress}

import scala.concurrent.duration.DurationInt

class PeakSimulation extends Simulation {

  //  val httpProtocol: HttpProtocolBuilder = UrlHelpers.gatewayHttpProtocol()

  //    val loginAuthentication = exec(
  //      http(CustomerConstants.CUSTOMER_IDENTITY_URL)
  //        .post(CustomerConstants.CUSTOMER_IDENTITY_URL)
  //        .headers(CustomerConstants.HEADERS)
  //        .body(StringBody(FeederHelpers.getFileContent(FeederHelpers.requestFilePath))).asJson //"requestBody" from  feeder
  //        .check(status.is(200))
  //    )

  //  val genrateToken = exec(
  //    http(CustomerConstants.CUSTOMER_TOKEN_URL)
  //      .post(CustomerConstants.CUSTOMER_TOKEN_URL)
  //      .headers(CustomerConstants.HEADERS)
  //      .header("Content-Type", "application/x-www-form-urlencoded")
  //      .formParam("username", "")
  //      .formParam("client_id", "")
  //      .formParam("password", "")
  //      .check(status.is(200))
  //      .check(jsonPath("$.access_token").saveAs("access_token"))
  //  )

  val scn: ScenarioBuilder = scenario("Business Banking, Ltd Company")
    .exec(_.set("token", APIConstants.BB_LTD_ASSERTION_TOKEN))
    .exec(_.set("accessToken", APIConstants.BB_LTD_ACCESS_TOKEN))
    .exec(GetEntitlements.getEntitlements)
    .pause(1)
    .exec(GetSegments.getSegments)
    .pause(1)
    .exec(GetStatus.getBusinessPegaStatus) //Get status API not matching for bb ltd tokens
    .pause(1)
    .exec(GetStatus.getIndividualPegaStatus) //Get status API not matching for bb ltd tokens
    .pause(1)
    .exec(GetAddress.getBusinessAddress)
    .pause(1)
    .exec(GetAddress.getIndividualAddress)
    .pause(1)
    .feed(FeederHelpers.addressSearchFeeder)
    .exec(SearchAddress.getPostcode)
    .pause(1)
    .exec(ValidateAddress.validateAddress)
    .pause(1)
    .feed(FeederHelpers.changeBusinessAddressFeeder)
    .exec(ChangeBusinessAddress.changeBusinessAddress)
    .feed(FeederHelpers.auditAddressFeeder)
    .exec(Audit.storeAudit)

  val scn2: ScenarioBuilder = scenario("Business Banking, Sole Trader")
    .exec(_.set("token", APIConstants.BB_SOLE_ASSERTION_TOKEN))
    .exec(_.set("accessToken", APIConstants.BB_SOLE_ACCESS_TOKEN))
    .exec(GetEntitlements.getEntitlements)
    .pause(1)
    .exec(GetSegments.getSegments)
    .pause(1)
    .exec(GetStatus.getBusinessPegaStatus)
    .pause(1)
    .exec(GetStatus.getIndividualPegaStatus)
    .pause(1)
    .exec(GetAddress.getBusinessAddress)
    .pause(1)
    .exec(GetAddress.getIndividualAddress)
    .pause(1)
    .feed(FeederHelpers.addressSearchFeeder)
    .exec(SearchAddress.getPostcode)
    .pause(1)
    .exec(ValidateAddress.validateAddress)
    .pause(1)
    .feed(FeederHelpers.changeBusinessAddressFeeder)
    .exec(ChangeBusinessAddress.changeBusinessAddress)
    .feed(FeederHelpers.auditAddressFeeder)
    .exec(Audit.storeAudit)

  val scn3: ScenarioBuilder = scenario("Non Business Banking, Ltd Company")
    .exec(_.set("token", APIConstants.NONBB_LTD_ASSERTION_TOKEN))
    .exec(_.set("accessToken", APIConstants.NONBB_LTD_ACCESS_TOKEN))
    .exec(GetEntitlements.getEntitlements)
    .pause(1)
    .exec(GetSegments.getSegments)
    .pause(1)
    .exec(GetStatus.getBusinessPegaStatus)
    .pause(1)
    .exec(GetStatus.getIndividualPegaStatus)
    .pause(1)
    .exec(GetAddress.getBusinessAddress)
    .pause(1)
    .exec(GetAddress.getIndividualAddress)
    .pause(1)
    .feed(FeederHelpers.addressSearchFeeder)
    .exec(SearchAddress.getPostcode)
    .pause(1)
    .exec(ValidateAddress.validateAddress)
    .pause(1)
    .feed(FeederHelpers.changeBusinessAddressFeeder)
    .exec(ChangeBusinessAddress.changeBusinessAddress)
    .feed(FeederHelpers.auditAddressFeeder)
    .exec(Audit.storeAudit)

//@Todo Workout peak simulation injection load
  setUp(
    scn3.inject(
      nothingFor(10), // 1
      atOnceUsers(5), // 2
      rampUsers(10).during(5), // 3
      constantUsersPerSec(20).during(15), // 4
      constantUsersPerSec(20).during(15).randomized, // 5
      rampUsersPerSec(10).to(20).during(2.minutes), // 6
      rampUsersPerSec(10).to(20).during(2.minutes).randomized, // 7
      stressPeakUsers(users).during(20), // 8
    ),
    //    scn2.inject(rampUsers(users).during(30.seconds)),
    //    scn3.inject(rampUsers(users).during(30.seconds)),
  )
    .maxDuration(20.seconds)
    .assertions(
      global.responseTime.max.lt(500),
      global.successfulRequests.percent.gt(95))

  //  setUp(
  //    scn.inject(rampUsers(1000).during(20.minutes)
  //    .maxDuration(10.minutes)),
  //    scn2.inject(atOnceUsers(users)),
  //    scn3.inject(atOnceUsers(users))
  //    )

  //
  //  setUp(scn.inject(atOnceUsers(users))
  //    .protocols(httpProtocol))
}

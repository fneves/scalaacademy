import com.zendesk.scalaacademy.controller.OffsetsController
import org.json4s.jackson.Serialization
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.test.scalatest.ScalatraFlatSpec

class OffsetsControllerSpec extends ScalatraFlatSpec {
  protected implicit lazy val jsonFormats: Formats = DefaultFormats

  addServlet(classOf[OffsetsController], "/api/*")

  behavior of "GET /api/"

  it should "return hello scala" in {
    get("/api/") {
      status should equal(200)
      val response = Serialization.read[SuccessfulResponse](body)
      response.message should equal("Hello Scala!")
    }
  }

  case class SuccessfulResponse(message: String)
}

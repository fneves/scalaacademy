import javax.servlet.ServletContext

import com.zendesk.scalaacademy.controller.OffsetsController
import org.scalatra.LifeCycle

class ScalatraBootstrap extends LifeCycle {

  override def init(context: ServletContext): Unit = {
    context.mount(new OffsetsController, "/api")
  }
}

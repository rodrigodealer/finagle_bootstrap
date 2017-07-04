import java.util.concurrent.TimeUnit

import com.twitter.finagle.{Http, Service}
import com.twitter.finagle.http.Method.Get
import com.twitter.finagle.http.filter.ExceptionFilter
import com.twitter.finagle.http.path.{->, /, Root}
import com.twitter.finagle.http.service.RoutingService
import com.twitter.finagle.http.{Request, Response}
import com.twitter.finagle.service.TimeoutFilter
import com.twitter.finagle.util.DefaultTimer
import com.twitter.util.{Await, Duration, Future}



object Router {
  def apply(): Service[Request, Response] = {
    RoutingService.byMethodAndPathObject {
      case Get -> Root / "users" / username => UserService(username)
//      case Patch -> Root / "posts" / Integer(postId) => PostService(postId)
    }
  }
}

object UserService {
  def apply(username: String): Service[Request, Response] = {
    new Service[Request, Response] {
      override def apply(request: Request): Future[Response] = {
        val response = Response()
        response.setContentString(username)
        Future.value(response)
      }
    }
  }
}

object Server extends App {
  val service = new TimeoutFilter(Duration(5, TimeUnit.SECONDS), DefaultTimer.twitter) andThen
    ExceptionFilter andThen
    Router()

  val server = Http.server.withLabel("server").withHttpStats
    .serve(":8080", service)
  Await.ready(server)
}
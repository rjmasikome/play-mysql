package controllers

import javax.inject._
import model.{User, UserFormData}
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._
import scala.concurrent.Future
import service.UserService
import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class ApplicationController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
//class ApplicationController extends Controller {

  def addUser() = Action.async(parse.json) { implicit request =>

    val userResult = request.body.validate[UserFormData]

    userResult.fold(
      errors => {
        Future.successful(
          BadRequest(
            Json.obj("status" ->"KO", "message" -> JsError.toJson(errors))
          )
        )
      },
      data => {
        val newUser = User(0, data.firstName, data.lastName, data.mobile, data.email)
        UserService.addUser(newUser).map(res =>
          Ok(
            Json.obj(
              "status" ->"OK",
              "message" -> ("User '"+ data.firstName + " " + data.lastName + "' saved.")
            )
          )
        )
      }
    )

  }


  def updateUser(id: Long) = Action.async(parse.json) { implicit request =>

    val userResult = request.body.validate[UserFormData]

    userResult.fold(
      errors => {
        Future.successful(
          BadRequest(
            Json.obj("status" ->"KO", "message" -> JsError.toJson(errors))
          )
        )
      },
      data => {
        val updatedUser = User(id, data.firstName, data.lastName, data.mobile, data.email)
        UserService.updateUser(updatedUser).map(res =>
          Ok(
            Json.obj(
              "status" ->"OK",
              "message" -> ("User '"+ data.firstName + " " + data.lastName + "' saved.")
            )
          )
        )
      }
    )
  }


  def deleteUser(id: Long) = Action.async { implicit request =>
    UserService.deleteUser(id) map { res =>
      Ok(
          Json.obj(
          "status" ->"OK",
          "message" -> ("User '"+ id.toString + "' deleted.")
        )
      )
    }
  }

  def listUsers() = Action.async { implicit request =>
    UserService.listAllUsers map { users =>
      Ok(Json.obj(
        "users" -> users.map { user =>
          Json.obj(
            "id" -> user.id,
            "firstName" -> user.firstName,
            "lastName" -> user.lastName,
            "mobile" -> user.mobile,
            "email" -> user.email
          )
      }))
    }
  }

}

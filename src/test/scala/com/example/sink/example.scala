package com.example.sink


object Example {
  class User {
     def livesIn(in: String): Boolean = true
     def name: String = "Josh"
  }

  object stdout extends  Sink[String] {
    override def apply(in: String): Unit = System.out.println(in);
  }
  def userSink: Sink[User] =
  for {
    user <- stdout.stage[User]
    if user livesIn "pittsburgh"
  } yield user.name
	
  def result = for {
    user <- List(new User)
  } userSink(user)
}

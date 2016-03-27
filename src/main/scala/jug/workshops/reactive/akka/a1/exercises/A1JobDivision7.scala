package jug.workshops.reactive.akka.a1.exercises

import java.util.concurrent.TimeUnit

import akka.actor.{Props, ActorSystem, Actor, ActorRef}

import scala.annotation.tailrec

/**
  * Created by pawel on 27.03.16.
  */
object A1JobDivision7 {
  import JobDivisionProtocol._

  def main(args: Array[String]) {

    val system=ActorSystem("computation")
    val combiner=system.actorOf(Props[Combiner])


    val l=List.tabulate(11)(_*1000)

    //check how tabulate is working  println(l)
   // check how zip is working println(l.zip(l.tail))

    l.zip(l.tail).foreach{case (start,stop) =>
      system.actorOf(Props(new TaskProcessor(combiner))) ! ComputationTask(start,stop)
    }


    TimeUnit.SECONDS.sleep(2)
    println("\n\n\n")
    combiner ! DisplayResult()
    println(s"should be the same as : ${(1 until  10000).sum}")

    system.terminate()
  }
}

import JobDivisionProtocol._

object JobDivisionProtocol{
  case class ComputationTask(start:Long,end:Long)
  case class Result(v:BigDecimal)
  case class DisplayResult()
}

class TaskProcessor(combiner:ActorRef) extends Actor{
  override def receive: Receive = {
    ???
    //EXERCISE - when received 'ComputationTask' run computation adn send response to combiner
  }
}


class Combiner extends Actor {

  var result = BigDecimal(0)

  override def receive: Actor.Receive = {
    ???
    //EXERCISE - when received result, store it in instance field
    //EXERCISe - when received 'DisplayResult' display value stored in instance fiel
  }
}
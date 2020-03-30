package com.al376538.soccergame.standings

import org.json.JSONObject
import java.lang.NullPointerException

class TeamInStanding(t : JSONObject, p: Int, pg : Int, w : Int, d : Int, l : Int, pnt : Int, gF : Int, gA : Int) {

    val team = t
    val position = p
    val playedGames = pg
    val won = w
    val draw = d
    val loss = l
    val points = pnt
    val goalsFor = gF
    val goalsAgainst = gA
}
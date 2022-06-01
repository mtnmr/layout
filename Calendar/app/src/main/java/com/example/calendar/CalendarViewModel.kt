package com.example.calendar

import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CalendarViewModel  : ViewModel(){

    private val myCalendar = Calendar.getInstance(Locale.JAPAN)

    fun getDays() : List<Date>{
        val startDate = myCalendar.time
        val cnt:Int = myCalendar.getActualMaximum(Calendar.WEEK_OF_MONTH) * 7

        myCalendar.set(Calendar.DATE, 1)
        val datOfWeek:Int = myCalendar.get(Calendar.DAY_OF_WEEK) -1
        myCalendar.add(Calendar.DATE, -datOfWeek)

        val days:ArrayList<Date> = arrayListOf()

        for (i in 1..cnt){
            days.add(myCalendar.time)
            myCalendar.add(Calendar.DATE, 1)
        }

        myCalendar.time = startDate

        return days
    }


    fun getTitle():String{
        return SimpleDateFormat("yyyy.MM", Locale.JAPAN).format(myCalendar.time)
    }

    fun nextMonth() : List<Date>{
        myCalendar.add(Calendar.MONTH, 1)
        return getDays()
    }

    fun prevMonth(): List<Date>{
        myCalendar.add(Calendar.MONTH, -1)
        return getDays()
    }

//    fun isCurrentMonth(date:Date):Boolean{
//        val format = SimpleDateFormat("yyyy.MM", Locale.JAPAN)
//        val currentMonth:String = format.format(myCalendar.time)
//        return currentMonth == format.format(date)
//    }
//
//    fun getDayOfWeek(date:Date) : Int{
//        val calendar = Calendar.getInstance()
//        calendar.time = date
//        return calendar.get(Calendar.DAY_OF_WEEK)
//    }
}
package com.dsd.tmsgraduationproject.retrofit

data class RateItem(
    val Cur_ID: Int,
    val Date: String,
    val Cur_Abbreviation: String,
    val Cur_Scale: Int,
    val Cur_Name: String,
    val Cur_OfficialRate: Double
) {
    override fun toString(): String {
        return "$Cur_Scale $Cur_Name стоит $Cur_Scale \n"
    }
}
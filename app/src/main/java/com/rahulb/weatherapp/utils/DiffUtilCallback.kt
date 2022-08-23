package com.rahulb.weatherapp.utils

import androidx.recyclerview.widget.DiffUtil

class DiffUtilCallbackForecast : DiffUtil.ItemCallback<com.rahulb.weatherapp.data.models.Daily>() {
    override fun areItemsTheSame(
        oldItem: com.rahulb.weatherapp.data.models.Daily,
        newItem: com.rahulb.weatherapp.data.models.Daily
    ): Boolean {
        return oldItem.dt == newItem.dt
    }

    override fun areContentsTheSame(
        oldItem: com.rahulb.weatherapp.data.models.Daily,
        newItem: com.rahulb.weatherapp.data.models.Daily
    ): Boolean {
        return oldItem == newItem
    }

}
package com.example.imgursearch.other

import android.content.Context
import android.content.res.Resources
import android.graphics.Typeface
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat


inline fun View.hide() {
	this.visibility = View.GONE
}

inline fun View.show() {
	this.visibility = View.VISIBLE
}

fun Context.resIdByName(resIdName: String?, resType: String): Int {
	resIdName?.let {
		return resources.getIdentifier(it, resType, packageName)
	}
	throw Resources.NotFoundException()
}

fun ActivityCompat.shortToast(context: Context, text: String) {
	Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}

fun Toolbar.changeToolbarFont(){
	for (i in 0 until childCount) {
		val view = getChildAt(i)
		if (view is TextView && view.text == title) {
			view.typeface = Typeface.createFromAsset(view.context.assets, "font/titillium_regular")
			break
		}
	}
}

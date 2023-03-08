package com.volokhinaleksey.viewbyidutils

import android.app.Activity
import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import java.lang.ref.WeakReference
import kotlin.reflect.KProperty

/**
 * Auxiliary delegate utility to get a view using its identifier using the findViewById method
 */

class ViewByIdDelegate<out T : View>(
    private val rootGetter: () -> View?,
    private val viewId: Int
) {

    private var rootRef: WeakReference<View>? = null

    private var viewRef: T? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        var view = viewRef
        val cachedRoot = rootRef?.get()

        val currentRoot = rootGetter()
        if (currentRoot != cachedRoot || view == null) {
            if (currentRoot == null) {
                if (view != null) {
                    return view
                }
                error("Cannot get View, there is no root yet")
            }
            view = currentRoot.findViewById(viewId)
            viewRef = view
            rootRef = WeakReference(currentRoot)
        }
        checkNotNull(view) { "View with id \"$viewId\" not found in root" }
        return view
    }

}

/**
 * Extension function for using the viewById method in Activity
 */

fun <T : View> Activity.viewById(@IdRes viewId: Int): ViewByIdDelegate<T> {
    return ViewByIdDelegate({ window.decorView.findViewById(android.R.id.content) }, viewId)
}

/**
 * Extension function for using the viewById method in Fragment
 */

fun <T : View> Fragment.viewById(@IdRes viewId: Int): ViewByIdDelegate<T> {
    return ViewByIdDelegate({ view }, viewId)
}

/**
 * Extension function for using the viewById method in any view
 */

fun <T : View> View.viewById(@IdRes viewId: Int): ViewByIdDelegate<T> {
    return ViewByIdDelegate({ this }, viewId)
}

/**
 * Extension function for using the viewById method in RecyclerView.ViewHolder
 */

fun <T : View> RecyclerView.ViewHolder.viewById(@IdRes viewId: Int): ViewByIdDelegate<T> {
    return ViewByIdDelegate({ this.itemView }, viewId)
}




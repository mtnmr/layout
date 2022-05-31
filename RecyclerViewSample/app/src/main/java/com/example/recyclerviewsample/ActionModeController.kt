package com.example.recyclerviewsample

import android.app.Activity
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.annotation.MenuRes

class ActionModeController(
    @MenuRes private val resId: Int,
    private val type: Int,
    private val onAction : (MenuItem) -> Unit) {

    // Activity から ActionMode を開始する
    fun startActionMode(activity: Activity) {
        activity.startActionMode(createActionModeCallback(), type)
    }

    private fun createActionModeCallback(): ActionMode.Callback {
        return object: ActionMode.Callback {
            override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
                // リソースIDをInflaterに渡してメニューを生成してやる
                mode.menuInflater.inflate(resId, menu)
                return true
            }

            override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
                // 更新されることは想定していないので何もしない
                return false
            }

            override fun onDestroyActionMode(mode: ActionMode) {
                // 破棄時に特にやることは無いので何もしない
            }

            override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
                // onAction に MenuItem を渡して選択されたことを通知する
                onAction.invoke(item)

                // MenuItem が選択されたあとは Action Mode を終了させる
                mode.finish()
                return true
            }
        }
    }
}
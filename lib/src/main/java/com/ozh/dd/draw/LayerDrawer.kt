/*
 * Copyright 2019 Oleg Zhilo
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.ozh.dd.draw

import android.graphics.Canvas
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Abstract class for implementation LayerDrawer
 */
interface LayerDrawer {
    fun draw(canvas: Canvas, view: View, recyclerView: RecyclerView, state: RecyclerView.State) {
        //Override
    }

    fun draw(canvas: Canvas, recyclerView: RecyclerView, state: RecyclerView.State) {
        //Override
    }
}
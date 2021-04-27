/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.systemui.media

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.IntegerRes
import com.android.systemui.R
import com.android.systemui.util.animation.TransitionLayout

/** ViewHolder for a Smartspace media recommendation. */
class RecommendationViewHolder private constructor(itemView: View) {

    val recommendations = itemView as TransitionLayout

    // Recommendation screen
    val mediaCoverItems = listOf<ImageView>(
        itemView.requireViewById(R.id.media_cover1),
        itemView.requireViewById(R.id.media_cover2),
        itemView.requireViewById(R.id.media_cover3),
        itemView.requireViewById(R.id.media_cover4))
    val mediaLogoItems = listOf<ImageView>(
        itemView.requireViewById(R.id.media_logo1),
        itemView.requireViewById(R.id.media_logo2),
        itemView.requireViewById(R.id.media_logo3),
        itemView.requireViewById(R.id.media_logo4))
    val mediaCoverItemsResIds = listOf<@IntegerRes Int>(
        R.id.media_cover1,
        R.id.media_cover2,
        R.id.media_cover3,
        R.id.media_cover4)
    val mediaLogoItemsResIds = listOf<@IntegerRes Int>(
        R.id.media_logo1,
        R.id.media_logo2,
        R.id.media_logo3,
        R.id.media_logo4)

    // Settings/Guts screen
    val cancel = itemView.requireViewById<View>(R.id.cancel)
    val dismiss = itemView.requireViewById<ViewGroup>(R.id.dismiss)
    val dismissLabel = dismiss.getChildAt(0)
    val recommendationText = itemView.requireViewById<TextView>(R.id.recommendation_text)
    val settings = itemView.requireViewById<View>(R.id.settings)

    init {
        (recommendations.background as IlluminationDrawable).let { background ->
            mediaCoverItems.forEach { background.registerLightSource(it) }
            mediaLogoItems.forEach { background.registerLightSource(it) }
            background.registerLightSource(cancel)
            background.registerLightSource(dismiss)
            background.registerLightSource(dismissLabel)
            background.registerLightSource(settings)
        }
    }

    companion object {
        /**
         * Creates a RecommendationViewHolder.
         *
         * @param inflater LayoutInflater to use to inflate the layout.
         * @param parent Parent of inflated view.
         */
        @JvmStatic fun create(inflater: LayoutInflater, parent: ViewGroup):
            RecommendationViewHolder {
            val itemView =
                inflater.inflate(
                    R.layout.media_smartspace_recommendations,
                    parent,
                    false /* attachToRoot */)
            // Because this media view (a TransitionLayout) is used to measure and layout the views
            // in various states before being attached to its parent, we can't depend on the default
            // LAYOUT_DIRECTION_INHERIT to correctly resolve the ltr direction.
            itemView.layoutDirection = View.LAYOUT_DIRECTION_LOCALE
            return RecommendationViewHolder(itemView)
        }

        // Res Ids for the control components on the recommendation view.
        val controlsIds = setOf(
            R.id.media_cover1,
            R.id.media_cover2,
            R.id.media_cover3,
            R.id.media_cover4,
            R.id.media_logo1,
            R.id.media_logo2,
            R.id.media_logo3,
            R.id.media_logo4
        )

        // Res Ids for the components on the guts panel.
        val gutsIds = setOf(
            R.id.recommendation_text,
            R.id.remove_text,
            R.id.cancel,
            R.id.dismiss,
            R.id.settings
        )
    }
}
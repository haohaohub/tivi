/*
 * Copyright 2017 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package app.tivi.home.popular

import android.os.Bundle
import android.view.View
import app.tivi.R
import app.tivi.SharedElementHelper
import app.tivi.data.resultentities.PopularEntryWithShow
import app.tivi.home.HomeNavigator
import app.tivi.util.EntryGridFragment
import kotlinx.android.synthetic.main.fragment_rv_grid.*
import javax.inject.Inject

class PopularShowsFragment : EntryGridFragment<PopularEntryWithShow, PopularShowsViewModel>(PopularShowsViewModel::class.java) {

    @Inject lateinit var homeNavigator: HomeNavigator

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        grid_toolbar.apply {
            title = getString(R.string.discover_popular)
            setNavigationOnClickListener {
                viewModel.onUpClicked(homeNavigator)
            }
        }
    }

    override fun onItemClicked(item: PopularEntryWithShow) {
        val sharedElements = SharedElementHelper()
        grid_recyclerview.findViewHolderForItemId(item.generateStableId()).let {
            sharedElements.addSharedElement(it.itemView, "poster")
        }
        viewModel.onItemClicked(item, homeNavigator, sharedElements)
    }
}

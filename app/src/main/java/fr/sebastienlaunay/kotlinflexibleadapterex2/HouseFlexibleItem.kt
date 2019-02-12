package fr.sebastienlaunay.kotlinflexibleadapterex2

import android.animation.ObjectAnimator
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.KeyEvent.ACTION_DOWN
import android.view.KeyEvent.ACTION_UP
import android.view.MotionEvent.ACTION_MOVE
import android.view.View
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import eu.davidea.flexibleadapter.items.IFlexible
import fr.sebastienlaunay.kotlinflexibleadapterex2.Model.House
import fr.sebastienlaunay.kotlinflexibleadapterex2.Model.Order
import fr.sebastienlaunay.tanexpress.ui.AbsFlexibleViewHolder
import kotlinx.android.synthetic.main.item_house.view.*
import kotlinx.android.synthetic.main.item_order.view.*

class HouseFlexibleItem(var house: House, private val houseListener: OrderFlexibleAdapter.HouseListener) :
    AbstractFlexibleItem<AbsFlexibleViewHolder>(), FlexibleAdapter.OnItemClickListener {

    override fun bindViewHolder(
        adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>?,
        holder: AbsFlexibleViewHolder?,
        position: Int,
        payloads: MutableList<Any>?
    ) {
        (holder as ViewHolder).bind(house, houseListener)
    }

    override fun createViewHolder(
        view: View,
        adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>
    ): AbsFlexibleViewHolder = ViewHolder(view, adapter)


    override fun getLayoutRes(): Int = R.layout.item_house
    override fun equals(other: Any?): Boolean = other is HouseFlexibleItem
    override fun onItemClick(view: View?, position: Int): Boolean {
        return false
    }

    class ViewHolder(view: View, adapter: FlexibleAdapter<out IFlexible<*>>) :
        AbsFlexibleViewHolder(view, adapter, false) {

        fun bind(house: House, houseListener: OrderFlexibleAdapter.HouseListener) {

            contentView.houseTitle.text = house.title
            contentView.houseDescription.text = house.description

            contentView.setOnTouchListener { v, event ->

                if (event.action == ACTION_DOWN) {
                    v.animate().translationX(200f).setDuration(100).withEndAction {
                        v.animate().translationX(0f).duration = 100
                    }

                    house.id.let { houseId ->
                        houseListener.onHouseClicked(houseId)
                    }
                }
                true
            }
        }
    }
}
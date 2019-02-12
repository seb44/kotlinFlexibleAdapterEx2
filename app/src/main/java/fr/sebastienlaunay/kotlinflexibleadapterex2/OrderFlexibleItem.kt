package fr.sebastienlaunay.kotlinflexibleadapterex2

import android.support.v7.widget.RecyclerView
import android.view.View
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import eu.davidea.flexibleadapter.items.IFlexible
import fr.sebastienlaunay.kotlinflexibleadapterex2.Model.Order
import fr.sebastienlaunay.tanexpress.ui.AbsFlexibleViewHolder
import kotlinx.android.synthetic.main.item_order.view.*

class OrderFlexibleItem(var order: Order, private val listener: OrderFlexibleAdapter.OrderListener) :
    AbstractFlexibleItem<AbsFlexibleViewHolder>(), FlexibleAdapter.OnItemClickListener {

    override fun bindViewHolder(
        adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>?,
        holder: AbsFlexibleViewHolder?,
        position: Int,
        payloads: MutableList<Any>?
    ) {
        (holder as ViewHolder).bind(order, listener)
    }

    override fun createViewHolder(
        view: View,
        adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>
    ): AbsFlexibleViewHolder = ViewHolder(view, adapter)


    override fun getLayoutRes(): Int = R.layout.item_order
    override fun equals(other: Any?): Boolean = other is OrderFlexibleItem
    override fun onItemClick(view: View?, position: Int): Boolean {
        return false
    }

    class ViewHolder(view: View, adapter: FlexibleAdapter<out IFlexible<*>>) :
        AbsFlexibleViewHolder(view, adapter, false) {

        fun bind(order: Order, listener: OrderFlexibleAdapter.OrderListener) {

            contentView.setOnClickListener {
                order.id.let { orderId ->
                    listener.onOrderClicked(orderId)
                }
            }

            contentView.title.text = order.title
            contentView.subTitle.text = order.subTitle
            contentView.checkBox.isChecked = order.validate
        }
    }
}
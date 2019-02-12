package fr.sebastienlaunay.kotlinflexibleadapterex2

import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import fr.sebastienlaunay.kotlinflexibleadapterex2.Model.House
import fr.sebastienlaunay.kotlinflexibleadapterex2.Model.Order
import fr.sebastienlaunay.tanexpress.ui.AbsFlexibleAdapter
import fr.sebastienlaunay.tanexpress.ui.AbsFlexibleViewHolder

class OrderFlexibleAdapter(orderList: List<Order>, houseList: List<House>, private val orderListener: OrderListener, private val houseListener : HouseListener) :
    AbsFlexibleAdapter<AbstractFlexibleItem<AbsFlexibleViewHolder>, AbsFlexibleViewHolder>() {

    var orderList = orderList
    var houseList = houseList

    fun updateData() {
        setOrderAndHouseData(orderList,houseList)
    }

    fun updateOrderData() {
        setOrderData(orderList)
    }

    fun updateHouseData() {
        setHouseData(houseList)
    }

    fun setOrderData(orderList: List<Order>) {
        val dataToDisplay = mutableListOf<AbstractFlexibleItem<AbsFlexibleViewHolder>>()

        orderList.forEach { order ->
            dataToDisplay.add(OrderFlexibleItem(order, orderListener))
        }

        updateDataSet(dataToDisplay)
    }

    fun setHouseData(houseList: List<House>) {
        val houseDataToDisplay = mutableListOf<AbstractFlexibleItem<AbsFlexibleViewHolder>>()

        houseList.forEach { house ->
            houseDataToDisplay.add(HouseFlexibleItem( house, houseListener))
        }

        updateDataSet(houseDataToDisplay)
    }

    fun setOrderAndHouseData(orderList: List<Order>, houseList: List<House>) {
        val dataToDisplay = mutableListOf<AbstractFlexibleItem<AbsFlexibleViewHolder>>()

        orderList.forEach { order ->
            dataToDisplay.add(OrderFlexibleItem(order, orderListener))
        }

        houseList.forEach { house ->
            dataToDisplay.add(HouseFlexibleItem( house, houseListener))
        }

        updateDataSet(dataToDisplay)

    }

    interface OrderListener {
        fun onOrderClicked(orderId: Int)
    }

    interface HouseListener {
        fun onHouseClicked(houseId: Int)
    }
}
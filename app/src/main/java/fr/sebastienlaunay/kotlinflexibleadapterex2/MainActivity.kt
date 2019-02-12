package fr.sebastienlaunay.kotlinflexibleadapterex2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import fr.sebastienlaunay.kotlinflexibleadapterex2.Model.House
import fr.sebastienlaunay.kotlinflexibleadapterex2.Model.Order
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OrderFlexibleAdapter.OrderListener, OrderFlexibleAdapter.HouseListener {

    private var orderList = ArrayList<Order>()
    private var houseList = ArrayList<House>()
    private lateinit var listAdapter: OrderFlexibleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listAdapter = OrderFlexibleAdapter(orderList, houseList, this, this)

        laListe.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = listAdapter
        }

        val order1 = Order(1, "Ordre 1", "Sous titre 1", false)
        val order2 = Order(2, "Ordre 2", "Sous titre 2", false)
        val order3 = Order(3, "Ordre 3", "Sous titre 3", true)
        val order4 = Order(4, "Ordre 4", "Sous titre 4", false)
        val order5 = Order(5, "Ordre 4", "Sous titre 5", true)
        val order6 = Order(6, "Ordre 6", "Sous titre 6", false)
        val order7 = Order(7, "Ordre 7", "Sous titre 7", false)

        orderList.add(order1); orderList.add(order2); orderList.add(order3); orderList.add(order4)
        orderList.add(order5); orderList.add(order6); orderList.add(order7)

        val house1 = House(1, "Maison 1", "1 pièce")
        val house2 = House(2, "Maison 2", "2 pièces")
        val house3 = House(3, "Maison 3", "3 pièces")
        val house4 = House(4, "Maison 4", "4 pièces")
        val house5 = House(5, "Maison 5", "5 pièces")

        houseList.add(house1); houseList.add(house2); houseList.add(house3); houseList.add(house4); houseList.add(house5)

        listAdapter.setOrderAndHouseData(orderList, houseList)

        btnOrder.setOnClickListener { listAdapter.updateOrderData() }
        btnHouse.setOnClickListener { listAdapter.updateHouseData() }
        btnBoth.setOnClickListener { listAdapter.updateData() }
        btnValidate.setOnClickListener { afficherLeResultat(orderList) }
    }

    private fun afficherLeResultat(orderList: ArrayList<Order>) {

        textResult.text = "Vous avez cliqué sur : "
        orderList.filter { it.validate == true }.apply {
            this.forEach {
                textResult.text = textResult.text.toString() + "${it.id} - "
            }
        }
    }

    override fun onOrderClicked(orderId: Int) {
        orderList.find { it.id == orderId }?.apply {
            this.validate = !this.validate
        }
        listAdapter.updateData()
    }

    override fun onHouseClicked(houseId: Int) {

        houseList.find { it.id == houseId }?.let { house ->
            textResult.text = "Vous avez cliqué sur la maison suivante : ${house.title}"
        }
    }
}


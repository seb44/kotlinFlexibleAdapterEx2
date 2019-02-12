package fr.sebastienlaunay.tanexpress.ui

import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import eu.davidea.viewholders.FlexibleViewHolder

abstract class AbsFlexibleAdapter<FI : AbstractFlexibleItem<VH>, VH : FlexibleViewHolder>(items: List<FI>? = null) : FlexibleAdapter<FI>(items)
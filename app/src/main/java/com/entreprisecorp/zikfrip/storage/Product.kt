package com.entreprisecorp.zikfrip.storage

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(

    val id: Int = 0,
    val name : String = "Stratocaster",
    val description : String = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas faucibus tempor fringilla aliquet sagittis, mi, sit cum. Ultricies pretium etiam sed id duis nibh egestas facilisi venenatis. Ut nisl varius ut augue arcu. Nam et aliquam nunc id nisl, eget suspendisse mi.",
    val imageURL : String = "https://images.unsplash.com/photo-1606043526494-a853922f42e8?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80",
    val price : Int = 100,
    val category : String,
    var deliveryFee : Double
) : Parcelable {
    public fun priceToString() : String {
        return "$price,-€"
    }
}
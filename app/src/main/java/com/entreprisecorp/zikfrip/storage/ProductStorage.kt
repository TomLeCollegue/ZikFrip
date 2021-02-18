package com.entreprisecorp.zikfrip.storage

import com.entreprisecorp.zikfrip.storage.Category
import com.entreprisecorp.zikfrip.storage.Product
import com.entreprisecorp.zikfrip.storage.ProductStorage.Singleton.productList

class ProductStorage {

    object Singleton{
        val productList = arrayListOf<Product>(
            Product(
                0,
                "Stratocaster Rouge",
                "tres bon etat a vendre",
                "https://dmldbxcvbvfk2.cloudfront.net/images/1962-fender-stratocaster-fiesta-red-6-1.jpg",
                150,
                "Guitares"
            ),
            Product(
                1,
                "Cajon Bois",
                "Percu sympa",
                "https://www.bax-shop.fr/blog/wp-content/uploads/2019/03/blog_cajon_ritmes.jpg",
                100,
                "Percussions"
            ),
            Product(
                2,
                "Stratocaster Rouge",
                "tres bon etat a vendre",
                "https://dmldbxcvbvfk2.cloudfront.net/images/1962-fender-stratocaster-fiesta-red-6-1.jpg",
                150,
                "Guitares"
            ),
            Product(
                3,
                "Cajon Bois",
                "Percu sympa",
                "https://www.bax-shop.fr/blog/wp-content/uploads/2019/03/blog_cajon_ritmes.jpg",
                100,
                "Percussions"
            ),
            Product(
                4,
                "Stratocaster Rouge",
                "tres bon etat a vendre",
                "https://dmldbxcvbvfk2.cloudfront.net/images/1962-fender-stratocaster-fiesta-red-6-1.jpg",
                150,
                "Guitares"
            ),
            Product(
                5,
                "Cajon Bois",
                "Percu sympa",
                "https://www.bax-shop.fr/blog/wp-content/uploads/2019/03/blog_cajon_ritmes.jpg",
                100,
                "Percussions"
            ),
            Product(
                6,
                "Stratocaster Rouge",
                "tres bon etat a vendre",
                "https://dmldbxcvbvfk2.cloudfront.net/images/1962-fender-stratocaster-fiesta-red-6-1.jpg",
                150,
                "Guitares"
            ),
            Product(
                7,
                "Cajon Bois",
                "Percu sympa",
                "https://www.bax-shop.fr/blog/wp-content/uploads/2019/03/blog_cajon_ritmes.jpg",
                100,
                "Percussions"
            )


        )
        val categoriesList = arrayListOf<Category>(
            Category(
                "Guitares",
                "https://cdn.pixabay.com/photo/2016/11/18/19/55/guitar-1836655_1280.jpg"
            ),
            Category(
                "Pianos",
                "https://cdn.pixabay.com/photo/2020/06/29/19/26/piano-5353974_1280.jpg"
            ),
            Category(
                "Percussions",
                "https://cdn.pixabay.com/photo/2016/11/19/13/57/drum-set-1839383_1280.jpg"
            )
        )
    }

    companion object{
        fun CalculateDeliferyFee(price : Int) : Int{

            return 5
        }



        fun getProductbyCategoy(category : String) : List<Product> {

            return productList.filter { it.category == category }
        }

        fun getAllProduct() : List<Product> {

            return productList
        }

        fun getFirstsproduct(idMax : Int): List<Product> {

            return productList.filter { it.id < idMax }
        }

    }
}
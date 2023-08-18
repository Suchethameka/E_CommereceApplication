package com.example.e_commereceapplication.presenter

import com.example.e_commereceapplication.model.Network.User.UserProfile
import com.example.e_commereceapplication.model.Network.cart.CartItem
import com.example.e_commereceapplication.model.Network.category.CategoryResponse
import com.example.e_commereceapplication.model.Network.productslist.ProductListResponse
import com.example.e_commereceapplication.model.Network.subCategory.SubcategoryResponse
import com.example.e_commereceapplication.model.Network.productDetailsModel.ProductDescriptionResponse

interface MVPShoppingCart {
    interface IRegisterPresenter{
        fun registerUser(userProfile: UserProfile)
    }
    interface RegisterView{
        fun setError()
        fun setSuccess()
    }
    interface ILoginPresenter{
        fun validateUser(emailId:String, password:String)
    }

    interface LoginView{

        fun setError()
        fun setSuccess()

    }

    interface ICategoryPresenter{
        fun getCategories()
    }
    interface CategoryView{
        fun setError()
        fun setSuccess(categoriesResponse: CategoryResponse)
    }


    interface ISubCategoryPresenter{

        fun getSubCategories(catId:String)
    }
    interface SubCategoryView{
        fun setError()
        fun setSuccess(subcategoryResponse: SubcategoryResponse)
    }

    interface IProductListPresenter{

        fun getProducts(subCatId:String)
    }

    interface ProductView{
        fun setError()
        fun setSuccess(productListResponse: ProductListResponse)
    }

    interface IProductDetailsPresenter{

        fun getProductDetails(productId:String)

        fun addToCart(cartItem: CartItem)

        fun deleteItemInCart(cartItem: CartItem)

        fun getProductWithId(productId: String): CartItem?
    }

    interface ProductDetailsView{
        fun setError()
        fun setSuccess(productDescriptionResponse: ProductDescriptionResponse)
    }

    interface ICartPresenter{

        fun getCartItems(): List<CartItem>
    }

}
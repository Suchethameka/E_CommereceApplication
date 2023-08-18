package com.example.e_commereceapplication.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.e_commereceapplication.databinding.QuantityStepperBinding

interface QuantityStepperListener{
    fun onQuantityChanged(quantity: Int)
    fun onQuantityZero()
}
class QuantityStepper(context: Context, attrs: AttributeSet):ConstraintLayout(context, attrs) {

    private var quantityStepperListener: QuantityStepperListener? = null
    private val binding: QuantityStepperBinding =
        QuantityStepperBinding.inflate(LayoutInflater.from(getContext()), this)
    private var quantity = 1

    init {
        with(binding){
            imgAdd.setOnClickListener {
                tvQuantity.text = (++quantity).toString()
                quantityStepperListener?.onQuantityChanged(quantity)
            }
            imgRemove.setOnClickListener {
                if(--quantity == 0){
                    tvQuantity.text = 1.toString()
                    quantityStepperListener?.onQuantityZero()

                } else{
                    tvQuantity.text = quantity.toString()
                    quantityStepperListener?.onQuantityChanged(quantity)
                }
            }
        }
    }

    fun setQuantity(quantity: Int){
        binding. tvQuantity.text = quantity.toString()
    }

    fun setQuantityStepperListener(quantityStepperListener: QuantityStepperListener){
        this.quantityStepperListener = quantityStepperListener
    }



}
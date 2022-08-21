import React from 'react'
import CartItem from './CartItem'

const Cart = ({ cart, onAddToCart }) => {
  return (
    <>
      <div class="cards-wrapper">
        <div>
          {cart
            .sort((a, b) => a.name.localeCompare(b.name))
            .map(item => (
              <CartItem key={item.id} book={item} onAddToCart={onAddToCart} />
            ))}
        </div>
      </div>
    </>
  )
}

export default Cart

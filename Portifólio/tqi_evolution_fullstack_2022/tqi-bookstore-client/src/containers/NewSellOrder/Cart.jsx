import React from 'react'
import CartItem from './CartItem'

const Cart = ({ cart, onAddToCart, onRemoveFromCart }) => {
  const totalAmount = cart.reduce(
    (acc, book) => acc + book.price * book.quantity,
    0
  )

  return (
    <>
      <div class="cards-wrapper">
        <div>
          {cart
            .sort((a, b) => a.name.localeCompare(b.name))
            .map(item => (
              <CartItem
                key={item.id}
                book={item}
                onAddToCart={onAddToCart}
                onRemoveFromCart={onRemoveFromCart}
              />
            ))}
        </div>
        <h2>Preço total: R$ {totalAmount} </h2>
      </div>
    </>
  )
}

export default Cart

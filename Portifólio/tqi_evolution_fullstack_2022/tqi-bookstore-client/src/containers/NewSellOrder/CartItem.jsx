import React from 'react'

const BooksListItem = ({ book, onAddToCart, onRemoveFromCart }) => {
  const toCurrency = n => `R$${(n ?? 0).toFixed(2)}`

  return (
    <div class="card-container">
      <span class="card-info">
        <div class="card-image">
          <img src={book.image} alt={`${book.title} cover.`} />
        </div>

        <div class="card-data">
          <div class="card-text">
            <h3>{book.name}</h3>
            <p>{book.author}</p>
          </div>

          <div class="card-text">
            <h3>Quantidade</h3>
            <p>{book.quantity}</p>
          </div>

          <div class="card-text">
            <h3>Preço Unitário</h3>
            <p>{toCurrency(book.price)}</p>
          </div>

          <div class="card-text">
            <h3>Preço Total</h3>
            <p>{toCurrency(book.price * book.quantity)}</p>
          </div>

          <span class="card-buttons">
            <button onClick={() => onRemoveFromCart(book.id)}>-</button>
            <button onClick={() => onAddToCart(book.id)}>+</button>
          </span>
        </div>
      </span>
    </div>
  )
}

export default BooksListItem

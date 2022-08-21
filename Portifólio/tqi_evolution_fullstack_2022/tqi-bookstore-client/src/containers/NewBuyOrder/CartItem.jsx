import React from 'react'

const BooksListItem = ({ book }) => {
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
            <h3>Estoque atual</h3>
            <p>{book.quantity} Unidades</p>
          </div>

          <div class="card-text">
            <h3>Preço Atual</h3>
            <p>{toCurrency(book.price)}</p>
          </div>
        </div>
      </span>
    </div>
  )
}

export default BooksListItem

import React from 'react'

const BooksListItem = ({ book, onAddToCart }) => {
  const toCurrency = n => `R$${n.toFixed(2)}`

  return (
    <div class="card-container">
      <span class="card-info">
        <div class="card-image">
          <img src={book.image} alt={`${book.title} cover.`} />
        </div>

        <div class="card-data">
          <div class="card-text">
            <h3>{book.name}</h3>
            <p>{book.author.name}</p>
          </div>

          <div class="card-text">
            <h3>Estoque</h3>
            <p>{book.quantity} Unidades</p>
          </div>

          <div class="card-text">
            <h3>Preço</h3>
            <p>{toCurrency(book.price)}</p>
          </div>
        </div>
      </span>

      <span class="card-buttons">
        {onAddToCart ? (
          <>
            {' '}
            <button onClick={() => onAddToCart(book.id)}>Adicionar</button>
          </>
        ) : (
          <></>
        )}
      </span>
    </div>
  )
}

export default BooksListItem

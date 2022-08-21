import BooksListItem from "./BooksListItem"
import React from 'react'

const BooksList = ({books, onAddToCart}) => {
  return (
    <div class="cards-wrapper">
    {books.map(book => <BooksListItem key={book.id} book={book} onAddToCart={onAddToCart}/>)}
    </div>
  )
}

export default BooksList
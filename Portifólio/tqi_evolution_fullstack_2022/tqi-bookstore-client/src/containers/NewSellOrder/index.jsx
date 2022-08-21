import React, { useState, useEffect } from 'react'
import Main from '../../components/Main'
import SearchBar from '../../components/SearchBar'
import Spinner from '../../components/Spinner'
import BooksList from '../Homepage/BooksList'
import Input from '../../components/Input'
import ClientInfo from './ClientInfo'
import Cart from './Cart'
import { api } from '../../services/api'

const NewSellOrder = () => {
  const [formData, setFormData] = useState({
    cpf: '',
    client: { name: '-', email: '-', id: null }
  })
  const [validation, setValidation] = useState({ cpf: true })
  const [cart, setCart] = useState([])

  const [books, setBooks] = useState(null)
  const [filteredBooks, setFilteredBooks] = useState([])

  useEffect(() => {
    const fetchBooks = async () => {
      const booksFromServer = await api.get('/book/findAll')
      return setBooks(booksFromServer.data)
    }

    fetchBooks()
  }, [])

  useEffect(() => {
    setFilteredBooks(books)
  }, [books])

  const isLoading = books === null

  const handleFilter = text => {
    setFilteredBooks(
      books.filter(
        book =>
          book.author.toLowerCase().includes(text.toLowerCase()) ||
          book.name.toLowerCase().includes(text.toLowerCase())
      )
    )
  }

  const onAddToCart = bookId => {
    const bookObject = books.find(book => book.id === bookId)
    const bookOnCart = cart.find(item => item.id === bookId)

    if (!bookOnCart) {
      setCart(previous => [...previous, { ...bookObject, quantity: 1 }])
      return
    }

    setCart(previous => {
      const itemsToKeep = previous.filter(item => item.id !== bookId)
      const updatedCartItems = [
        ...itemsToKeep,
        { ...bookOnCart, quantity: bookOnCart.quantity + 1 }
      ]

      return updatedCartItems
    })
  }

  useEffect(() => {
    console.log(cart)
  }, [cart])

  const onRemoveFromCart = bookId => {
    const bookOnCart = cart.find(item => item.id === bookId)

    if (!bookOnCart) {
      return
    }

    const lastUnit = bookOnCart.quantity === 1

    setCart(previous => {
      const itemsToKeep = previous.filter(item => item.id !== bookId)
      const updatedItem = { ...bookOnCart, quantity: bookOnCart.quantity - 1 }
      const updatedCartItems = itemsToKeep

      if (!lastUnit) {
        updatedCartItems.push(updatedItem)
      }

      return updatedCartItems
    })
  }

  const onChangeCpfInput = event =>
    setFormData(previous => ({ ...previous, cpf: event.target.value }))

  const handleSubmitOrder = async () => {
    if (!formData.client.id) {
      alert('Selecione um cliente válido!')
      return
    }

    const totalPrice = cart.reduce(
      (acc, book) => acc + book.price * book.quantity,
      0
    )
    const bookIds = cart.map(book => book.id)
    const booksQnt = cart.map(book => book.quantity)

    const order = {
      clientId: formData.client.id,
      totalPrice,
      bookIds,
      booksQnt
    }

    try {
      await api.post('/sell/create', order)
      alert('Pedido concluído!')
      window.location.reload()
    } catch (error) {
      alert('Erro ao enviar pedido.')
    }
  }
  const handleSearchClient = async () => {
    const cpfIsValid = formData.cpf.trim().length > 0
    setValidation({ cpf: cpfIsValid })

    if (!cpfIsValid) {
      return
    }

    try {
      const clientExists = await api.get(`/client/findByCpf/${formData.cpf}`)
      setFormData(previous => ({ ...previous, client: clientExists.data }))
    } catch (error) {
      alert('Não foi encontrado cliente com o CPF informado.')
    }
  }

  return (
    <Main>
      <main className="container">
        <h2>Nova Venda</h2>
        <div className="search-bar-container">
          <h2>CPF DO CLIENTE</h2>
          <div class="search-wrapper">
            <Input
              title=""
              name="cpf"
              isValid={validation.cpf}
              value={formData.cpf}
              onChange={onChangeCpfInput}
              onClick={handleSearchClient}
            />
            <button onClick={handleSearchClient}>Pesquisar</button>
          </div>
        </div>
        <div class="cards-wrapper">
          <div class="card-container">
            <ClientInfo client={formData.client} />
          </div>
        </div>
      </main>

      <main className="container">
        <div className="search-bar-container">
          <h2>Carrinho</h2>
        </div>
        <Cart
          onAddToCart={onAddToCart}
          onRemoveFromCart={onRemoveFromCart}
          cart={cart}
        />
        <button onClick={handleSubmitOrder}>Enviar pedido</button>
      </main>

      <main class="container">
        <SearchBar title="Adicionar Livros" onSearch={handleFilter} />
        {isLoading ? (
          <Spinner />
        ) : (
          <BooksList books={filteredBooks ?? []} onAddToCart={onAddToCart} />
        )}
      </main>
    </Main>
  )
}

export default NewSellOrder

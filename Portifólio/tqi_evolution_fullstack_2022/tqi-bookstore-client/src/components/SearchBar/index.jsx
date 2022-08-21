import React, { useState} from 'react'

const SearchBar = ({onSearch, title}) => {
const [search, setSearch] = useState('')

const handleOnChangeSearch = e => setSearch(e.target.value)

const handleSearch = () => onSearch(search)

  return (
    <div class="search-bar-container">
    <h2>{title}</h2>
    <div class="search-wrapper">
          <input
            class="search-bar"
            value={search}
            onChange={handleOnChangeSearch}
            type="text"
            placeholder="Digite aqui para pesquisar..."
          />
          <button 
          onClick={handleSearch}
          >Pesquisar</button>
</div>
</div>
  )
}

export default SearchBar
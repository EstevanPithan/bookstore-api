import React from 'react'

const Input = ({ title, isValid, value, onChange, name }) => {
  return (
    <div className="search-bar-container">
      <div className="registration-container">
        <h3>{title}</h3>
        <input
          className={`input-bar search-bar ${
            isValid ? '' : 'input-feedback-invalid'
          }`}
          type="text"
          value={value}
          name={name}
          onChange={onChange}
          placeholder="Digite aqui..."
        />
        {!isValid && (
          <p className="p-feedback-invalid">Digite um {title} válido.</p>
        )}
      </div>
    </div>
  )
}

export default Input

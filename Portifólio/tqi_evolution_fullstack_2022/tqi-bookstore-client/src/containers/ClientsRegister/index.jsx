import React, { useState } from 'react'
import Main from '../../components/Main'
import Input from '../../components/Input'
import { api } from '../../services/api'

const initialFormData = { name: '', cpf: '', email: '' }

const ClientsRegister = () => {
  const [formData, setFormData] = useState(initialFormData)
  const [validation, setValidation] = useState({
    name: true,
    cpf: true,
    email: true
  })

  const handleInputChange = e =>
    setFormData(previous => ({ ...previous, [e.target.name]: e.target.value }))

  const validateInputs = form => {
    const tempValidation = { name: true, cpf: true, email: true }

    if (form.name.trim().length === 0) {
      tempValidation.name = false
    }
    if (form.cpf.trim().length === 0) {
      tempValidation.cpf = false
    }
    if (form.email.trim().length === 0) {
      tempValidation.email = false
    }

    setValidation(tempValidation)

    const formIsValid = Object.values(tempValidation).every(val => val === true)

    return formIsValid
  }

  const handleSubmit = async () => {
    const formIsValid = validateInputs(formData)

    if (!formIsValid) {
      return
    }

    try {
      await api.post('/client/create', formData)
      alert('Cliente cadastrado!')
      setFormData(initialFormData)
    } catch (error) {
      alert('Erro ao cadastrar Cliente.')
    }
  }

  return (
    <Main>
      <main className="container">
        <div className="search-bar-container">
          <h2>Cadastro de Clientes</h2>
        </div>

        <div className="cards-wrapper">
          <Input
            title="Nome"
            name="name"
            isValid={validation.name}
            value={formData.name}
            onChange={handleInputChange}
          />
          <Input
            title="CPF"
            name="cpf"
            isValid={validation.cpf}
            value={formData.cpf}
            onChange={handleInputChange}
          />
          <Input
            title="Email"
            name="email"
            isValid={validation.email}
            value={formData.email}
            onChange={handleInputChange}
          />

          <div className="registration-buttons">
            <button onClick={handleSubmit}>Salvar</button>
            <button>Cancelar</button>
          </div>
        </div>
      </main>
    </Main>
  )
}

export default ClientsRegister

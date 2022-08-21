import React, {useState} from 'react'
import Main from "../../components/Main"
import Input from "../../components/Input";
import { api } from "../../services/api";

const initialFormData = {name: '', image: '', author: '', publishingCompany: '',  year: '' }

const BooksRegister = () => {
const [formData, setFormData] = useState(initialFormData)
const [validation, setValidation] = useState({name: true, image: true, author: true, publishingCompany: true,  year: true })
    
const handleInputChange = e => setFormData(previous => ({...previous, [e.target.name] : e.target.value}))

    const validateInputs = (form) => {

        const tempValidation = {name: true, image: true, author: true, publishingCompany: true,  year: true }

        if(form.name.trim().length === 0){ tempValidation.name= false}
        if(form.image.trim().length === 0){ tempValidation.image= false}
        if(form.author.trim().length === 0){ tempValidation.author= false}
        if(form.publishingCompany.trim().length === 0){ tempValidation.publishingCompany= false}
        if(form.year.trim().length === 0 || isNaN(Number(form.year))){ tempValidation.year= false}

        setValidation(tempValidation)

        const formIsValid = Object.values(tempValidation).every(val => val === true)

        return formIsValid
    }

    const handleSubmit = async () => {
        const formIsValid = validateInputs(formData)

        if(!formIsValid){
            return;
        }

        try {
            await api.post("/book/create", formData)
            alert("Livro cadastrado!")
            setFormData(initialFormData)
        } catch (error) {
            alert("Erro ao cadastrar livro.")
        }

  
    }


    return (
   <Main>
    <main className="container">
      <div className="search-bar-container">
        <h2>Cadastro de Livro</h2>
      </div>

      <div className="cards-wrapper">

        <Input title="Título" name="name" isValid={validation.name} value={formData.name} onChange={handleInputChange} />
        <Input title="URL da imagem do livro" name="image" isValid={validation.image} value={formData.image} onChange={handleInputChange} />
        <Input title="Autor" name="author" isValid={validation.author} value={formData.author} onChange={handleInputChange} />
        <Input title="Editora" name="publishingCompany" isValid={validation.publishingCompany} value={formData.publishingCompany} onChange={handleInputChange} />
        <Input title="Ano de publicação" name="year" isValid={validation.year} value={formData.year} onChange={handleInputChange} />

        <div className="registration-buttons">
          <button onClick={handleSubmit}>Salvar</button>
          <button>Cancelar</button>
        </div>
      </div>
    </main>

   </Main>
  )
}

export default BooksRegister